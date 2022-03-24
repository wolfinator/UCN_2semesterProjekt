package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Customer;
import model.Employee;

public class CustomerDB implements CustomerDbIF {
	// query: select personId, isClub,
	// person: Id, fname, lname, phoneNo, email, personType, addressId

	private static final String FIND_BY_PHONENO_SQL = "select isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city  "
			+ "from Person p, CityZipCode c, Address a, Customer cu " + "where personType = 1 " + "and personId = p.id "
			+ "and p.addressId = a.id " + "and a.zipcode = c.zipcode" + "and p.phoneNo = ? ";
	private PreparedStatement ps_findByPhoneNo;

	public CustomerDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps_findByPhoneNo = con.prepareStatement(FIND_BY_PHONENO_SQL);

		} catch (SQLException e) {
			e.printStackTrace(); // TODO not Finished
		}

	}

	public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
		Customer res = null;
		try {
			ps_findByPhoneNo.setString(1, phoneNo);	
			ResultSet rs = ps_findByPhoneNo.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// Teknisk set ikke brugt endnu
	private List<Customer> buildObjects(ResultSet rs) throws DataAccessException {
		List<Customer> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer customer = buildObject(rs);
				res.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1       2   3      4      5        6      7           8            9           10       11
	// isClub, id, fname, lname, phoneNo, email, personType, houseNumber, streetName, zipcode, city
	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer = new Customer();
		try {
			customer.setIsClub(rs.getBoolean(1));
			customer.setId(rs.getInt(2));
			customer.setName(rs.getString(3) + (rs.getString(4)));
			customer.setPhoneNo(rs.getString(5));
			customer.setEmail(rs.getString(6));
			customer.setAddress(rs.getString(9) + ' ' + rs.getString(8));
			customer.setCity(rs.getString(11));
			customer.setZipcode(rs.getString(10));
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return customer;
	}
	
	/*
	 * Ikke brug for implementation pt
	 * 
	public Customer findByPersonID(int personID) throws DataAccessException {
		Customer res = null;
		return res;
	}

	public List<Customer> findCustomer() throws DataAccessException {
		List<Customer> res = new ArrayList<>();

		return res;
	}

	public Customer findByPersonID(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Customer insert(Customer customer) throws DataAccessException {
		Customer res = null;
		// Tror ikke vi skal fokusere på det her pt.
		return res;
	}
	*/
	
}
