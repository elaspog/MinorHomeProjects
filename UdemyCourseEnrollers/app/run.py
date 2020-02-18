#!/usr/bin/env python

import sys
import time
import pandas as pd

import config as cfg
from selenium_browser import BrowserController
from user_time_simulator import UserTimeGenerator
import downloaded_courses_manager as dm


# Initialize Navigator
bc = BrowserController(cfg.browser['mode'], cfg.browser['window_size'])

# Initialize user reaction time simulation
utg = UserTimeGenerator()


def read_current_courses_from_site(page_count):

    course_urls_with_coupons = []
    articles_xpath = cfg.site['freebiesglobal']['udemy_collections']['xpath_articles']

    for i in range(1, page_count+1):
        url = cfg.site['freebiesglobal']['udemy_collections']['url_template'] % str(i)
        bc.wait(0.1)
        bc.navigate(url)
        course_urls_with_coupons = course_urls_with_coupons + [x.get_attribute("href") for x in bc.find(multi=True, xpath=articles_xpath).state]

    scraped_course_info_df = pd.DataFrame(course_urls_with_coupons, columns = ["courses_w_coupons"])
    scraped_course_info_df["course_url"] = scraped_course_info_df.apply(lambda x: x['courses_w_coupons'].split("?")[0], axis=1)

    return scraped_course_info_df


def process_coupons(courses_df):

    col_name_status = cfg.course_status_database_file["csv_column_names"][0]

    # Select rows where 'status'='todo' and 'courses_w_coupons' has non null value
    to_process_df = courses_df[ (courses_df[["courses_w_coupons"]].notnull().any(axis=1)) & (courses_df[col_name_status] == 'todo') ]

    for _, row in to_process_df.iterrows():
        bc.navigate(row["courses_w_coupons"])


def join_database_with_currently_scraped(database_df, scraped_df):

    col_name_status = cfg.course_status_database_file["csv_column_names"][0]
    col_name_course = cfg.course_status_database_file["csv_column_names"][1]
    stat_to_process = cfg.course_status_database_file["status_values"][1]

    # Fill statuses of unseen courses with 'todo' string
    courses_df = pd.merge(database_df, scraped_course_info_df, left_on=[col_name_course], right_on=[col_name_course], how="outer")
    courses_df[[col_name_status]] = courses_df[[col_name_status]].fillna(stat_to_process)

    return courses_df


if __name__== "__main__":

    # Read existing scraping information
    database_df = dm.read_database()

    print(database_df)

    # Read paging information
    bc.navigate(cfg.site['freebiesglobal']['udemy_collections']['url_first_page'])
    page_count = bc.find(xpath=cfg.site['freebiesglobal']['page_count']).read_attribute("text")

    print("page_count: ", page_count)

    # Read all courses from coupon site
    scraped_course_info_df = read_current_courses_from_site(int(page_count))

    # Join currently scraped information with already stored
    joined_courses_df = join_database_with_currently_scraped(database_df, scraped_course_info_df)

    # Process coupons found
    process_coupons(joined_courses_df)

    print(joined_courses_df)

    # Write results to database file
    dm.write_database(joined_courses_df[["status", "course_url"]])
