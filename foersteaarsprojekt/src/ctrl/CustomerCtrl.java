package ctrl;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

public class CustomerCtrl {
	private CustomerDBIF customerDB;
	private Customer currCustomer;
	
	public CustomerCtrl() {
		customerDB = new CustomerDB();
	}


//	public Customer CreateCustomer (String cusName, String cusPhoneNo, String cusEmail) {
//		Customer customer = new Customer(cusName, cusPhoneNo, cusEmail);
//
//		return customer;
//	}
	
	

	
}
