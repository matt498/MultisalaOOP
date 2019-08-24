package model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
	private Integer id;
	private List<Seat> seatList;
	
	public Sala() {
		seatList = new ArrayList<Seat>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
	
	
	
}
