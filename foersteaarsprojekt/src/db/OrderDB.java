package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import ctrl.DataAccessException;
import model.Order;
import model.OrderLineItem;
import model.Reservation;

public class OrderDB implements OrderDBIF {
	private Connection con;
	private static final String SAVE_ORDER_SQL = "INSERT INTO _Order(totalPrice, status, tableId, reservationId) "
			+ "values (?,?,?,?);";
	private String INSERT_ORDERLINEITEM_SQL = "INSERT INTO OrderLineItem (OrderNo, productId, quantity,)"
			+ " values (?,?,?);";

	private PreparedStatement ps_saveOrder;
	private PreparedStatement ps_saveOrderItem;

	public OrderDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			con = DBConnection.getInstance().getConnection();
			ps_saveOrder = con.prepareStatement(SAVE_ORDER_SQL);
			ps_saveOrderItem = con.prepareStatement(INSERT_ORDERLINEITEM_SQL);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		if(generatedKeys.next()) o.setId(generatedKeys.getInt(1));
		
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}		
		
	
	//Inserting OrderLineItem
		try {
		List<OrderLineItem> oli = o.getOrderLineItem();
		//Looping all orderLineItems, and executing queries
		for(OrderLineItem orderLineItem : oli) {
			ps_saveOrderItem.setInt(1, o.getOrderNo());
			ps_saveOrderItem.setInt(2, orderLineItem.getProduct().getId());
			ps_saveOrderItem.setInt(3, orderLineItem.getQuantity());
			
			ps_saveOrderItem.execute();
		}
		
		}
		
	  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	
	return res;

	}
}
