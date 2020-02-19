
import random
import config as cfg


class UserTimeGenerator():


    def __init__(self, from_second = None, to_second = None):
        self.from_second    = from_second if from_second != None else float(cfg.user_reaction_simulation['from_second'])
        self.to_second      = to_second if to_second != None else float(cfg.user_reaction_simulation['to_second'])
        self.time_gen       = self.reaction_time_generator()


    @staticmethod
    def Between(from_sec, to_sec):
        return random.uniform(from_sec, to_sec)


    def reaction_time_generator(self):

        while True:
            yield random.uniform(self.from_second, self.to_second)


    def generate_user_wait_time(self):

        return next(self.time_gen)
