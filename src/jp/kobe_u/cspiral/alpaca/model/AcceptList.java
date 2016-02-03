package jp.kobe_u.cspiral.alpaca.model;

import java.util.List;

public class AcceptList {

	private String OwnerName;
	private List<Ticket> AcceptedTicket;

	public AcceptList(String OwnerName, List<Ticket> AcceptedTicket){
		this.OwnerName = OwnerName;
		this.AcceptedTicket = AcceptedTicket;
	}

	public String getOwnerName() {
		return OwnerName;
	}
	public void setOwnerName(String ownerName) {
		OwnerName = ownerName;
	}
	public List<Ticket> getAcceptedTicket() {
		return AcceptedTicket;
	}
	public void setAcceptedTicket(List<Ticket> acceptedTicket) {
		AcceptedTicket = acceptedTicket;
	}
}
