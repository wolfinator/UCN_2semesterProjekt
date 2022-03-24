package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Customer;
import model.Employee;

public interface CustomerDbIF {
	
	List<Customer> findByPhoneNo(String phoneNo) throws DataAccessException;
	
	Customer findBypersonID(int personID , boolean b) throws DataAccessException;

	List<Customer> findCustomer(boolean fullAssociation) throws DataAccessException;
	
	Customer insert(Customer customer) throws DataAccessException;

}
