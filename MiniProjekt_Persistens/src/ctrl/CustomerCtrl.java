package ctrl;

import java.util.List;

import db.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDB customerDB;

	public CustomerCtrl() throws DataAccessException {
		customerDB = new CustomerDB();
	}

	public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
		Customer res = customerDB.findByPhoneNo(phoneNo);
		return res;
	}
	
	/*
	 * Ikke brug for implementation endnu
	 * 
	public Customer findByPersonID(int personID) throws DataAccessException {
		Customer res = customerDB.findByPersonID(true);
		return res;
	}
	*/
	
	/*
	 *  Ikke brug for implementation endnu
	 *  
	public List<Customer> findCustomer() throws DataAccessException {
		List<Customer> res = customerDB.findCustomer();
		return res;
	}
	*/
	
	/*
	 * Ikke brug for implementation endnu
	 * 
	public Customer insert(Customer customer) throws DataAccessException {
		Customer res = customerDB.insert(customer);
		return res;
	}
	*/
}
