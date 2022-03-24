package model;

public class Employee extends Person {
	private double salary;

	public Employee(int id, String name, String email, String phoneNo, String address,
			String zipCode, String city, double salary) {
		super(id, name, email, phoneNo, address, zipCode, city);
		this.setSalary(salary);
	}
	
	public Employee() {
		super();
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
