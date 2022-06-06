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
	public Reservation currentReservation;

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

	public List<LocalTime> setGuestCountAndDate(int quantity, LocalDate date) throws DataAccessException {
		List<LocalTime> res = null;

		currentReservation.setGuestCount(quantity);
		/*
		 * LocalTime.MIN sat som default værdi i LocalTime delen af LocalDateTime
		 */
		currentReservation.setDate(LocalDateTime.of(date, LocalTime.MIN));

		res = findAvailableTimes();

		return res;
	}

	public void setStartingTime(LocalTime time) {
		LocalDate date = currentReservation.getDate().toLocalDate();

		currentReservation.setDate(LocalDateTime.of(date, time));
	}

	public Order addProduct(String name, int quantity) throws DataAccessException {
		Order res = null;

		res = orderCtrl.addProduct(name, quantity);

		return res;
	}

	public void setOrder(Order order) {
		currentReservation.setOrder(order);
	}

	public void setNote(String note) {
		currentReservation.setNote(note);
	}

	public void addTable(Table t) {
		currentReservation.addTable(t);
	}

	public Reservation endReservation(String custName, String custPhoneNo, String custEmail)
			throws DataAccessException {
		currentReservation.setCustomer(customerCtrl.createCustomer(custName, custPhoneNo, custEmail));

		delegateTablesToReservation();

		reservationDB.saveReservation(currentReservation);

		return currentReservation;
	}
	
	public List<Reservation> findAll() throws DataAccessException {
		return reservationDB.findAll();
	}

	/*
	 * LOCAL FUNCTIONS
	 */

	private void calculateAvailableSeats(List<Integer> seatsInTimeSlot, List<LocalTime> timeSlots,
			List<Table> allTables, List<Reservation> reservationsForTheDay) {
		// COMPUTE max number of seats from tables
		int maxNumberOfSeats = allTables.stream()
				.map((t) -> t.getSeats()) // Transform Table object to seats as integer
				.reduce(0, (a, b) -> a + b); // Sum all the seats for a max number of seats

		// Loop all timeslots and find number of available seats for each one
		for (LocalTime timeSlot : timeSlots) {
			// Variable to store the available seats for each timeslot
			int seatsInUse = 0;
			// For each timeslot check if the reservations for the day overlaps the timeslot
			for (Reservation reservation : reservationsForTheDay) {
				// If timeslot is overlapping
				if (isOverlapping(reservation, timeSlot)) {
					// Add all the reservation's tables' seats
					for (Table table : reservation.getTables()) {
						seatsInUse += table.getSeats();
					}
				}
			}
			/*
			 * Store the available seats in a list parallel to
			 * the timeslots' list so timeSlots[i] has seatsInTimeSlot[i] available seats.
			 */
			seatsInTimeSlot.add(maxNumberOfSeats - seatsInUse);
		}

	}

	private void initializeTimeSlots(List<LocalTime> timeSlots) {
		// Using a local variable to store a LocalTime
		LocalTime counter = openingTimeWeekday;
		
		/*
		 * While the counter is before closing time minus a timeslot's duration,
		 * add the timeslot to a list, so when end up with a list of timeslots to choose.
		 * The closing time gets reduced since we don't want to 
		 * be able to book so we exceed the closing time.
		 */
		while (counter.isBefore(closingTimeWeekday.minus(TIMESLOT_DURATION_MINUTES - 1, ChronoUnit.MINUTES))) {
			timeSlots.add(counter);
			counter = counter.plus(TIMESLOT_INTERVAL_MINUTES, ChronoUnit.MINUTES);
		}

	}

	private Table getBiggestTable(List<Table> availableTables, int guestCountToFill) {
		// Take the first available table
		Table res = availableTables.get(0);

		// Loop all tables through to find the biggest table
		for (Table table : availableTables) {
			// Check to see if a table is bigger AND not too big for the guest count.
			if (table.getSeats() > res.getSeats() && table.getSeats() <= guestCountToFill) {
				res = table;
			}
		}
		return res;
	}

	private List<Table> availableTables(List<Table> tables, LocalTime timeToCheck) throws DataAccessException {
		List<Table> res = new ArrayList<>();
		// Get reservations for the day
		List<Reservation> reservationsForTheDay = reservationDB
				.findReservationsByDate(currentReservation.getDate().toLocalDate());

		// Remove tables from the list, if they're in a reservation overlapping the timeToCheck
		for (Reservation r : reservationsForTheDay) {
			if (isOverlapping(r, timeToCheck)) {
				for (Table t : r.getTables()) {
					tables.remove(t);
				}
			}

		}
		res = tables;
		return res;
	}
	
	// To check if reservation is overlapping with timeslot
	private boolean isOverlapping(Reservation reservation, LocalTime timeToCheck) {
		boolean res = false;
		// Define start and end time for the reservation
		LocalTime start = reservation.getDate().toLocalTime();
		LocalTime end = start.plus(TIMESLOT_DURATION_MINUTES, ChronoUnit.MINUTES);
		
		/*
		 * IF the time to check is before reservation end time
		 * AND after reservation start-timeslotDuration
		 * the reservation is overlapping with the time.
		 */
		if (timeToCheck.isBefore(end) && timeToCheck
				.isAfter(start.minus(TIMESLOT_DURATION_MINUTES - 1, ChronoUnit.MINUTES))) {
			res = true;
		}
		return res;
	}

	// Assign tables to the current reservation
	private void delegateTablesToReservation() throws DataAccessException {
		List<Table> availableTables = availableTables(tableCtrl.getTables(),
				currentReservation.getDate().toLocalTime());

		// Get the guest count so we know how many seats we need to fill
		int guestCountToFill = currentReservation.getGuestCount();
		
		// Add tables to the reservation until the the count is 0
		while (guestCountToFill > 0) {
			Table biggestTable = getBiggestTable(availableTables, guestCountToFill);
			currentReservation.addTable(biggestTable);
			availableTables.remove(biggestTable);
			guestCountToFill -= biggestTable.getSeats();
		}
	}

	// Find available times from the set date and guest count
	private List<LocalTime> findAvailableTimes() throws DataAccessException {
		List<LocalTime> res = null;

		if (currentReservation.getDate() != null) {
			res = new ArrayList<>();
			// Getting all reservation for the day
			List<Reservation> reservationsForTheDay = reservationDB
					.findReservationsByDate(currentReservation.getDate().toLocalDate());
			// Getting ALL tables in the db
			List<Table> allTables = tableCtrl.getTables();

			// set up TIMESLOTS to check for and corresponding available seats
			List<LocalTime> timeSlots = new ArrayList<>();
			initializeTimeSlots(timeSlots);
			List<Integer> seatsInTimeSlot = new ArrayList<>();
			calculateAvailableSeats(seatsInTimeSlot, timeSlots, allTables, reservationsForTheDay);

			// adding timeslot to res where availableSeats >= reservation.seats
			for (int i = 0; i < timeSlots.size(); i++) {
				// for testing
				System.out.println(seatsInTimeSlot.get(i) + " " + timeSlots.get(i));
				// doing the adding
				if (seatsInTimeSlot.get(i) >= currentReservation.getGuestCount()) {
					res.add(timeSlots.get(i));
				}
			}
		}

		return res;
	}
	
}
