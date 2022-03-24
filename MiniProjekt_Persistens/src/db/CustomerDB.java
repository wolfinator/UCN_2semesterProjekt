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

	private CustomerDbIF customerDB;
	// query: select personId, isClub,
	// person: Id, fname, lname, phoneNo, email, personType, addressId

	private static final String FIND_CUSTOMER_Q = "select personId, isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city  " + 
												  "from Person p, CityZipCode c, Address a, Customer cu " + 
												  "where personType = 1 " +
												  "and personId = p.id " +
												  "and p.addressId = a.id " +
												  "and a.zipcode = c.zipcode" +
												  "and p.phoneNo = ? ";
	private PreparedStatement findCustomerPS;

	private static final String FIND_BY_CUSTOMER_OR_NAME_Q = FIND_CUSTOMER_Q + "where customer like? or name like?";
	private PreparedStatement findByCustomerOrNamePS;

	private static final String FIND_BY_PERSONID_Q = FIND_CUSTOMER_Q + "where personId = ?";
	private PreparedStatement findByPersonIdPS;

	private static final String INSERT_Q = "insert into customer (personId, isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private PreparedStatement insertPS;

	public CustomerDB(CustomerDbIF customerDB) throws DataAccessException {
		this.customerDB = customerDB;
		init();
	}

	public CustomerDB() throws DataAccessException {
		customerDB = new CustomerDB(this);
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findCustomerPS = con.prepareStatement(FIND_CUSTOMER_Q);
			findByCustomerOrNamePS = con.prepareStatement(FIND_BY_CUSTOMER_OR_NAME_Q);
			findByPersonIdPS = con.prepareStatement(FIND_BY_PERSONID_Q);
			insertPS = con.prepareStatement(INSERT_Q);

		} catch (SQLException e) {
			e.printStackTrace(); // TODO not Finished
		}

	}

	public List<Customer> findByPhoneNo(String phoneNo, boolean fullAssociation) throws DataAccessException {
		List<Customer> res = null;
		try {
			findByCustomerOrNamePS.setString(1, phoneNo);
			if (phoneNo != null && phoneNo.length() ==1) {
			} else {
				findByCustomerOrNamePS.setString(2, "");
			}
			findByCustomerOrNamePS.setString(3,phoneNo);
			ResultSet rs = findByCustomerOrNamePS.executeQuery();
			res = buildObjects(rs, fullAssociation);	
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return res;
	}

	private List<Customer> buildObjects(ResultSet rs, boolean fullAssociation) {
		List<Customer> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer currCustomer = buildObject(rs, fullAssociation);
				res.add(currCustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Customer buildObject(ResultSet rs, boolean fullAssociation) {
		Customer currCustomer = new Customer();
		try {
			currCustomer.setPersonID(rs.getString("personId"));
			currCustomer.setIsClub(rs.getString("isClub"));
			currCustomer.setP.Id(rs.getString("p.Id"));
			currCustomer.setFname(rs.getString("fname"));
			currCustomer.setLname(rs.getString("Lname"));
			currCustomer.setphoneNo(rs.getString("phoneNo"));
			currCustomer.setEmail(rs.getString("email"));
			currCustomer.setPersonType(rs.getString("personType"));
			currCustomer.sethouseNumber(rs.getString("houseNumber"));
			currCustomer.setStreetName(rs.getString("streetName"));
			currCustomer.setc.zipcode(rs.getString("c.zipcode"));
			currCustomer.setCity(rs.getString("city"));
			

		}
		return null;
	}

	Customer findBypersonID(int personID , boolean b) throws DataAccessException {
		return 
	}

	List<Employee> findCustomer(boolean fullAssociation) throws DataAccessException;

	public Customer insert(Customer customer) throws DataAccessException;

	public Customer findByPersonID(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
