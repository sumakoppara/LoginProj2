

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
public class RegisterServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system", "manager");
			 PreparedStatement st = con.prepareStatement("insert into login values('"+username+"','"+password+"')");
			 st.executeUpdate();
			 out.println("Registered Successfully<br>");
			 out.println("Click <a href=\"/Login/login.html\">here</a>to login");
			 st.close();
			 con.close();
		
	}
		catch(Exception e)
		{
			out.println(e);	
		}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
