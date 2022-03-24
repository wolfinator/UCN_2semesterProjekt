package ctrl;

import db.CustomerDb;
import model.Customer;

public class CustomerCtrl {
	private CustomerDb customerDb;
	
	public CustomerCtrl() {
		customerDb = new CustomerDb();
	}
	
	public Customer findByPhoneNo(String phoneNo) {
		return null;

	}
}
