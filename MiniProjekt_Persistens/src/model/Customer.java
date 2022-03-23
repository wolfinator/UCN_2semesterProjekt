package model;

public class Customer extends Person{
	private String CustomerID;
	
	public Customer() {
		super(name, email, phoneNo, address, zipcode, city);
	
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
}
