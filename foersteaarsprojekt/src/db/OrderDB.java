package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ctrl.DataAccessException;
import model.Order;
import model.Reservation;

public class OrderDB implements OrderDBIF{
	private Connection con; 
	private static final String SAVE_ORDER_SQL = "select int id, int orderNo, boolean status"; //TODO -> ??
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
	public void saveOrder(Order o, Reservation r) {
		
		
	}
}
