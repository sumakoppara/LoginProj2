

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.JOptionPane;
import java.sql.*;
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system", "manager");
			 PreparedStatement st = con.prepareStatement("select * from login where username='"+username+"' and password='"+password+"'");
			 ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			JOptionPane.showMessageDialog(null, "Login Succesfull");
			response.sendRedirect("/Login/login.html");
		}
		
		else
		{
			JOptionPane.showMessageDialog(null, "Login Not Succesfull");
			RequestDispatcher rd= request.getRequestDispatcher("/RegisterServlet");
			rd.forward(request, response);
		}
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
