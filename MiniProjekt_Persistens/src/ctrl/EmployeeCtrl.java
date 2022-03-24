package ctrl;

import db.EmployeeDB;
import db.EmployeeDbIF;
import model.Employee;

public class EmployeeCtrl {

	private Employee employeeById;	
	private EmployeeDbIF employeeDb;
	
	public EmployeeCtrl() throws DataAccessException {
		employeeById = new Employee(1, "Jamal", "Jamal@Jamail.com", "42069696", "JamalHedeVej 2",  "9000", "Aalborg", 42069.95);
		EmployeeDB employeeDb = new EmployeeDB();
	}
	

	public Employee findEmployeeById(int id) throws DataAccessException {
		Employee e = employeeDb.findEmployeeById(id);
		return e;
		
	}

	
	
	
	
}
