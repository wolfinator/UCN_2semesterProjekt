package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
	private Customer customer;
	private int id;
	private int guestCount;
	private LocalDateTime date;
	private String note;
	
	private List<Order> orders;
	
	public Reservation() {
		orders = new ArrayList<>();
			
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public void addOrder(Order o) {
		if(o != null) {
			orders.add(o);
		}
	
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
}
