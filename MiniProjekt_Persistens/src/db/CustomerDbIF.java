package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Customer;
import model.Employee;

public interface CustomerDbIF {
	
	Customer findByPhoneNo(String phoneNo) throws DataAccessException;
	
	//Customer findByPersonID(int personID) throws DataAccessException;

	//List<Customer> findCustomer() throws DataAccessException;
	
	//Customer insert(Customer customer) throws DataAccessException;

}
