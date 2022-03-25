package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Order;
import model.SaleLineItem;

public class OrderDb implements OrderDbIF {
	
	private String INSERT_ORDER_SQL = "INSERT INTO SaleOrder (_date, deliveryStatus, deliveryDate, employeeId, customerId) "+
									  "values (?, ?, ?, ?, ?);";
	private String INSERT_SALELINEITEM_SQL = "INSERT INTO SaleLineItem (saleOrderNo, productId, amount) "+
									  		 "values (?, ?, ?);";
	
	private PreparedStatement ps_insert;
	
	public OrderDb() throws DataAccessException{
		
	}

	@Override
	public boolean insert(Order o) throws DataAccessException {
		boolean res = true;
		
		// Getting the connection
		DBConnection dbc = DBConnection.getInstance();
		Connection con = dbc.getConnection();
		
		// Inserting the order into the Database
		try {
			// Formatting the prepared sql query
			// Second parameter allows for retrieval of the inserted id/orderNo
			ps_insert = con.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps_insert.setDate(1, Date.valueOf(LocalDate.now()));
			ps_insert.setBoolean(2, false);
			ps_insert.setDate(3, null);
			ps_insert.setInt(4, o.getEmployee().getId());
			ps_insert.setInt(5, o.getCustomer().getId());
			
			// Executing the insert
			ps_insert.executeUpdate();
			
			// Getting the id/orderNo and set it in the Object
			ResultSet generatedKeys = ps_insert.getGeneratedKeys();
			if(generatedKeys.next()) o.setOrderNo(generatedKeys.getInt(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Inserting the SaleLineItems
		try {
			List<SaleLineItem> sli = o.getSaleLineItems();
			
			// Looping all the SaleLineItems and executing queries
			for (SaleLineItem saleLineItem : sli) {
				ps_insert = con.prepareStatement(INSERT_SALELINEITEM_SQL);
				
				ps_insert.setInt(1, o.getOrderNo());
				ps_insert.setInt(2, saleLineItem.getProduct().getId());
				ps_insert.setInt(3, saleLineItem.getAmount());
				
				ps_insert.execute();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return res;
	}

}
