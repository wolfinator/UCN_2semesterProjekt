package ctrl;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

public class CustomerCtrl {
	private CustomerDBIF customerDB;
	
	public CustomerCtrl() {
		customerDB = new CustomerDB();
	}
	
	public Customer CreateCustomer (String cusName, String cusPhoneNo, String cusEmail) {
		
	}
	
}
