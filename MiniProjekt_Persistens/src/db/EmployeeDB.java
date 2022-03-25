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
	private String FIND_EMPLOYEE_BY_ID_SQL = "select p.id, p.fname, p.lname, p.phoneNo, p.email, p.personType, a.streetName,"
			+ " a.houseNumber, a.zipcode, c.city, salary "
			+ "from Person p, Employee e, CityZipcode c, _Address a where personId = ? and personType = 2"
			+ " and personId = p.id and p.addressId = a.id and a.zipcode = c.zipcode";
	private PreparedStatement ps_findById;

	public EmployeeDB() throws DataAccessException {

	}

	public Employee findEmployeeById(int id) throws DataAccessException {
		DBConnection dbc = DBConnection.getInstance();
		Connection con = dbc.getConnection();
		Employee res = null;

		try {
			ps_findById = con.prepareStatement(FIND_EMPLOYEE_BY_ID_SQL);
			ps_findById.setInt(1, id);
			ResultSet rs = ps_findById.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO lav noget andet maybe
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
			
			System.out.println(e);
		}	
		return res;
	}

	// 1   2      3      4 		  5		 6			 7
	// id, fname, lname, phoneNo, email, personType, streetName,
	// 8			9		 10	   11
	// houseNumber, zipcode, city, salary.
	private Employee buildObject(ResultSet rs) throws DataAccessException {
		Employee employee = new Employee();
		try {
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2) + ' ' + rs.getString(3));
			employee.setPhoneNo(rs.getString(4));
			employee.setEmail(rs.getString(5));
			employee.setAddress(rs.getString(7) + ' ' + rs.getString(8));
			employee.setCity(rs.getString(10));
			employee.setZipcode(rs.getString(9));
			employee.setSalary(rs.getDouble(11));

		} catch (SQLException e) {
			System.out.println(e);
		}
		return employee;

	}

}
