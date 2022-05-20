package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;
import gui.CalendarTimeView;
import gui.ConfirmationView;
import gui.CreateOrderView;
import gui.GuestCountView;
import gui.LocationView;
import model.Order;

public class ReservationUI {

	private LocationView locationView;
	private GuestCountView guestCountView;
	private CalendarTimeView calendarTimeView;
	private CreateOrderView createOrderView;
	private ConfirmationView confirmationView;
	
	private ReservationCtrl reservationCtrl;
	
	public static void main(String[] args) {
		ReservationUI ui = new ReservationUI();
		ui.start();
	}
	
	private void start() {
		confirmationView = new ConfirmationView(this);
		locationView = new LocationView(this, confirmationView);
		guestCountView = new GuestCountView(this, confirmationView);
		calendarTimeView = new CalendarTimeView(this, confirmationView);
		createOrderView = new CreateOrderView(this, confirmationView);
		
		
		locationView.addTransitions(guestCountView);
		guestCountView.addTransitions(locationView, calendarTimeView);
		calendarTimeView.addTransitions(guestCountView, createOrderView);
		createOrderView.addTransitions(calendarTimeView, confirmationView);
		confirmationView.addTransitions(createOrderView, locationView);
		
		try {
			reservationCtrl = new ReservationCtrl();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		locationView.setVisible(true);
		
	}

	public void createReservation() {
		reservationCtrl.createReservation();
		
	}

	public void setStartingTime(LocalTime timeSelected) throws DataAccessException {
		reservationCtrl.setStartingTime(timeSelected);
		
	}
	
	public void setGuestCountAndDate(int guestCount, LocalDate newDate) {
		reservationCtrl.setGuestCountAndDate(guestCount, newDate);
		
	}

	public void endReservation(String cusName, String cusPhoneNo, String cusEmail) throws DataAccessException {
		reservationCtrl.endReservation(cusName, cusPhoneNo, cusEmail);
		
	}

	public List<LocalTime> findAvailableTimes() throws DataAccessException {
		return reservationCtrl.findAvailableTimes();
	}

	public void setOrder(Order order) {
		reservationCtrl.setOrder(order);
		
	}

	
}
