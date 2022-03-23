package ctrl;

import db.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDB customerDB;
	

	public Customer findByPhoneNo(String phoneNo) {
		Customer res = CustomerDB.getInstance().findByPhoneNo(phoneNo);
		return res;
	}
}
