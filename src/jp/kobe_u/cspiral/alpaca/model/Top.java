package jp.kobe_u.cspiral.alpaca.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Top {

	private double TotalProgress;
//	private List<String> TracLog;
	private List<AcceptList> EachAcceptTicket;
	private List<Progress> UCProgress;
	private List<String> OwnerList;


	public double getTotalProgress() {
		return TotalProgress;
	}
	public void setTotalProgress(double totalProgress) {
		TotalProgress = totalProgress;
	}
//	public List<String> getTracLog() {
//		return TracLog;
//	}
//	public void setTracLog(List<String> tracLog) {
//		TracLog = tracLog;
//	}
	public List<AcceptList> getEachAcceptTicket() {
		return EachAcceptTicket;
	}
	public void setEachAcceptTicket(List<AcceptList> eachAcceptTicket) {
		EachAcceptTicket = eachAcceptTicket;
	}
	public List<Progress> getUCProgress() {
		return UCProgress;
	}
	public void setUCProgress(List<Progress> uCProgress) {
		UCProgress = uCProgress;
	}
	public List<String> getOwnerList() {
		return OwnerList;
	}
	public void setOwnerList(List<String> ownerList) {
		OwnerList = ownerList;
	}

}
