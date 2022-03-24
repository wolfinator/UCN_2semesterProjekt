package model;

public class Customer extends Person{
	private int customerID;
	private boolean isClub;
	public Object setP;
	
	public Customer(int customerID, boolean isClub, String name,String email,String phoneNo,String address,String zipcode,String city) {
		super(name, email, phoneNo, address, zipcode, city);
		this.customerID = customerID;
		this.isClub = isClub;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public boolean isClub() {
		return isClub;
	}

	public void setIsClub(boolean isClub) {
		this.isClub = isClub;
	}

	
}
	