package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ctrl.DataAccessException;
import db.DBConnection;

public class start {

	public static void main(String[] args) {
		try {
			DBConnection db = DBConnection.getInstance();
			Connection con = db.getConnection();
			String statement = "Select 'Connected';";
			PreparedStatement ps_test;
			ps_test = con.prepareStatement(statement);
			ResultSet rs = ps_test.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
