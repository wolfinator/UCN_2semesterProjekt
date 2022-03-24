package model;

public abstract class Person {
	private int id;
	private String name;
	private String email;
	private String phoneNo;
	private String address;
	private String zipcode;
	private String city;
	
	public Person(int id, String name, String email, String phoneNo, String address, String zipcode, String city) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
