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

	private static final String FIND_CUSTOMER_BYPHONENO_Q = "select isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city  "
			+ "from Person p, CityZipCode c, Address a, Customer cu " + "where personType = 1 " + "and personId = p.id "
			+ "and p.addressId = a.id " + "and a.zipcode = c.zipcode" + "and p.phoneNo = ? ";
	private PreparedStatement ps_findByPhoneNo;

	private static final String FIND_BY_CUSTOMER_OR_NAME_Q = "where customer like? or name like?";
	private PreparedStatement findByCustomerOrNamePS;

	private static final String INSERT_Q = "insert into customer (personId, isClub, p.Id, fname, lname, phoneNo, email, personType, houseNumber, streetName, c.zipcode, city) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps_findByPhoneNo = con.prepareStatement(FIND_CUSTOMER_BYPHONENO_Q);
			findByCustomerOrNamePS = con.prepareStatement(FIND_BY_CUSTOMER_OR_NAME_Q);
			insertPS = con.prepareStatement(INSERT_Q);

		} catch (SQLException e) {
			e.printStackTrace(); // TODO not Finished
		}

	}

	public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
		Customer res = null;
		try {
			ps_findByPhoneNo.setString(1, phoneNo);
			
			if (phoneNo != null && phoneNo.length() == 1) {
				// Nothing interesting happens.
			} else {
				findByCustomerOrNamePS.setString(2, "");
			}

			ResultSet rs = ps_findByPhoneNo.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

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

	// isClub, p.id,fname, lname, phoneNo,email,personType,houseNumber, streetName, c.Zipcode, city
	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer = new Customer();
		try {
			customer.setIsClub(rs.getBoolean(1));
			customer.setId(rs.getInt(2));
			customer.setName(rs.getString(3) + (rs.getString(4)));
			customer.setPhoneNo(rs.getString(5));
			customer.setEmail(rs.getString(6));			
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return customer;
	}
	
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
	
}
