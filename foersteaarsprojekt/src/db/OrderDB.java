package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import ctrl.DataAccessException;
import model.Order;
import model.Reservation;

public class OrderDB implements OrderDBIF{
	private Connection con; 
	private static final String SAVE_ORDER_SQL =  "INSERT INTO _Order(totalPrice, status, tableId, reservationId "
			+ "values (?,?,?,?);";
	
	private PreparedStatement ps_saveOrder; 

	public OrderDB() throws DataAccessException {
		init(); 
	}

	private void init() throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			con = DBConnection.getInstance().getConnection(); 
			ps_saveOrder = con.prepareStatement(SAVE_ORDER_SQL); 
		}catch(DataAccessException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveOrder (Order o, Reservation r)  throws DataAccessException {
	boolean res = true;
	
	//Getting connection
	DBConnection dbc = DBConnection.getInstance();
	Connection con = dbc.getConnection();
	
	// Saving order to the database
	try {
		ps_saveOrder = con.prepareStatement(SAVE_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
		ps_saveOrder.setDouble(1, o.getTotalPrice());
		ps_saveOrder.setBoolean(2, false);
		ps_saveOrder.setInt(3, r.getTables().get(0).getId());
		ps_saveOrder.setInt(4, r.getId());
		
		//Execute Save
		ps_saveOrder.executeUpdate();
		
		ResultSet generatedKeys = ps_saveOrder.getGeneratedKeys();
		if(generatedKeys.next()) r.setOrder(o);
		
		
	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	
	return res;

	}
}
