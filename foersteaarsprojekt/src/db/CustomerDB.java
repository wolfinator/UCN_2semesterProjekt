package db;

import ctrl.DataAccessException;
import model.Customer;

public class CustomerDB implements CustomerDBIF {

	@Override
	public Customer findById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveCustomer(Customer customer) throws DataAccessException {
		// TODO insert customer in db and return inserted id
		return 0;
	}

}
