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
	
	private Order order;
	
	public Reservation() {
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
	
	public void setOrder(Order o) {
		if(o != null) {
			this.order = o;
		}
	}
	
	public Order getOrder(){
		return this.order;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + guestCount;
		result = prime * result + id;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((tables == null) ? 0 : tables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (guestCount != other.guestCount)
			return false;
		if (id != other.id)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (tables == null) {
			if (other.tables != null)
				return false;
		} else if (!tables.equals(other.tables))
			return false;
		return true;
	}
	
	
}
