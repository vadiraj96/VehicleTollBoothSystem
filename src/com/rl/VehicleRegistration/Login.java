package com.rl.VehicleRegistration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	static String url="jdbc:postgresql://localhost:5432/postgres";
	static String usrname="postgres";
	static String sqlpword="root";
	static String driverclass="org.postgresql.Driver";
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public  Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubPrintWriter out=response.getWriter();
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		String username = request.getParameter("uname");
	

		
		String password = request.getParameter("password");
		
		/*if(password.equals(password1)==false)
		{
			response.sendRedirect("password.html");
		}
		*/
		try {
			Class.forName(driverclass);
			System.out.println("Class found in reg");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("CLASS NOT FOUND!");
			e1.printStackTrace();
		}


		
	    
		try(Connection con = DriverManager.getConnection(url, usrname, sqlpword)){
			
			
			System.out.println("connection created");
			//out.println("connection created");
			Statement st = con.createStatement();
			/*PreparedStatement st = con.prepareStatement("select username from USER_REGISTRATION2 ");
			PreparedStatement st1 = con.prepareStatement("select password from USER_REGISTRATION2 ");
			
	*/

			String sqlString = "SELECT FROM USER_REGISTRATION2 WHERE username='"+username+"' AND password='"+password+"' ";
			ResultSet rt=st.executeQuery(sqlString);
			
			if(rt.next()) 
			{
			
			//con.close();
			
			 System.out.println("LOGIN Success!");
			 HttpSession session=request.getSession();
			 session.setAttribute("uname",username);
			 System.out.println("Session Created "+session.getAttribute("uname"));
			
			 response.sendRedirect("welcome.html");
			}
			else
			{
				response.sendRedirect("error1.html");
			}
		}
	
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
	//	doGet(request, response);
		
	}
	
	
}

