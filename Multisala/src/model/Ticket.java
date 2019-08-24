package model;

public class Ticket {
	
	private Integer id;
	private Seat seat;
	private Integer price;
	
	public Ticket(Seat seat) {
		this.seat = seat;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", price=" + price + "]";
	}
	
}
