package com.rl.VehicleRegistration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	static String driverClassName="org.postgresql.Driver";
	static String url="jdbc:postgresql://localhost:5432/postgres";
	static String username="postgres";
	static String password="root";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hi");
		//doGet(request, response);
		 response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
		String un=request.getParameter("username");
		String pw=request.getParameter("password");
	
		// Connect to mysql and verify username password
		
		
			try {
				Class.forName(driverClassName);
				System.out.println("Class found");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("CLASS NOT FOUND!");
				e1.printStackTrace();
			}
		 // loads driver
			try(Connection conn=DriverManager.getConnection(url, username, password)){ // gets a new connection
		PreparedStatement ps = conn.prepareStatement("select username,pass from login where username=? and pass=?");
		ps.setString(1, un);
		ps.setLong(2, Integer.parseInt(pw));
 
		ResultSet rs = ps.executeQuery();
 
		int flag;
		if (rs.next()) {
            System.out.println("Correct login credentials");
            response.sendRedirect("VehicleRegister.jsp");
            flag=1;
        } 
        else {
            System.out.println("Incorrect login credentials");
            flag=0;
        }
			
		if(flag==1) {
			response.sendRedirect("login.html");
		}
		else
			response.sendRedirect("index.html");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
