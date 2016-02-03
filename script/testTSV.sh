
#tmp_date=`date '+%Y%m%d-%H%M%S'`

/usr/bin/wget -O /home/ec2-user/script/tmp.tsv --http-user=yuta --http-passwd=yuuta343 "http://ec2-52-69-42-30.ap-northeast-1.compute.amazonaws.com/trac/query?status=accepted&status=assigned&status=closed&status=new&status=reopened&format=tab&col=id&col=summary&col=status&col=type&col=priority&col=milestone&col=component&col=version&col=resolution&col=time&col=changetime&col=estimatedhours&col=startdate&col=totalhours&col=enddate&col=reporter&col=keywords&col=cc&col=owner&report=18&order=priority" 


/usr/bin/python /home/ec2-user/script/tsvparse.py
/usr/bin/mongo localhost/allticket /home/ec2-user/script/mongobat.js
/usr/bin/mongoimport --db allticket --collection TICKET --type json --file /home/ec2-user/script/ticket.json
