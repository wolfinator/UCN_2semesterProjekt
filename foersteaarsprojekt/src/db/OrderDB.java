package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ctrl.DataAccessException;
import model.Order;
import model.OrderLineItem;
import model.Product;

public class OrderDB implements OrderDBIF{
	private Connection con; 
	
	private static final String SAVE_ORDER_SQL = "select int orderNo, int totalPrice, boolean status, int tableId, int employeeId, int reservationId" 
												+ "values(?,?,?,?,?,?)"; 
	private String INSERT_ORDERLINEITEM_SQL = "INSERT INTO OrderLineItem (orderNo, productId, quantity)" + "values(?,?,?)"; 
	
	private PreparedStatement ps_saveOrder; 
	private PreparedStatement ps_orderLineItem; 

	public OrderDB() throws DataAccessException {
		init(); 
	}

	/* private void init() throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			con = DBConnection.getInstance().getConnection(); 
			ps_saveOrder = con.prepareStatement(SAVE_ORDER_SQL); 
			ps_orderLineItem = con.prepareStatement(INSERT_ORDERLINEITEM_SQL); 
		}catch(DataAccessException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}*/
	public void saveOrder(Order o) {
		// Getting the connection
		DBConnection dbc = DBConnection.getInstance();
		Connection con = dbc.getConnection();
		
		// Inserting the order into the Database
				try {
					// Formatting the prepared sql query
					ps_saveOrder = con.prepareStatement(SAVE_ORDER_SQL, Statement.RETURN_GENERATED_KEYS); 
					ps_saveOrder.setInt(1, o.getOrderNo());
					ps_saveOrder.setInt(2, o.getTotalPrice());
					ps_saveOrder.setBoolean(3, false);
					ps_saveOrder.setInt(4, o);
					
		
		
		// Inserting the OrderLineItems
		try {
			List<OrderLineItem> oli = o.getOrderLineItem();
			
			// Looping all the OrderLineItems and executing queries
			for (OrderLineItem orderLineItem : oli) {
				ps_orderLineItem = con.prepareStatement(INSERT_ORDERLINEITEM_SQL);
				
				ps_orderLineItem.setInt(1, o.getOrderNo());
				ps_orderLineItem.setInt(2, orderLineItem.getProduct().getId());
				ps_orderLineItem.setInt(3, orderLineItem.getQuantity());
				
				ps_orderLineItem.execute();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return res;
	}
}
