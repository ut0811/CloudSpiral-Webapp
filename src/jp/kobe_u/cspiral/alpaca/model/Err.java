package jp.kobe_u.cspiral.alpaca.model;

import java.util.List;

public class Err {

	private String ErrMsg;
	private List<String> TicketName;
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	public List<String> getTicketName() {
		return TicketName;
	}
	public void setTicketName(List<String> ticketName) {
		TicketName = ticketName;
	}

}
