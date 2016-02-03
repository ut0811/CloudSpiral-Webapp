
#tmp_date=`date '+%Y%m%d-%H%M%S'`

/usr/bin/sudo /usr/bin/wget -O /home/ec2-user/webapps/webapp/web/TimelineRss.xml --http-user=yuta --http-passwd=yuuta343 "http://ec2-52-69-42-30.ap-northeast-1.compute.amazonaws.com/trac/login?referer=%2Ftrac%2Ftimeline%3Fmilestone%3Don%26ticket%3Don%26changeset%3Don%26wiki%3Don%26max%3D50%26authors%3D%26daysback%3D90%26format%3Drss" 



