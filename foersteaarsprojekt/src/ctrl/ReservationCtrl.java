package ctrl;

import db.ReservationDB;
import model.Customer;
import model.Order;
import model.Reservation;
import model.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationCtrl {
	
	private CustomerCtrl customerCtrl;
	private OrderCtrl orderCtrl;
	private ReservationDB reservationDB;
	private Reservation currentReservation;
	
	public ReservationCtrl() throws DataAccessException {
		this.customerCtrl = new CustomerCtrl();
		this.orderCtrl = new OrderCtrl();
		this.reservationDB = new ReservationDB();
	}
	
	public Reservation createReservation(Customer customer, Table table, int guestCount) {
		Reservation res = new Reservation();
		res.setCustomer(customer);
		res.addTable(table);
		res.setGuestCount(guestCount);
		
		
		return res;
	}
	
	public List<LocalTime> setGuestCountAndDate(int quantity, LocalDate date) {
		List<LocalTime> res = new ArrayList<>();
		
		currentReservation.setGuestCount(quantity);
		// Ved ikke om LocalDateTime.of() virker med null som LocalTime parameter
		// så nu er det LocalTime.MIN
		currentReservation.setDate(LocalDateTime.of(date, LocalTime.MIN));
		
		//TODO the rest of the annoying function
		
		return res;
	}
	
	public void setStartingTime(LocalTime time) {
		LocalDate date = currentReservation.getDate().toLocalDate();
		
		currentReservation.setDate(LocalDateTime.of(date, time));
	}
	
	public Order addOrder(int productId, int quantity) {
		Order res = new Order();
		
		//TODO stuff with orderCtrl to add the order to the resrevation
		
		//currentReservation.addOrder(res);
		
		return res;
	}
	
	public Reservation endReservation(String custName, String custPhoneNo, String custEmail) throws DataAccessException {				
		currentReservation.setCustomer(
				customerCtrl.createCustomer(custName, custPhoneNo, custEmail));
		
		reservationDB.saveReservation(currentReservation);
		
		return currentReservation;
	}
}
