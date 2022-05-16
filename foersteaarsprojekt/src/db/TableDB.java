package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Reservation;
import model.Table;

public class TableDB implements TableDBIF{
	
	private DBConnection dbc;
	private Connection con;
	
	private String SAVE_TO_RESERVATION_SQL = "insert into ReservationTable (reservationId, tableId) values "
			+ "(?, ?);";
	private String FIND_ALL_SQL = "select id, tableNo, seats from _Table;";
	
	private PreparedStatement ps_saveToReservation;
	private PreparedStatement ps_findAll;

	public TableDB() {
		init();
	}
	
	private void init() {
		try {
			dbc = DBConnection.getInstance();
			con = dbc.getConnection();
			
			ps_saveToReservation = con.prepareStatement(SAVE_TO_RESERVATION_SQL);
			ps_findAll = con.prepareStatement(FIND_ALL_SQL);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Table> getTables() throws DataAccessException {
		List<Table> res = null;
		
		try {
			ResultSet rs = ps_findAll.executeQuery();
			
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Error in retrieving tables from db", e);
		}
		
		return res;
	}
	
	@Override
	public boolean saveTableToReservation(Table t, Reservation r) throws DataAccessException{
		boolean res = false;
		
		try {
			ps_saveToReservation.setInt(1, r.getId());
			ps_saveToReservation.setInt(2, t.getId());
			
			res = ps_saveToReservation.execute();
		} catch (SQLException e) {
			throw new DataAccessException("Error in saving table to reservation", e);
		}
		
		return res;
	}
	
	private List<Table> buildObjects(ResultSet rs) throws DataAccessException{
		List<Table> res = null;
		try {
			res = new ArrayList<>();
			while(rs.next()) {
				Table table = buildObject(rs);
				res.add(table);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Error in building table objects from resultset" , e);
		}
		return res;
	}

	private Table buildObject(ResultSet rs) throws DataAccessException {
		Table res = null;
		try {
			res = new Table();
			res.setId(rs.getInt("id"));
			res.setSeats(rs.getInt("seats"));
			res.setTableNo(rs.getInt("tableNo"));
		} catch (SQLException e) {
			throw new DataAccessException("Error building table object from resultset", e);
		}
		
		return res;
	}

}
