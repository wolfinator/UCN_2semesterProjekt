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

	public void setIsClub (boolean string) {
		this.isClub = string;
	}

	public static String getPersonId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPersonID(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setFname(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setLname(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPersonType(String string) {
		// TODO Auto-generated method stub
		
	}

	public void sethouseNumber(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setStreetName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setIsClub(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setphoneNo(String string) {
		// TODO Auto-generated method stub
		
	}

	public static String getIsClub() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getFname() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getLname() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getPersonType() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getHouseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getStreetName() {
		// TODO Auto-generated method stub
		return null;
	}
}
	