package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Order;
import model.SaleLineItem;

public class OrderDb implements OrderDbIF {
	
	private String INSERT_ORDER_SQL = "INSERT INTO SaleOrder (orderNo, _date, deliveryStatus, deliveryDate, employeeId, customerId) "+
									  "values (?, ?, ?, ?, ?, ?);";
	private String INSERT_SALELINEITEM_SQL = "INSERT INTO SaleLineItem (saleOrderId, productId, amount) "+
									  		 "values (?, ?, ?);";
	private String FIND_BY_ORDERNO_SQL = "";
	private String FIND_ALL_SQL = "";
	
	private PreparedStatement ps_insert;
	private PreparedStatement ps_findByOrderNo;
	private PreparedStatement ps_findAll;

	@Override
	public Order insert(Order o) throws DataAccessException {
		DBConnection dbc = DBConnection.getInstance();
		Connection con = dbc.getConnection();
		
		// Inserting the order into the Database
		try {			
			ps_insert = con.prepareStatement(INSERT_ORDER_SQL);
			ps_insert.setString(1, o.getOrderNo());
			ps_insert.setDate(2, Date.valueOf(LocalDate.now()));
			ps_insert.setBoolean(3, false);
			ps_insert.setDate(4, null);
			ps_insert.setInt(5, o.getEmployee().getEmployeeId());
			ps_insert.setInt(6, o.getCustomer().getCustomerID());
			
			ps_insert.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Inserting the SaleLineItems
		try {
			List<SaleLineItem> sli = o.getSaleLineItems();
			for (SaleLineItem saleLineItem : sli) {
				ps_insert = con.prepareStatement(INSERT_SALELINEITEM_SQL);
				
				ps_insert.setInt(1, o.getId());
				ps_insert.setInt(2, saleLineItem.getProduct().getId());
				ps_insert.setInt(3, saleLineItem.getAmount());
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return null;
	}

	@Override
	public Order findByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Order> buildObjects(ResultSet rs) throws SQLException {
		List<Order> res = new ArrayList<>();
		
		while(rs.next()) {
			Order order = buildObject(rs);
			res.add(order);
		}
		
		return res;
	}
	
	public Order buildObject(ResultSet rs) throws SQLException {
		Order res = new Order();
		
		res.setCustomer(null); // TODO full association?
		res.setEmployee(null);
		
		res.setId(rs.getInt(1));
		res.setOrderNo(rs.getString(2));
		res.setDate(rs.getDate(3).toLocalDate());
		res.setDeliveryStatus(rs.getBoolean(4));
		res.setDeliveryDate(rs.getDate(5).toLocalDate());
				
		return res;
	}

}