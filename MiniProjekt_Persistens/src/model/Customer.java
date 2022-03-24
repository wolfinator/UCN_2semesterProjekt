package model;

public class Customer extends Person{
	private boolean isClub;
	
	public Customer(int id, boolean isClub, String name,String email,String phoneNo,String address,String zipcode,String city) {
		super(id, name, email, phoneNo, address, zipcode, city);
		this.isClub = isClub;
	}
	
	public Customer() {
		super();
	}

	public boolean isClub() {
		return isClub;
	}

	public void setIsClub(boolean isClub) {
		this.isClub = isClub;
	}
	
}
	