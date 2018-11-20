package com.rl.VehicleRegistration;




import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register1")
public class register1 extends HttpServlet {
	public static final String INSERT_QUERY1 = "insert into USER_REGISTRATION2(USERNAME,MAIL_ID,PASSWORD,ADDRESS,PNO) values (?,?,?,?,?)";
	// public static final String INSERT_QUERY2
	static String url = "jdbc:postgresql://localhost:5432/postgres";
	static String usrname = "postgres";
	static String sqlpword = "root";
	static String driverclass = "org.postgresql.Driver";

	private static final long serialVersionUID = 1L;
	private static final String PASSWORD_PATTERN = "((?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{6,20})";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/*
	 * public register1() { super(); // TODO Auto-generated constructor stub }
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("uname");

		String pno = request.getParameter("pno");

		String email = request.getParameter("email");

		String password = request.getParameter("password");
		String password1 = request.getParameter("Rpassword");

		String address = request.getParameter("Address");
		try {
			Class.forName(driverclass);
			System.out.println("Class found in reg");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("CLASS NOT FOUND!");
			e1.printStackTrace();
		}

		/*
		 * password1 p =new password1(); p.PasswordValidator();
		 * if(p.validate(password)==false) { response.sendRedirect("regex.html"); }
		 */

		isAlreadyExistRedirect(username, response);
		isPasswordNotMatching(password, password1, response);
		isEmailValid(email, response);
		isPhoneNumberValidate(pno,response);
  
		try (Connection con = DriverManager.getConnection(url, usrname, sqlpword)) {
			System.out.println("connection created");
			PreparedStatement ps1 = con.prepareStatement(INSERT_QUERY1);

			ps1.setString(1, username);
			// ps1.setLong(2, Integer.parseInt(pno));
			ps1.setString(2, email);
			ps1.setString(3, password);
			ps1.setString(4, address);
			ps1.setString(5,pno);

			int noOfRows = ps1.executeUpdate();
			if (noOfRows > 0) {

				System.out.println("Registration Success!");
				out.println("<h1>" + "Successfully Submitted" + "</h1>");
				response.sendRedirect("login.html");
			} else {
				System.out.println("Registration UnSuccess!");
				response.sendRedirect("error.html");
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

	private void isPhoneNumberValidate(String pno, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if(pno.length()!=10)
		{
			response.sendRedirect(response.encodeRedirectURL("phonenumber.html"));
		}
		
	}

	private void isEmailValid(String email, HttpServletResponse response) throws IOException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex); 
		boolean isValid = true;
		if (email == null)
			isValid = false;
		
		isValid = pat.matcher(email).matches();
		
		if(!isValid)
			response.sendRedirect(response.encodeRedirectURL("wrong.html"));
	} 

	private void isPasswordNotMatching(String password, String password1, HttpServletResponse response)
			throws IOException {
		if (password.equals(password1) == false) {
			response.sendRedirect(response.encodeRedirectURL("password.html"));
		}
	}

	private void isAlreadyExistRedirect(String username, HttpServletResponse response) {
		String sql = "SELECT * FROM USER_REGISTRATION2 where username = '" + username + "'";
		try (Connection con = DriverManager.getConnection(url, usrname, sqlpword);
				PreparedStatement ps1 = con.prepareStatement(sql);
				ResultSet rs = ps1.executeQuery();) {

			if (rs.next())
				response.sendRedirect(response.encodeRedirectURL("wrong.html"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
