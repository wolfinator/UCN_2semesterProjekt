package ctrl;

import db.CustomerDB;
import db.CustomerDBIF;

public class CustomerCtrl {
	private CustomerDBIF customerDB;
	
	public CustomerCtrl() {
		customerDB = new CustomerDB();
	}
	
}
