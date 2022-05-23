package model;

import java.util.List;

public class Customer extends Person {

	public Customer() {
		super();
	}
	public Customer(String cusName, String cusPhoneNo, String cusEmail) {
		super();
		this.name = cusName;
		this.phoneNo = cusPhoneNo;
		this.email = cusEmail;
	}

	public String getFirstName() {
		String fullName = this.name;
		String firstName = null;
		if (fullName != null) {
			String[] splitName = fullName.split(" ");
			firstName = splitName[0];
		}

		return firstName;
	}

	public String getLastName() {
		String fullName = this.name;
		String lastName = null;
		if (fullName != null) {
			String[] splitName = fullName.split(" ");
			lastName = splitName[splitName.length - 1];
		}
		return lastName;
	}
	
	
}
