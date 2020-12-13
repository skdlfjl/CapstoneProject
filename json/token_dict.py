import json
from collections import OrderedDict



token_dict = OrderedDict() #자동 정렬을 막아줌

tokens = OrderedDict()




#tokens.txt 파일에서 토큰을 읽어온다.
with open("token_data/tokens.txt", "r") as f:
    token= []
    line = f.readline()
    line = line.split(",")
    while line!='':
        token.append(line)
        line = f.readline()



for x in range(len(token[0])) :
        tokens[token[0][x]] = 0


#dict1 에 tokens 추가
token_dict["tokens"] = tokens




with open("token_dict.json", 'w') as make_file:
    json.dump(token_dict, make_file, ensure_ascii=False, indent="")

