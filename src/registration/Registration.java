package registration;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
@WebServlet("/Registration")
public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		String user_name = request.getParameter("name");
		String user_email = request.getParameter("email");
		String user_pass = request.getParameter("password");

		System.out.println("username: " + user_name);
		System.out.println("password: " + user_pass);
		System.out.println("email: " + user_email);
		
		// get response writer
        /*PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your username is: " + user_name + "<br/>";      
        htmlRespone += "Your password is: " + user_pass + "<br>";
        htmlRespone += "Your email is: " + user_email + "</h2>";
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
		Connection con = null;
 * 

        writer.close();*/
		try {


            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(

                    DbContract.HOST+DbContract.DB_NAME,

                    DbContract.USERNAME,

                    DbContract.PASSWORD);

             

            System.out.println("REG DB connected");
            
			PreparedStatement ps = con.prepareStatement("INSERT INTO signup values(?,?,?)");

			ps.setString(1, user_name);
			ps.setString(2, user_email);
			ps.setString(3, user_pass);
			/*int i = */ps.executeUpdate();
			response.sendRedirect("index.html");

			/*if (i > 0) {
				out.println("Sucessfully registered..... Details entered in database.....");
			} else {
				out.println("Failed in  registration");
			}*/

		} catch (Exception se) {
			se.printStackTrace();
		} 

		}
	}





