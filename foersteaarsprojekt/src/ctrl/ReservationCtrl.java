package ctrl;

import db.ReservationDB;
import model.Customer;
import model.Order;
import model.Reservation;
import model.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReservationCtrl {
	
	private CustomerCtrl customerCtrl;
	private OrderCtrl orderCtrl;
	private TableCtrl tableCtrl;
	private ReservationDB reservationDB;
	private Reservation currentReservation;
	
	private final int TIMESLOT_INTERVAL_MINUTES = 30;
	private final int TIMESLOT_DURATION_MINUTES = 90;
	private final LocalTime openingTimeWeekday = LocalTime.of(15, 0);
	private final LocalTime closingTimeWeekday = LocalTime.of(21, 0);
	
	public ReservationCtrl() throws DataAccessException {
		this.customerCtrl = new CustomerCtrl();
		this.orderCtrl = new OrderCtrl();
		this.tableCtrl = new TableCtrl();
		this.reservationDB = new ReservationDB();
	}
	
	public Reservation createReservation() {
		Reservation res = new Reservation();
		
		return res;
	}
	
	public List<LocalTime> setGuestCountAndDate(int quantity, LocalDate date) {
		List<LocalTime> res = new ArrayList<>();
		
		currentReservation.setGuestCount(quantity);
		// Ved ikke om LocalDateTime.of() virker med null som LocalTime parameter
		// så nu er det LocalTime.MIN
		currentReservation.setDate(LocalDateTime.of(date, LocalTime.MIN));
		
		return res;
	}
	
	public List<LocalTime> findAvailableTimes() throws DataAccessException{
		List<LocalTime> res = null;
		
		if(currentReservation.getDate() != null) {
			res = new ArrayList<>();
			// Getting all reservation for the day
			List<Reservation> reservationsForTheDay = reservationDB.findReservationsByDate(
					currentReservation.getDate()
					.toLocalDate());
			// Getting ALL tables in the db
			List<Table> allTables = tableCtrl.getTables();
			// COMPUTE max number of seats from tables
			int maxNumberOfSeats = allTables.stream()
					.map((t) -> t.getSeats())
					.reduce(0, (a, b) -> a+b);
			// set up TIMESLOTS to check for and corresponding available seats
			List<LocalTime> timeSlots = new ArrayList<>();
			initializeTimeSlots(timeSlots);
			List<Integer> seatsInTimeSlot = new ArrayList<>();
			calculateAvailableSeats(seatsInTimeSlot, timeSlots, allTables, reservationsForTheDay, maxNumberOfSeats);
			
			// adding timeslot to res where availableSeats >= reservation.seats
			for(int i = 0; i < timeSlots.size(); i++) {
				// for testing
				System.out.println(seatsInTimeSlot.get(i) + " " + timeSlots.get(i));
				// doing the adding
				if(seatsInTimeSlot.get(i) >= currentReservation.getGuestCount()) {
					res.add(timeSlots.get(i));
				}
			}
		}
		
		return res;
	}
	
	private void calculateAvailableSeats(List<Integer> seatsInTimeSlot, List<LocalTime> timeSlots, List<Table> allTables, List<Reservation> reservationsForTheDay, int maxNumberOfSeats) {
		for(LocalTime timeSlot : timeSlots) {
			int seatsInUse = 0;
			for(Reservation reservation : reservationsForTheDay) {
				LocalTime timeSlotNextInterval = timeSlot.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES);
				LocalTime reservationStartTime = reservation.getDate().toLocalTime();
				LocalTime reservationEndTime = reservation.getDate().toLocalTime()
						.plus(TIMESLOT_DURATION_MINUTES, ChronoUnit.MINUTES);
				
				if(timeSlot.isBefore(reservationEndTime)
						&& timeSlotNextInterval.isAfter(reservationStartTime)) {
					
					for (Table table : reservation.getTables()) {
						seatsInUse += table.getSeats();
					}
				}
			}
			seatsInTimeSlot.add(maxNumberOfSeats - seatsInUse);
		}
		
	}

	private void initializeTimeSlots(List<LocalTime> timeSlots) {
		LocalTime counter = openingTimeWeekday;
		while(counter.isBefore(closingTimeWeekday.minus(TIMESLOT_DURATION_MINUTES-1, ChronoUnit.MINUTES))) {
			timeSlots.add(counter);
			counter = counter.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES);
		}
		
	}

	public void setStartingTime(LocalTime time) throws DataAccessException {
		LocalDate date = currentReservation.getDate().toLocalDate();
		
		currentReservation.setDate(LocalDateTime.of(date, time));
		delegateTablesToReservation();
	}
	
	private void delegateTablesToReservation() throws DataAccessException {
		List<Table> availableTables = availableTables(tableCtrl.getTables(), currentReservation.getDate().toLocalTime());
		// TODO make it choose bigger tables first from the list
		
		int guestCountToFill = currentReservation.getGuestCount();
		while(guestCountToFill > 0) {
			
		}
	}

	private List<Table> availableTables(List<Table> tables, LocalTime timeToCheck) throws DataAccessException {
		List<Table> res = new ArrayList<>();
		List<Reservation> reservationsForTheDay = reservationDB.findReservationsByDate(
				currentReservation.getDate()
				.toLocalDate());
		for(Reservation r : reservationsForTheDay) {
			if(isOverlapping(r, timeToCheck)) {
				for(Table t : r.getTables()) {
					tables.remove(t);
				}
			}
			
		}
		res = tables;
		return res;
	}
	
	private boolean isOverlapping(Reservation reservation, LocalTime timeToCheck) {
		boolean res = false;
		LocalTime reservationStartTime = reservation.getDate().toLocalTime();
		LocalTime reservationEndTime = reservation.getDate().toLocalTime()
				.plus(TIMESLOT_DURATION_MINUTES, ChronoUnit.MINUTES);
		
		if(timeToCheck.isBefore(reservationEndTime)
				&& timeToCheck.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES)
				.isAfter(reservationStartTime)) {
			res = true;
		}
		return res;
	}

	public Order addOrder(int productId, int quantity) {
		Order res = new Order();
		
		//TODO stuff with orderCtrl to add the order to the resrevation
		
		//currentReservation.addOrder(res);
		
		return res;
	}
	
	public void addTable(Table t) {
		currentReservation.addTable(t);
	}
	
	public Reservation endReservation(String custName, String custPhoneNo, String custEmail) throws DataAccessException {				
		currentReservation.setCustomer(
				customerCtrl.createCustomer(custName, custPhoneNo, custEmail));
		
		reservationDB.saveReservation(currentReservation);
		
		return currentReservation;
	}
}
