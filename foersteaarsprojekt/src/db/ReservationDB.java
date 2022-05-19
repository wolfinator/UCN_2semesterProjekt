package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Customer;
import model.Order;
import model.Reservation;
import model.Table;

public class ReservationDB implements ReservationDBIF {
	
	private DBConnection dbc;
	private Connection con;

	private final String FIND_BY_DATE_SQL = "select * from Reservation "+
			"where dateTimeslot >= cast(? as datetime)"+
			"and dateTimeslot < cast(? as datetime);";
	private final String FIND_ALL_SQL = "select 1"; // TODO make it
	private final String SAVE_SQL = "insert into Reservation(guestCount, dateTimeslot, note, customerId) "
			+ "values(?, ?, ?, ?);";

	private PreparedStatement ps_findByDate;
	private PreparedStatement ps_findAll;
	private PreparedStatement ps_save;

	private OrderDBIF orderDB;
	private CustomerDBIF customerDB;
	private TableDBIF tableDB;

	public ReservationDB() throws DataAccessException {
		init();
	}

	private void init() {
		try {
			orderDB = new OrderDB();
			customerDB = new CustomerDB();
			tableDB = new TableDB();
			
			dbc = DBConnection.getInstance();
			con = dbc.getConnection();
			ps_findByDate = con.prepareStatement(FIND_BY_DATE_SQL);
			ps_findAll = con.prepareStatement(FIND_ALL_SQL);
			ps_save = con.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Reservation> findReservationsByDate(LocalDate date) throws DataAccessException {
		List<Reservation> res;
		LocalDate nextDay = date.plus(1, ChronoUnit.DAYS);
		
		try {
			ps_findByDate.setDate(1, Date.valueOf(date));
			ps_findByDate.setDate(2, Date.valueOf(nextDay));
			ResultSet rs = ps_findByDate.executeQuery();
			
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("SQL error in retreiving Reservations by date (findReservationsByDate)", e);
		}
		
		return res;
	}

	@Override
	public boolean saveReservation(Reservation reservation) throws DataAccessException {
		boolean res = false;
		
		// Gemme Customer
		try {
			int customerId = customerDB.saveCustomer(reservation.getCustomer());
			reservation.getCustomer().setId(customerId);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error saving Customer", e);
		}
		
		// Gemme Reservation

		try {
			ps_save.setInt(1, reservation.getGuestCount());
			ps_save.setObject(2, java.sql.Timestamp.valueOf(reservation.getDate()));
			ps_save.setString(3, reservation.getNote());
			ps_save.setInt(4, reservation.getCustomer().getId());
			res = ps_save.executeUpdate() > 0; //TODO idk about this solution
			
			
			
			ResultSet generatedKeys = ps_save.getGeneratedKeys();
			if(generatedKeys.next()) {
				reservation.setId(generatedKeys.getInt(1));
			} else {
				// Maybe throw exception
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Error saving Reservation", e);
		}
		
		dbc.startTransaction();
		// Gemme tables i ReservationTable join tabellen
		try {
			for(Table table : reservation.getTables()) {
				tableDB.saveTableToReservation(table, reservation);
			}
		} catch (DataAccessException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("Error in saving tables to reservation", e);
		}
		
		// Gemme Order	
		try {
			List<Order> orders = reservation.getOrders();
			for(Order o : orders) {
				orderDB.saveOrder(o);
			}
		} catch (DataAccessException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("Error saving Orders", e);
		}
		dbc.commitTransaction();
		return res;
	}
	
	private List<Reservation> buildObjects(ResultSet rs) throws DataAccessException {
		List<Reservation> res = new ArrayList<>();
		
		try {
			while(rs.next()) {
				Reservation reservation = buildObject(rs);
				res.add(reservation);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Error in building reservation objects (buildObjects())", e);
		}
		
		return res;
	}

	private Reservation buildObject(ResultSet rs) throws DataAccessException {
		Reservation res = new Reservation();
		
		try {
			res.setId(rs.getInt("id"));
			res.setGuestCount(rs.getInt("guestCount"));
			LocalDateTime ldt = 
					LocalDateTime.of(
							rs.getDate("dateTimeslot").toLocalDate(), 
							rs.getTime("dateTimeslot").toLocalTime());
			res.setDate(ldt);
			Customer customer = customerDB.findById(rs.getInt("customerId"));
			res.setCustomer(customer);
			List<Table> tables = tableDB.getTables(res.getId());
			for(Table t : tables) {
				res.addTable(t);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Error in building a reservation object from resultset(buildObject())", e);
		}
		
		return res;
	}

}
