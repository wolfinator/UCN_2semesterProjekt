package ctrl;

import java.util.List;

import db.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDB customerDB;

	public CustomerCtrl() throws DataAccessException {
		customerDB = new CustomerDB();
	}

	public List<Customer> findByPhoneNo(String phoneNo) throws DataAccessException {
		List<Customer> res = customerDB.findByPhoneNo(phoneNo, true);
		return res;
	}

	public Customer findByPersonID(int personID) throws DataAccessException {
		Customer res = customerDB.findByPersonID(true);
		return res;
	}

	public List<Customer> findCustomer() throws DataAccessException {
		List<Customer> res = customerDB.findCustomer();
		return res;
	}

	public Customer insert(Customer customer) throws DataAccessException {
		Customer res = customerDB.insert(customer);
		return res;
	}
}
