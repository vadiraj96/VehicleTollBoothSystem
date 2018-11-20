package com.rl.VehicleRegistration;

import java.sql.*;

public class DbConnection {
	private static String DriverClass="org.postgres.Driver";
	private static String url="jdbc:postgresql://localhost:5432/postgres";
	private static String user="postgres";
	private static String password="root";
	 static Connection con;
	 static Statement st;
	 static ResultSet rs;
	 static PreparedStatement ps;
	public  static void connectDb() throws ClassNotFoundException, SQLException {
		try {
    		//Class.forName(DriverClass);
    		con = DriverManager.getConnection(url,user,password);
    		System.out.println("Congratulations Your  Connection is established "); 
    		st=con.createStatement();
    		
		} 
		catch (SQLException e) {
			System.out.println("Connection Failed Check output console");
    		e.printStackTrace();
    		//throw e;
		}	
	}
}
