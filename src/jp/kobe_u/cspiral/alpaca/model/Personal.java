package jp.kobe_u.cspiral.alpaca.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Personal{

	private List<Ticket> AcceptTicket;
	private List<Ticket> FixedTicket;
//	private List<TracLog> TracLog;
//	private List<String> TracLog;
	private List<String> batchList;
	private List<Est> EstErrList;
	private List<Err> ErrList;
	private String Exp;
	public List<Ticket> getAcceptTicket() {
		return AcceptTicket;
	}
	public void setAcceptTicket(List<Ticket> acceptTicket) {
		AcceptTicket = acceptTicket;
	}
	public List<Ticket> getFixedTicket() {
		return FixedTicket;
	}
	public void setFixedTicket(List<Ticket> fixedTicket) {
		FixedTicket = fixedTicket;
	}
//	public List<TracLog> getTracLog() {
//		return TracLog;
//	}
//	public void setTracLog(List<TracLog> tracLog) {
//		TracLog = tracLog;
//	}
//	public List<String> getTracLog() {
//		return TracLog;
//	}
//	public void setTracLog(List<String> tracLog) {
//		TracLog = tracLog;
//	}
	public List<Est> getEstErrList() {
		return EstErrList;
	}
	public void setEstErrList(List<Est> estErrList) {
		EstErrList = estErrList;
	}
	public List<Err> getErrList() {
		return ErrList;
	}
	public void setErrList(List<Err> errList) {
		ErrList = errList;
	}
	public String getExp() {
		return Exp;
	}
	public void setExp(String exp) {
		Exp = exp;
	}
	public List<String> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<String> batchList) {
		this.batchList = batchList;
	}

}
