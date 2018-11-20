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

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
		
	public static final String INSERT_QUERY1="insert into registration(name,username,email,phno,pass) values(?,?,?,?,?)";
	public static final String INSERT_QUERY2="insert into login(username,pass) values(?,?)";

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
		    String name=request.getParameter("name");
		    String un=request.getParameter("username");
		    String email=request.getParameter("email");
		    String phno=request.getParameter("phonenumber");
			String pw=request.getParameter("password");
		
				// Connect to mysql and verify username password
				try {
					Class.forName(driverClassName);
					System.out.println("Class found in reg");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("CLASS NOT FOUND!");
					e1.printStackTrace();
				}
				// loads driver
				try(Connection conn=DriverManager.getConnection(url, username, password)){ // gets a new connection
					PreparedStatement st = conn.prepareStatement("select * from registration");
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY1);
			ps.setString(1, name);
			ps.setString(2,un);
			ps.setString(3, email);
			ps.setLong(4, Integer.parseInt(pw));
			ps.setString(5,pw);
	 
			PreparedStatement ps1 = conn.prepareStatement(INSERT_QUERY2);
			ps1.setString(1,un);
			ps1.setLong(2, Integer.parseInt(pw));
			
			ResultSet rs = st.executeQuery();
			boolean rs1 = ps.execute();
			boolean rs2 = ps1.execute();
			 while(rs.next()) {

		           //maxSalary =  r1.getString("empname");
		           //System.out.println("The name of employee who has the higher salary is :");
				   	//System.out.println( "empname"+"||"+ "epid");
		    		System.out.println( rs.getString("name")+"||"+ rs.getString("username"));

		      }
			int flag;
			if (rs1) {
	            System.out.println("Registration UnSuccess!");
	            flag=0;
	        } 
	        else {
	            System.out.println("Registration Success!");
	            flag=1;
	        }
				
			if(flag==1) {
				response.sendRedirect("login.html");
			}
			else
				response.sendRedirect("error.html");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
