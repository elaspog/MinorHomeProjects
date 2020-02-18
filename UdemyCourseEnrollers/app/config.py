

course_status_database_file = {
    'file_name' : 'courses_data.csv',
    'csv_column_names' : ['status', 'course_url'],
    'status_values' : ('done', 'todo')
}

browser = {
    'window_size': '--window-size=1920x1080',
    'mode': '--headless'
}

user_reaction_simulation = {
    'from_second' : 2,
    'to_second' : 8
}

site = {
    'udemy' : {
        'enroll_button' : '//button[text()="Enroll now"]'
    },
    'freebiesglobal': {
        'navigation_back' : '//a[text()="« Previous Page"]',                    # not present on the first page
        'navigation_next' : '//a[text()="Next Page »"]',                        # not present on the last page
        'page_count': '//li[a[.="Next Page »"]]/preceding-sibling::li[1]/a',    # not present on the last page,
        # 'every_course_list': {
        #     'url': 'https://freebiesglobal.com',
        #     'xpath_articles_parent_1': '/html/body/div[2]/div[2]/div/div/article/div[2]/div/div/div/div[2]',
        #     'xpath_articles_parent_2': '/html/body/div[2]/div[2]/div/div/article/div[3]/div/div/div/div[1]'
        # },
        'udemy_collections': {
            'url_first_page': 'https://freebiesglobal.com/dealstore/udemy',
            'url_template': 'https://freebiesglobal.com/dealstore/udemy/page/%s',
            'xpath_articles': '/html/body/div[2]/div[2]/div/div[3]/article/div[1]/div[2]/article/div[1]/div/div[2]/div/span/a'
        }
    }
}