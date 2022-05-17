package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
	private Customer customer;
	private int id;
	private int guestCount;
	private List<Table> tables;
	private LocalDateTime date;
	private String note;
	
	private List<Order> orders;
	
	public Reservation() {
		orders = new ArrayList<>();
		tables = new ArrayList<>();
	}
	
	public void addTable(Table t) {
		if(t != null) {
			tables.add(t);
		}
	}
	
	public List<Table> getTables(){
		return tables;
	}
	
	public void addOrder(Order o) {
		if(o != null) {
			orders.add(o);
		}
	
	}
	
	public List<Order> getOrders(){
		return this.orders;
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
	
	public void setStartingTime(LocalTime time) {
		LocalDate date = this.date.toLocalDate();
		this.date = LocalDateTime.of(date, time);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return this.note;
	}
}
