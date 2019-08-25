package model;

import java.util.HashMap;

public class Order {

	private HashMap<Integer, Ticket> ticketList;

	public Order() {
		this.ticketList = new HashMap<Integer, Ticket>();
	}

	public HashMap<Integer, Ticket> getTicketList() {
		return ticketList;
	}

}
