import json
from collections import OrderedDict

erase = OrderedDict()


with open("erase.json", 'w') as make_file:
    json.dump(erase, make_file, ensure_ascii=False, indent="")