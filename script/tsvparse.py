import json
import re
import datetime

itemList = []
for line in open('/home/ec2-user/script/tmp.tsv','r'):
    itemList.append(line[:-1].split('\t'))

json_data = {'Python':'python','Serch':'Engine'}

encode_json_data = json.dumps(json_data)

itemName = itemList.pop(0)

itemList_json = []

for item in itemList:
    json_data = {}
    for i, e in enumerate(item):
        if(itemName[i] == "startdate" or itemName[i] == "enddate"):
            if(e != ""):
                e = e[0:10]
                e = datetime.datetime.fromtimestamp(int(e)).strftime('%Y-%m-%d %H:%M:%S')
        json_data.update({itemName[i]:e})
    itemList_json.append(json_data)

with open('/home/ec2-user/script/tmp.json','w') as f:
     json.dump(itemList_json,f,sort_keys=True,indent=4)


file = "";
for line in open('/home/ec2-user/script/tmp.json','r'):
    if line.find("]\n") != -1:
        pass
    elif line.find("[\n") != -1:
        pass
    elif line.find("},") == -1:
        file += line.strip();
    else:
        file += line;

file = file.replace('\\r','');
file = file.replace('\\ufeff','');

with open('/home/ec2-user/script/ticket.json','w') as f:
    f.write(file);
