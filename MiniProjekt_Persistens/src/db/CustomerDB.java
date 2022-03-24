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

	private static final String FIND_CUSTOMER_Q = "select personId, isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city  "
			+ "from Person p, CityZipCode c, Address a, Customer cu " + "where personType = 1 " + "and personId = p.id "
			+ "and p.addressId = a.id " + "and a.zipcode = c.zipcode" + "and p.phoneNo = ? ";
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

	public List<Customer> findByPhoneNo(String phoneNo) throws DataAccessException {
		List<Customer> res = null;
		try {
			findByCustomerOrNamePS.setString(1, phoneNo);
			if (phoneNo != null && phoneNo.length() == 1) {
			} else {
				findByCustomerOrNamePS.setString(2, "");
			}
			findByCustomerOrNamePS.setString(3, phoneNo);
			ResultSet rs = findByCustomerOrNamePS.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private List<Customer> buildObjects(ResultSet rs) {
		List<Customer> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer currCustomer = buildObject(rs);
				res.add(currCustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer Customer = new Customer();
		try {
			Customer.setPersonID(rs.getString("personId"));
			Customer.setIsClub(rs.getString("isClub"));
			Customer.setId(rs.getString("Id"));
			Customer.setFname(rs.getString("fname"));
			Customer.setLname(rs.getString("Lname"));
			Customer.setPhoneNo(rs.getString("phoneNo"));
			Customer.setEmail(rs.getString("email"));
			Customer.setPersonType(rs.getString("personType"));
			Customer.sethouseNumber(rs.getString("houseNumber"));
			Customer.setStreetName(rs.getString("streetName"));
			Customer.setzipcode(rs.getString("zipcode"));
			Customer.setCity(rs.getString("city"));
			}
		}catch(

	SQLException e)
	{
		e.printStackTrace();
	}
		return Customer;

	Customer findBypersonID(int personID) throws DataAccessException {
		return res;
	}

	List<Employee> findCustomer() throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findCustomerPS.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Customer> res = buildObjects(rs);
		return res;
	}

	public Customer findByPersonID(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer insert(Customer customer) throws DataAccessException {
		try {
			insertPS.setString(1, Customer.getPersonId());
			insertPS.setString(2, Customer.getIsClub());
			insertPS.setString(3, Customer.getp.Id());
			insertPS.setString(4, Customer.getFname());
			insertPS.setString(5, Customer.getLname());
			insertPS.setString(6, Customer.getPhoneNo());
			insertPS.setString(7, Customer.getEmail());
			insertPS.setString(8, Customer.getPersonType());
			insertPS.setString(9, Customer.getHouseNumber());
			insertPS.setString(10, Customer.getStreetName());
			insertPS.setString(11, Customer.getC.zipcode());
			insertPS.setString(12, Customer.getCity());
			}
	}catch(

	SQLException e)
	{
		e.printStackTrace();
	}

	return res;
}
