package ctrl;

import db.ReservationDB;
import model.Order;
import model.Reservation;
import model.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
		orderCtrl.createOrder();
		
		currentReservation = res;
		
		return res;
	}
	
	public List<LocalTime> setGuestCountAndDate(int quantity, LocalDate date) {
		List<LocalTime> res = new ArrayList<>();
		
		currentReservation.setGuestCount(quantity);
		/*
		 * LocalTime.MIN sat som default værdi i LocalTime delen af LocalDateTime
		 */
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
	
	

	public void setStartingTime(LocalTime time) throws DataAccessException {
		LocalDate date = currentReservation.getDate().toLocalDate();
		
		currentReservation.setDate(LocalDateTime.of(date, time));
	}	

	public Order addProduct(int productId, int quantity) throws DataAccessException {
		Order res = null;
		
		res = orderCtrl.addProduct(productId, quantity);
		
		return res;
	}
	
	public void addTable(Table t) {
		currentReservation.addTable(t);
	}
	
	public Reservation endReservation(String custName, String custPhoneNo, String custEmail) throws DataAccessException {				
		currentReservation.setCustomer(
				customerCtrl.createCustomer(custName, custPhoneNo, custEmail));
		
		delegateTablesToReservation();
		
		reservationDB.saveReservation(currentReservation);
		
		return currentReservation;
	}
	
	/*
	 * LOCAL FUNCTIONS
	 */
	
	private void calculateAvailableSeats(List<Integer> seatsInTimeSlot, List<LocalTime> timeSlots, List<Table> allTables, List<Reservation> reservationsForTheDay, int maxNumberOfSeats) {
		// TODO Figure out how it can be negative values
		for(LocalTime timeSlot : timeSlots) {
			int seatsInUse = 0;
			for(Reservation reservation : reservationsForTheDay) {
				LocalTime timeSlotNextInterval = timeSlot.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES);
				LocalTime reservationStartTime = reservation.getDate().toLocalTime();
				LocalTime reservationEndTime = reservation.getDate().toLocalTime()
						.plus(TIMESLOT_DURATION_MINUTES, ChronoUnit.MINUTES);
				
				if(timeSlot.isBefore(reservationEndTime)
						&& timeSlotNextInterval.isAfter(reservationStartTime.minus(TIMESLOT_DURATION_MINUTES-1, ChronoUnit.MINUTES))) {
					
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
	
	private Table getBiggestTable(List<Table> availableTables, int guestCountToFill) {
		Table res = availableTables.get(0);
		
		for (Table table : availableTables) {
			if(table.getSeats() > res.getSeats() && table.getSeats() <= guestCountToFill) {
				res = table;
			}
		}
		return res;
	}

	private List<Table> availableTables(List<Table> tables, LocalTime timeToCheck) throws DataAccessException {
		List<Table> res = new ArrayList<>();
		List<Reservation> reservationsForTheDay = reservationDB.findReservationsByDate(
				currentReservation.getDate()
				.toLocalDate());
		
		// Remove tables from reservations overlapping timeToCheck
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
				&& 
		   timeToCheck.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES)
				.isAfter(reservationStartTime.minus(TIMESLOT_DURATION_MINUTES-1, ChronoUnit.MINUTES))) {
			res = true;
		}
		return res;
	}
	
	private void delegateTablesToReservation() throws DataAccessException {
		List<Table> availableTables = availableTables(tableCtrl.getTables(), currentReservation.getDate().toLocalTime());
		
		int guestCountToFill = currentReservation.getGuestCount();
		while(guestCountToFill > 0) {
			Table biggestTable = getBiggestTable(availableTables, guestCountToFill);
			currentReservation.addTable(biggestTable);
			availableTables.remove(biggestTable);
			guestCountToFill -= biggestTable.getSeats();
		}
	}
}
