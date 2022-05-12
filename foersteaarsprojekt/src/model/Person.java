package model;

public abstract class Person { 
	protected int id;
	protected String name;
	protected String phoneNo;
	protected String email;
	protected String address;

	public Person(int id, String name, String phoneNo, String email, String address) {

		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
	}
	
	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
