
import time

from user_time_simulator import UserTimeGenerator

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options


class BrowserController(object):


    def __init__(self, *arguments):
        self.arguments = [str(arg) for arg in arguments]
        self.browser_options, self.browser = self._initialize_selenium_browser()

        # Initialize user reaction time simulation
        self.utg = UserTimeGenerator()


    def _initialize_selenium_browser(self):

        browser_options = Options()
        for arg in self.arguments:
            browser_options.add_argument(arg)

        try:
            browser = webdriver.Chrome(options=browser_options)

        except Exception as e:
            raise "Chrome driver not found."

        if browser == None:
            raise "No driver found."

        return browser_options, browser


    def describe(self):
        print("Driver settings: " + ", ".join(self.arguments))


    def wait(self, seconds=None):

        if not seconds:
            seconds = self.utg.generate_user_wait_time()

        time.sleep(seconds)
        return self


    def navigate(self, site_url):

        if not isinstance(site_url, str):
            raise "URL is not string: %s" % str(site_url)

        self.browser.get(site_url)
        return self


    def find(self, multi=False, xpath=None, tag=None):

        if not xpath and not tag and not id:
            raise "No valid argument passed"

        if xpath and not isinstance(xpath, str):
            raise "XPath is not string: %s" % str(xpath)

        if tag and not isinstance(tag, str):
            raise "TagName is not string: %s" % str(xpath)

        if xpath:
            if multi:
                self._state_val = self.browser.find_elements(By.XPATH, xpath)
            else:
                self._state_val = self.browser.find_element(By.XPATH, xpath)

        if tag:
            self._state_val = self.browser.find_elements_by_tag_name(tag)

        return self


    def click(self):

        if self._state_val:
            self._state_val.click()

        return self


    def enter(self, string_value):

        if self._state_val:
            self._state_val.send_keys(string_value)

        return self


    def human_type(self, string_value):

        if self._state_val:
            for s in string_value:
                self.wait(UserTimeGenerator.Between(0.1, 0.3))
                self._state_val.send_keys(s)

        return self


    @property
    def state(self):
        return self._state_val


    def reset_state(self):
        self._state_val = None
        return self.state


    def read_attribute(self, attribute_name):

        if not isinstance(attribute_name, str) or not attribute_name:
            raise "Invalid attribute_name: '%s'" % str(attribute_name)

        if self._state_val == None:
            return None

        return self._state_val.get_attribute(attribute_name)


    def exit(self):

        self.browser.quit()
