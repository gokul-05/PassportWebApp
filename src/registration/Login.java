package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String name = null; String password = null;
		 */
		String query;
		String dbUsername, dbPassword;
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter printWriter  = response.getWriter();
        
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
				System.out.println("test name and password" + name + " and " + password);
				System.out.println("test dbname and dbpassword" + dbUsername + " and " + dbPassword);
				
				LoginService loginService = new LoginService();
				boolean result = loginService.authenticate(name, password);
				
				if(result){
					response.sendRedirect("userRegistration.html");
					return;
				}
				else{
					
					printWriter.println("<script type=\"text/javascript\">");
					printWriter.println("alert('Invalid Username or password...Retry!!');");
					printWriter.println("location='index.html';");
					printWriter.println("</script>");
					
					/*printWriter.println("alert('User or password incorrect');");
					response.sendRedirect("index.html");*/
					return;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}