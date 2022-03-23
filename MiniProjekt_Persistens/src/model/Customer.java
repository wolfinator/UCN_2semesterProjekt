package model;

public class Customer extends Person{
	private String customerID;
	private boolean isClub;
	
	public Customer(String customerID, boolean isClub, String name,String email,String phoneNo,String address,String zipcode,String city) {
		super(name, email, phoneNo, address, zipcode, city);
		this.customerID = customerID;
		this.isClub = isClub;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public boolean isClub() {
		return isClub;
	}

	public void setIsClub (boolean isClub) {
		this.isClub = isClub;
	}
}
	