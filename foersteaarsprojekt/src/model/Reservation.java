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
	
	public void addOrder(Order o) {
		if(o != null) {
			orders.add(o);
		}
	
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	
	public int getGuestCount() {
		return this.guestCount;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
	public Customer getCustomer() {
		return this.customer;
	}
}
