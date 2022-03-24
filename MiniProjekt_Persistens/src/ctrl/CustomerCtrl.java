package ctrl;

import db.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDB customerDB;
	
	public CustomerCtrl(CustomerDB customerDB) {
		this.customerDB = customerDB;
	}
	
	public Customer findByPhoneNo(String phoneNo) {
		Customer res = CustomerDB.getInstance().findByPhoneNo(phoneNo);
		return res;
	}

	public CustomerDB getCustomerDB() {
		return customerDB;
	}

	public void setCustomerDB(CustomerDB customerDB) {
		this.customerDB = customerDB;
	}
}
