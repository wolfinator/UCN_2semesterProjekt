package model;

public class Employee extends Person {
	private int employeeId;
	private double salary;

	public Employee(int employeeId, String name, String email, String phoneNo, String address,
			String zipCode, String city, double salary) {
		super(name, email, phoneNo, address, zipCode, city);
		this.setEmployeeId(employeeId);
		this.setSalary(salary);
	}
	
	public Employee() {
		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
