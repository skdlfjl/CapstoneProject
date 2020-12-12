import json
from collections import OrderedDict



search_dict = OrderedDict() #자동 정렬을 막아줌
search = OrderedDict()



#search.txt 파일에서 난수를 얻어온다.
with open("./search.txt" , "r") as f:
    searches= []
    line = f.readline()
    line = line.split(",")
    while line!='':
        searches.append(line)
        line = f.readline()

number = 1

for x in range(len(searches[0])) :
        search[int(number)] = searches[0][x]
        number +=1

# dict1에 search 추가
search_dict["search"] = search


with open("search_dict.json", 'w') as make_file:
    json.dump(search_dict, make_file, ensure_ascii=False, indent="")

