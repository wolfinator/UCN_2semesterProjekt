package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ctrl.DataAccessException;
import model.Customer;

public class CustomerDB implements CustomerDBIF {
	private DBConnection dbc;
	
	private static final String FIND_BY_ID_SQL = "select firstname, lastname, phoneNo, email,"
			+ " addressId, id from Customer "+ "where id = ?;";
	private static final String SAVE_SQL = "insert into Customer (firstname, lastname, phoneNo, email) values (?,?,?,?)";
	
	private PreparedStatement ps_findById;
	private PreparedStatement ps_saveId;

	public CustomerDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		dbc = DBConnection.getInstance();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps_findById = con.prepareStatement(FIND_BY_ID_SQL);
			ps_saveId = con.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace(); // TODO not Finished
		}

	}

	@Override
	public Customer findById(int id) throws DataAccessException {
		Customer res = null;
		try {
			ps_findById.setInt(1, id);
			ResultSet rs = ps_findById.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
	Customer customer = new Customer();
	try {
		customer.setName(rs.getString(1) + " " + (rs.getString(2)));
		customer.setPhoneNo(rs.getString(3));
		customer.setEmail(rs.getString(4));
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return customer;
	}

	@Override
	public int saveCustomer(Customer customer) throws DataAccessException {
		int customerId = -1;

		try {
			ps_saveId.setString(1, customer.getFirstName());
			ps_saveId.setString(2, customer.getLastName());
			ps_saveId.setString(3, customer.getPhoneNo());
			ps_saveId.setString(4, customer.getEmail());
			ps_saveId.executeUpdate();
			
			ResultSet generatedKeys = ps_saveId.getGeneratedKeys();
			if(generatedKeys.next()) customerId = generatedKeys.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}	
		
		return customerId;
	}

}
;