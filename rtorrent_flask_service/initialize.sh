#!/usr/bin/env bash

echo -e "\tCreating folders."
mkdir uploaded

echo -e "\tInitializing virtual environment."
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt

#echo -e "\tExecuting application."
#python app.py
