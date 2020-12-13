import json
from collections import OrderedDict


dict1 = OrderedDict() #자동 정렬을 막아줌
key =  OrderedDict()
tokens = OrderedDict()
search = OrderedDict()

# key 제목을 만들고 dict1 에 추가

key["key"] = 0
dict1["key"] = key


#tokens.txt 파일에서 토큰을 읽어온다.
with open("token_data/edit_token.txt", "r") as f:
    token= []
    line = f.readline()
    line = line.split(",")
    while line!='':
        token.append(line)
        line = f.readline()

number = 1

for x in range(len(token[0])) :
        tokens[int(number)] = token[0][x]
        number +=1


#dict1 에 tokens 추가
dict1["tokens"] = tokens




#search.txt 파일에서 난수를 얻어온다.
number2 =1

with open("token_data/edit_search.txt", "r") as f:
    searches= []
    line = f.readline()
    line = line.split(",")
    while line!='':
        searches.append(line)
        line = f.readline()

for x in range(len(searches[0])) :
        search[int(number2)] = searches[0][x]
        number2 +=1

# dict1에 search 추가
dict1["search"] = search


with open("edit_dict.json", 'w') as make_file:
    json.dump(dict1, make_file, ensure_ascii=False, indent="")

