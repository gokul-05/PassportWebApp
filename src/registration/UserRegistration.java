package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegistration
 */
@SuppressWarnings("serial")
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		String uname = request.getParameter("name");
		String uage = request.getParameter("age");
		String udate = request.getParameter("date");
		String ubirthplace = request.getParameter("birthplace");
		String ugender = request.getParameter("gender");
		String ufathername = request.getParameter("fathername");
		String umothername = request.getParameter("mothername");
		String uaadhar = request.getParameter("aadhar");
		String umobile = request.getParameter("mobile");
		String uaddress = request.getParameter("address");
		String upassporttype = request.getParameter("passporttype");

		/*System.out.println("username: " + uname);
		System.out.println("age: " + udate);
		System.out.println("date: " + uage);*/
		
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

             

            System.out.println("UR DB connected");
            
			PreparedStatement ps = con.prepareStatement("INSERT INTO userreg values(?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, uname);
			ps.setString(2, uage);
			ps.setString(3, udate);
			ps.setString(4, ubirthplace);
			ps.setString(5, ugender);
			ps.setString(6, ufathername);
			ps.setString(7, umothername);
			ps.setString(8, uaadhar);
			ps.setString(9, umobile);
			ps.setString(10, uaddress);
			ps.setString(11, upassporttype);
			
			/*int i = */ps.executeUpdate();
			response.sendRedirect("success.html");

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
