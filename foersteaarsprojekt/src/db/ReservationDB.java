package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Customer;
import model.Reservation;

public class ReservationDB implements ReservationDBIF {
	
	private Connection con;

	private final String INSERT_RESERVATION_SQL = "";
	private final String FIND_BY_DATE = "select id, guestCount, dateTimeslot, note, customerId from Reservation "
			+ "where dateTimeslot = ?;";
	private final String FIND_ALL = "";

	private PreparedStatement ps_insert;
	private PreparedStatement ps_findByDate;
	private PreparedStatement ps_findAll;

	private OrderDBIF orderDB;
	private CustomerDBIF customerDB;

	public ReservationDB() {
		orderDB = new OrderDB();
	}

	private void init() {
		try {
			con = DBConnection.getInstance().getConnection();
			ps_insert = con.prepareStatement(INSERT_RESERVATION_SQL);
			ps_findByDate = con.prepareStatement(FIND_BY_DATE);
			ps_findAll = con.prepareStatement(FIND_ALL);
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
		
		try {
			ps_findByDate.setDate(1, Date.valueOf(date));
			ResultSet rs = ps_findByDate.executeQuery();
			
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("SQL error in retreiving Reservations by date (findReservationsByDate)", e);
		}
		
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
			
		} catch (SQLException e) {
			throw new DataAccessException("Error in building a reservation object (buildObject())", e);
		}
		
		return res;
	}

	@Override
	public List<LocalDate> findAvailableTimes(List<Reservation> rList, int quantity, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}
