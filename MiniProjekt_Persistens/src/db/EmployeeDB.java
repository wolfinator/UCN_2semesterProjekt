package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Employee;

public class EmployeeDB implements EmployeeDbIF {
	private String FIND_EMPLOYEE_BY_ID = "select p.id, p.fname, p.lname, p.phoneNo, p.email, p.personType, p.addressId, a.streetName,"
			+ " a.houseNumber, a.zipcode, c.city,  salary, "
			+ "from person, Employee where personId = ? and personType = 2"
			+ " and personId = p.id, and p.addressId = a.id, and a.zipcode = c.zipcode";
	private PreparedStatement ps_findById;

	private EmployeeDB() throws DataAccessException {

	}

	public Employee findEmployeeById(int id) throws DataAccessException {
		DBConnection dbc = DBConnection.getInstance();
		Connection con = dbc.getConnection();
		Employee res = null;

		try {
			ps_findById = con.prepareStatement(FIND_EMPLOYEE_BY_ID);
			ps_findById.setInt(1, id);
			ResultSet rs = ps_findById.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {

		}
		return res;
	}

	private List<Employee> buildObjects(ResultSet rs) throws DataAccessException {
		List<Employee> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Employee employee = buildObject(rs);
				res.add(employee);
			}
		} catch (SQLException e) {

		}	
		return res;
	}

	

	// id, fname, lname, phoneNo, email, personType, addressId, streetName,
	// houseNumber, zipcode, city, salary.
	private Employee buildObject(ResultSet rs) throws DataAccessException {
		Employee employee = new Employee();
		try {
			employee.setEmployeeId(rs.getInt(1));
			employee.setName(rs.getString(2) + ' ' + rs.getString(3));
			employee.setPhoneNo(rs.getString(4));
			employee.setEmail(rs.getString(5));
			employee.setAddress(rs.getString(7) + rs.getString(8) + rs.getInt(9) + rs.getInt(10) + rs.getString(11));
			employee.setSalary(rs.getDouble(12));

		} catch (SQLException e) {

		}

	}

}

}
