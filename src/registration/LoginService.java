package registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginService {

	public boolean authenticate(String name, String password) {
		String query;
		String dbUsername, dbPassword;

		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(

					DbContract.HOST + DbContract.DB_NAME,

					DbContract.USERNAME,

					DbContract.PASSWORD);

			System.out.println("DB connected");
			Statement stmt = (Statement) con.createStatement();
			query = "SELECT name, password FROM signup;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				dbUsername = rs.getString("name");
				dbPassword = rs.getString("password");
			
				if ((dbUsername.trim().equalsIgnoreCase(name.trim())) && (dbPassword.equalsIgnoreCase(password))) {
					System.out.println("OK");
					return true;
				} 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
