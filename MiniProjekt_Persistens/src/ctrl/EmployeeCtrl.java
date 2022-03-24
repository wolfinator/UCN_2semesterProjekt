package ctrl;

import model.Employee;

public class EmployeeCtrl {

	private Employee employeeById;
	private static EmployeeCtrl instance;
	
	
	private EmployeeCtrl() {
		employeeById = new Employee(1, "Jamal", "Jamal@Jamail.com", "42069696", "JamalHedeVej 2",  "9000", "Aalborg", 42069.95);
	}
	
	public static EmployeeCtrl getInstance() {
		if(instance == null) {
			instance = new EmployeeCtrl();
		}
		return instance;
	}
	
	public Employee findEmployeeById() {
		return employeeById;
	}
	
	
	
	
}
