package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private List<Ticket> ticketList;

	public Order() {
		this.ticketList = new ArrayList<Ticket>();
	}

	public Integer totalPrice() {
		
		Integer tot = 0;
		
		for (Ticket ticket : ticketList) {
			tot += ticket.getPrice();
		}

		return tot;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}
	
	
	
}
