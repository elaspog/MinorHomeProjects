
import pandas as pd
import config as cfg
import os.path
from os import path


def read_database():

    file_name = cfg.course_status_database_file['file_name']
    if path.exists(file_name):
        return pd.read_csv(file_name, sep=';', encoding='utf-8')
    else:
        return pd.DataFrame(columns=cfg.course_status_database_file['csv_column_names'])


def write_database(dataframe):

    file_name = cfg.course_status_database_file['file_name']
    dataframe.to_csv(file_name, sep=';', encoding='utf-8', index=False)
