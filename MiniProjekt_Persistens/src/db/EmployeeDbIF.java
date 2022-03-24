package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Employee;

public interface EmployeeDbIF {
	Employee findEmployeeById(int id) throws DataAccessException;
}
