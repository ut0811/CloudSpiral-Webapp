package jp.kobe_u.cspiral.alpaca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.management.counter.Variability;
import jp.kobe_u.cspiral.alpaca.model.Err;
import jp.kobe_u.cspiral.alpaca.model.Est;
import jp.kobe_u.cspiral.alpaca.model.Ticket;
import jp.kobe_u.cspiral.alpaca.model.TracLog;
import jp.kobe_u.cspiral.alpaca.util.DBUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class PersonalController {

	private final String TICKET_COLLECTION_NAME = "TICKET";
	private final String TRACLOG_COLLECTION_NAME = "TRACLOG";

	private DBCollection TICKET_COLLECTION;
	private DBCollection TRACLOG_COLLECTION;

	public PersonalController() {
		this.TICKET_COLLECTION = DBUtils.getInstance().getDb().getCollection(TICKET_COLLECTION_NAME);
		this.TRACLOG_COLLECTION = DBUtils.getInstance().getDb().getCollection(TRACLOG_COLLECTION_NAME);
	}


	private String getDBObjectString(Object temp){
		if(temp==null){
			return null;
		}
		return temp.toString();
	}

	public List<Ticket> findByOwnerAndAccepted(String ownerName) {
		List<Ticket> list = new ArrayList<>();
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject("owner",ownerName).append("status", "accepted"));
//		DBCursor cursor = TICKET_COLLECTION.find();
		for (DBObject o : cursor) {
			System.out.println(o);
			Ticket tempTicket = new Ticket();
//			tempTicket.setId(o.get("id").toString());
//			tempTicket.setSummary(o.get("summary").toString());
//			tempTicket.setStatus(o.get("status").toString());
//			tempTicket.setOwner(o.get("owner").toString());
//			tempTicket.setType(o.get("type").toString());
//			tempTicket.setMilestone(o.get("milestone").toString());
//			tempTicket.setComponent(o.get("component").toString());
//			tempTicket.setResolution(o.get("resolution").toString());
//			tempTicket.setTime(o.get("time").toString());
//			tempTicket.setChangetime(o.get("changetime").toString());
//			tempTicket.setEstimatedhours(o.get("estimatedhours").toString());
//			tempTicket.setStartdate(o.get("startdate").toString());
//			tempTicket.setTotalhours(o.get("totalhours").toString());
//			tempTicket.setEnddate(o.get("enddate").toString());
//			tempTicket.setReporter(o.get("reporter").toString());
//			tempTicket.setKeywords(o.get("keywords").toString());
//			tempTicket.setCc(o.get("cc").toString());
//			tempTicket.setPriority(null);
			/*   まつしん先生コード
			if (o.get("priority") != null) {
				tempTicket.setPriority(o.get("priority").toString());
			} else {
				tempTicket.setPriority(null);
			}
			*/
			//tempTicket.setPriority(o.get("priority").toString());

			tempTicket.setId(getDBObjectString(o.get("id")));
			tempTicket.setSummary(getDBObjectString(o.get("summary")));
			tempTicket.setStatus(getDBObjectString(o.get("status")));
			tempTicket.setOwner(getDBObjectString(o.get("owner")));
			tempTicket.setType(getDBObjectString(o.get("type")));
			tempTicket.setMilestone(getDBObjectString(o.get("milestone")));
			tempTicket.setComponent(getDBObjectString(o.get("component")));
			tempTicket.setResolution(getDBObjectString(o.get("resolution")));
			tempTicket.setTime(getDBObjectString(o.get("time")));
			tempTicket.setChangetime(getDBObjectString(o.get("changetime")));
			tempTicket.setEstimatedhours(getDBObjectString(o.get("estimatedhours")));
			tempTicket.setStartdate(getDBObjectString(o.get("startdate")));
			tempTicket.setTotalhours(getDBObjectString(o.get("totalhours")));
			tempTicket.setEnddate(getDBObjectString(o.get("enddate")));
			tempTicket.setReporter(getDBObjectString(o.get("reporter")));
			tempTicket.setKeywords(getDBObjectString(o.get("keywords")));
			tempTicket.setCc(getDBObjectString(o.get("cc")));
			tempTicket.setPriority(getDBObjectString(o.get("priority")));

			list.add(tempTicket);
		}
		return list;
	}

	public List<Ticket> findByOwnerAndFixed(String ownerName){
		List<Ticket> list = new ArrayList<>();
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject("owner",ownerName).append("resolution", "fixed"));
		for (DBObject o : cursor) {
			Ticket tempTicket = new Ticket();
//			tempTicket.setId(o.get("id").toString());
//			tempTicket.setSummary(o.get("summary").toString());
//			tempTicket.setStatus(o.get("status").toString());
//			tempTicket.setOwner(o.get("owner").toString());
//			tempTicket.setType(o.get("type").toString());
//			tempTicket.setMilestone(o.get("milestone").toString());
//			tempTicket.setComponent(o.get("component").toString());
//			tempTicket.setResolution(o.get("resolution").toString());
//			tempTicket.setTime(o.get("time").toString());
//			tempTicket.setChangetime(o.get("changetime").toString());
//			tempTicket.setEstimatedhours(o.get("estimatedhours").toString());
//			tempTicket.setStartdate(o.get("startdate").toString());
//			tempTicket.setTotalhours(o.get("totalhours").toString());
//			tempTicket.setEnddate(o.get("enddate").toString());
//			tempTicket.setReporter(o.get("reporter").toString());
//			tempTicket.setKeywords(o.get("keywords").toString());
//			tempTicket.setCc(o.get("cc").toString());
//			tempTicket.setPriority(null);
			/*   まつしん先生コード
			if (o.get("priority") != null) {
				tempTicket.setPriority(o.get("priority").toString());
			} else {
				tempTicket.setPriority(null);
			}
			*/
			//			tempTicket.setPriority(o.get("priority").toString());

			tempTicket.setId(getDBObjectString(o.get("id")));
			tempTicket.setSummary(getDBObjectString(o.get("summary")));
			tempTicket.setStatus(getDBObjectString(o.get("status")));
			tempTicket.setOwner(getDBObjectString(o.get("owner")));
			tempTicket.setType(getDBObjectString(o.get("type")));
			tempTicket.setMilestone(getDBObjectString(o.get("milestone")));
			tempTicket.setComponent(getDBObjectString(o.get("component")));
			tempTicket.setResolution(getDBObjectString(o.get("resolution")));
			tempTicket.setTime(getDBObjectString(o.get("time")));
			tempTicket.setChangetime(getDBObjectString(o.get("changetime")));
			tempTicket.setEstimatedhours(getDBObjectString(o.get("estimatedhours")));
			tempTicket.setStartdate(getDBObjectString(o.get("startdate")));
			tempTicket.setTotalhours(getDBObjectString(o.get("totalhours")));
			tempTicket.setEnddate(getDBObjectString(o.get("enddate")));
			tempTicket.setReporter(getDBObjectString(o.get("reporter")));
			tempTicket.setKeywords(getDBObjectString(o.get("keywords")));
			tempTicket.setCc(getDBObjectString(o.get("cc")));
			tempTicket.setPriority(getDBObjectString(o.get("priority")));

			list.add(tempTicket);
		}
		return list;
	}

	public List<TracLog> findTracLogByName(String name){
		List<TracLog> list = new ArrayList<>();
		DBCursor cursor = TRACLOG_COLLECTION.find(new BasicDBObject("creater",name));
		for (DBObject o : cursor) {
			TracLog tempTracLog = new TracLog();
//			tempTracLog.setTitle(o.get("title").toString());
//			tempTracLog.setCreater(o.get("creater").toString());
//			tempTracLog.setPubDate(o.get("pubDate").toString());
//			tempTracLog.setLink(o.get("link").toString());
//			tempTracLog.setDescription(o.get("description").toString());
//			tempTracLog.setCategory(o.get("category").toString());

			tempTracLog.setTitle(getDBObjectString(o.get("title")));
			tempTracLog.setCreater(getDBObjectString(o.get("creater")));
			tempTracLog.setPubDate(getDBObjectString(o.get("pubDate")));
			tempTracLog.setLink(getDBObjectString(o.get("link")));
			tempTracLog.setDescription(getDBObjectString(o.get("description")));
			tempTracLog.setCategory(getDBObjectString(o.get("category")));

			list.add(tempTracLog);
		}
		return list;
	}

	public List<Est> calculateEstErr(List<Ticket> FixedTicket){
		List<Est> estErrList = new ArrayList<Est>();
		for(int i=0;i<FixedTicket.size();i++){
			Est tempEst = new Est();
			Ticket tempTicket = FixedTicket.get(i);
			if(tempTicket.getTotalhours()== null || tempTicket.getEstimatedhours() == null){//どちらかがnullのときエスケープしないとバグるので注意
				tempEst.setEstErr("");
			}else{
//				tempEst.setEstErr(String.valueOf(Math.abs(Float.parseFloat(tempTicket.getTotalhours()) - Float.parseFloat(tempTicket.getEstimatedhours()))/Integer.parseInt(tempTicket.getTotalhours())));
				tempEst.setEstErr(String.valueOf(Math.abs(Integer.parseInt(tempTicket.getTotalhours()) - Integer.parseInt(tempTicket.getEstimatedhours()) * 100)/Integer.parseInt(tempTicket.getTotalhours())));
			}
			tempEst.setTicketName("ID " + tempTicket.getId()+ ":" + tempTicket.getSummary());
			estErrList.add(tempEst);
		}
		return estErrList;
	}

	public List<Err> calculateErr(List<Ticket> AcceptTicket, List<Ticket> FixedTicket){
		List<Err> ErrList = new ArrayList<Err>();

		if(AcceptTicket.size()>1){
			Err tempErr = new Err();
			tempErr.setErrMsg("マルチタスクになっています.");
			List<String> tempList = new ArrayList<String>();
			for(int i=0;i<AcceptTicket.size();i++)
				tempList.add("ID " + AcceptTicket.get(i).getId()+ ":" + AcceptTicket.get(i).getSummary());
			tempErr.setTicketName(tempList);
			ErrList.add(tempErr);
		}

		for(int i=0;i<AcceptTicket.size();i++){
			Boolean errFlag = false;
			String errMsgString = "チケットに入力漏れがあります．(";
			if(AcceptTicket.get(i).getId() == ""){
				errFlag = true;
				errMsgString += "'id',";
			}
			if(AcceptTicket.get(i).getSummary() == ""){
				errFlag = true;
				errMsgString += "'summary',";
			}
			if(AcceptTicket.get(i).getStatus() == ""){
				errFlag = true;
				errMsgString += "'status',";
			}
			if(AcceptTicket.get(i).getOwner() == ""){
				errFlag = true;
				errMsgString += "'owner',";
			}
			if(AcceptTicket.get(i).getType() == ""){
				errFlag = true;
				errMsgString += "'type',";
			}
			if(AcceptTicket.get(i).getMilestone() == ""){
				errFlag = true;
				errMsgString += "'milestone',";
			}
			if(AcceptTicket.get(i).getComponent() == ""){
				errFlag = true;
				errMsgString += "'component',";
			}
			if(AcceptTicket.get(i).getTime() == ""){
				errFlag = true;
				errMsgString += "'time',";
			}
			if(AcceptTicket.get(i).getChangetime() == ""){
				errFlag = true;
				errMsgString += "'changetime',";
			}
			if(AcceptTicket.get(i).getEstimatedhours() == ""){
				errFlag = true;
				errMsgString += "'estimatehours',";
			}
			if(AcceptTicket.get(i).getStartdate() == ""){
				errFlag = true;
				errMsgString += "'startdate',";
			}
			if(AcceptTicket.get(i).getTotalhours() == ""){
				errFlag = true;
				errMsgString += "'totalhours',";
			}
			if(AcceptTicket.get(i).getReporter() == ""){
				errFlag = true;
				errMsgString += "'reporter',";
			}
			if(AcceptTicket.get(i).getKeywords() == ""){
				errFlag = true;
				errMsgString += "'keywords',";
			}

			if(errFlag){
				Err tempErr = new Err();
				errMsgString = errMsgString.substring(0, errMsgString.length()-1);
				tempErr.setErrMsg(errMsgString + ")");
				List<String> tempList = new ArrayList<String>();
				tempList.add("ID " + AcceptTicket.get(i).getId()+ ":" + AcceptTicket.get(i).getSummary());
				tempErr.setTicketName(tempList);
				ErrList.add(tempErr);
			}
		}


		for(int i=0;i<FixedTicket.size();i++){
			Boolean errFlag = false;
			String errMsgString = "チケットに入力漏れがあります．(";
			if(FixedTicket.get(i).getId() == ""){
				errFlag = true;
				errMsgString += "'id',";
			}
			if(FixedTicket.get(i).getSummary() == ""){
				errFlag = true;
				errMsgString += "'summary',";
			}
			if(FixedTicket.get(i).getStatus() == ""){
				errFlag = true;
				errMsgString += "'status',";
			}
			if(FixedTicket.get(i).getOwner() == ""){
				errFlag = true;
				errMsgString += "'owner',";
			}
			if(FixedTicket.get(i).getType() == ""){
				errFlag = true;
				errMsgString += "'type',";
			}
			if(FixedTicket.get(i).getMilestone() == ""){
				errFlag = true;
				errMsgString += "'milestone',";
			}
			if(FixedTicket.get(i).getComponent() == ""){
				errFlag = true;
				errMsgString += "'component',";
			}
			if(FixedTicket.get(i).getResolution() == ""){
				errFlag = true;
				errMsgString += "'resolution',";
			}
			if(FixedTicket.get(i).getTime() == ""){
				errFlag = true;
				errMsgString += "'time',";
			}
			if(FixedTicket.get(i).getChangetime() == ""){
				errFlag = true;
				errMsgString += "'changetime',";
			}
			if(FixedTicket.get(i).getEstimatedhours() == ""){
				errFlag = true;
				errMsgString += "'estimatehours',";
			}
			if(FixedTicket.get(i).getStartdate() == ""){
				errFlag = true;
				errMsgString += "'startdate',";
			}
			if(FixedTicket.get(i).getTotalhours() == ""){
				errFlag = true;
				errMsgString += "'totalhours',";
			}
			if(FixedTicket.get(i).getEnddate() == ""){
				errFlag = true;
				errMsgString += "'enddate',";
			}
			if(FixedTicket.get(i).getReporter() == ""){
				errFlag = true;
				errMsgString += "'reporter',";
			}
			if(FixedTicket.get(i).getKeywords() == ""){
				errFlag = true;
				errMsgString += "'keywords',";
			}

			if(errFlag){
				Err tempErr = new Err();
				errMsgString = errMsgString.substring(0, errMsgString.length()-1);
				tempErr.setErrMsg(errMsgString+")");
				List<String> tempList = new ArrayList<String>();
				tempList.add("ID " + FixedTicket.get(i).getId()+ ":" + FixedTicket.get(i).getSummary());
				tempErr.setTicketName(tempList);
				ErrList.add(tempErr);
			}

		}
		return ErrList;
	}

	public String calcurateExp(List<Ticket> fixedTickets){
		final int BasePoint = 100;//チケット1枚達成ごとに得られる経験地
		final float TotalhoursBounusRate = 10.0f;//チケットにかかった時間を経験地する際の倍率
		int Exp = 0;
		for (Ticket ticket : fixedTickets) {
			int tempExp = BasePoint;
			tempExp += Integer.parseInt(ticket.getTotalhours()) * TotalhoursBounusRate;

			Exp += tempExp;
		}
		return Integer.toString(Exp);
	}

	public List<String> calcurateBatch(List<Ticket> fixedTickets){
		List<String> batchList = new ArrayList<String>();
		int memberCount = findOwnerList().size();
		List<String> typeList = findTypeList();
//		List<Integer> categoryCount = new ArrayList<Integer>();
		Map<String, Integer> typeCountMap = new HashMap<String,Integer>();
		for(String type : typeList){
			typeCountMap.put(type, 0);
		}
		for (Ticket ticket : fixedTickets) {
			if(typeCountMap.get(ticket.getType())!=null){
				int count = typeCountMap.get(ticket.getType());
				typeCountMap.put(ticket.getType(), count+1);
			}
		}
		for(String type : typeList){
			int allTicketCount = countTicket(type);
			int count = typeCountMap.get(type);
			if(allTicketCount == 0 || count == 0){
				continue;
			}
			float parcentage = (float)count/((float)allTicketCount / (float)memberCount);
//			batchList.add(""+typeList.size()+" "+allTicketCount+" "+count+" "+memberCount+" "+parcentage);
			if(parcentage >= 0.75 && parcentage <= 1.25){
				batchList.add(type);
			}
		}
		return batchList;
	}
	private List<String> findOwnerList() {
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
	private List<String> findTypeList() {
		List<String> typeList = new ArrayList<>();
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject());
		for (DBObject o : cursor) {
			String type = o.get("type").toString();
			if(!typeList.contains(type)){
				typeList.add(type);
			}
		}
		return typeList;
	}
	private Integer countTicket(String type) {
		DBCursor cursor = TICKET_COLLECTION.find(new BasicDBObject("type",type).append("resolution", "fixed"));
		return cursor.length();
	}

}
