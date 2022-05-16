package db;

import ctrl.DataAccessException;
import model.Customer;

public interface CustomerDBIF {
	Customer findById(int id) throws DataAccessException;
	
	int saveCustomer(Customer customer) throws DataAccessException;
}
