package jp.kobe_u.cspiral.alpaca;

import java.util.ArrayList;
import java.util.List;

import jp.kobe_u.cspiral.alpaca.model.AcceptList;
import jp.kobe_u.cspiral.alpaca.model.Progress;
import jp.kobe_u.cspiral.alpaca.model.Ticket;
//import jp.kobe_u.cspiral.alpaca.model.TracLog;
import jp.kobe_u.cspiral.alpaca.util.DBUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TopController {
	private final String TICKET_COLLECTION_NAME = "TICKET";
//	private final String TRACLOG_COLLECTION_NAME = "TRACLOG";

	private DBCollection TICKET_COLLECTION;
//	private DBCollection TRACLOG_COLLECTION;

	public TopController() {
		this.TICKET_COLLECTION = DBUtils.getInstance().getDb().getCollection(TICKET_COLLECTION_NAME);
//		this.TRACLOG_COLLECTION = DBUtils.getInstance().getDb().getCollection(TRACLOG_COLLECTION_NAME);
	}

	public List<String> findOwnerList() {
		List<String> OwnerList = new ArrayList<>();
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject());
		for (DBObject o : cursor) {
			String owner = o.get("owner").toString();
			if(!OwnerList.contains(owner)){
				OwnerList.add(owner);
			}
		}
		return OwnerList;
	}

	public List<String> findUCList() {
		List<String> UCList = new ArrayList<>();
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject());
		for (DBObject o : cursor) {
			String UCName = o.get("keywords").toString();
			if(!UCList.contains(UCName)){
				UCList.add(UCName);
			}
		}
		return UCList;
	}

	public double calcTotalProgress(){
		double total;
		double closed;
		double progress;
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject());
		total = cursor.count();
		DBCursor cursor2 = TICKET_COLLECTION.find(new BasicDBObject("status", "closed"));
		closed = cursor2.count();

		progress = closed / total * 100;

		return progress;
	}

	public List<Progress> calcUCProgress(){
		List<Progress> UCProgress = new ArrayList<>();
		List<String> UCNameList = findUCList();
		for(String UCName:UCNameList){
			double total;
			double closed;
			double progress;
			DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject("keywords",UCName));
			total = cursor.count();
			DBCursor cursor2 = TICKET_COLLECTION.find(new BasicDBObject("status", "closed").append("keywords", UCName));
			closed = cursor2.count();

			progress = closed / total * 100;
			UCProgress.add(new Progress(UCName, progress));
		}

		return UCProgress;
	}

	public List<AcceptList> findByAccepted() {
		List<String> ownerList = findOwnerList();
		List<AcceptList> acceptList  = new ArrayList<>();
		List<Ticket> list = new ArrayList<>();
		for(String owner:ownerList){
			DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject("owner",owner).append("status", "accepted"));
			for(DBObject o : cursor){
			Ticket tempTicket = new Ticket();
			tempTicket.setId(o.get("id").toString());
			tempTicket.setSummary(o.get("summary").toString());
			tempTicket.setStatus(o.get("status").toString());
			tempTicket.setOwner(o.get("owner").toString());
			tempTicket.setType(o.get("type").toString());
			tempTicket.setPriority(null);
			/*   まつしん先生コード
			if (o.get("priority") != null) {
				tempTicket.setPriority(o.get("priority").toString());
			} else {
				tempTicket.setPriority(null);
			}
			*/
			tempTicket.setMilestone(o.get("milestone").toString());
			tempTicket.setComponent(o.get("component").toString());
			tempTicket.setResolution(o.get("resolution").toString());
			tempTicket.setTime(o.get("time").toString());
			tempTicket.setChangetime(o.get("changetime").toString());
			tempTicket.setEstimatedhours(o.get("estimatedhours").toString());
			tempTicket.setStartdate(o.get("startdate").toString());
			tempTicket.setTotalhours(o.get("totalhours").toString());
			tempTicket.setEnddate(o.get("enddate").toString());
			tempTicket.setReporter(o.get("reporter").toString());
			tempTicket.setKeywords(o.get("keywords").toString());
			tempTicket.setCc(o.get("cc").toString());

			list.add(tempTicket);
			}
			acceptList.add(new AcceptList(owner, list));
		}
		return acceptList;
	}
/*
	public List<TracLog> findTracLog(){
		List<TracLog> list = new ArrayList<>();
		DBCursor cursor = TRACLOG_COLLECTION.find(new BasicDBObject());
		for (DBObject o : cursor) {
			TracLog tempTracLog = new TracLog();
			tempTracLog.setTitle(o.get("title").toString());
			tempTracLog.setCreater(o.get("creater").toString());
			tempTracLog.setPubDate(o.get("pubDate").toString());
			tempTracLog.setLink(o.get("link").toString());
			tempTracLog.setDescription(o.get("description").toString());
			tempTracLog.setCategory(o.get("category").toString());

			list.add(tempTracLog);
		}
		return list;
	}
	*/

}
