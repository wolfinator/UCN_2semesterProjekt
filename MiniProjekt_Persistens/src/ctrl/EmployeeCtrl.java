package ctrl;

import db.EmployeeDB;
import db.EmployeeDbIF;
import model.Employee;

public class EmployeeCtrl {

	private EmployeeDbIF employeeDb;
	
	public EmployeeCtrl() throws DataAccessException {
		employeeDb = new EmployeeDB();
	}

	public Employee findEmployeeById(int id) throws DataAccessException {
		Employee e = employeeDb.findEmployeeById(id);
		return e;
		
	}
	
}
