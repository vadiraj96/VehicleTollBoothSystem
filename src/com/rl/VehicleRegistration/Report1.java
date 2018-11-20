package com.rl.VehicleRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Report1")
public class Report1 extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  String url="jdbc:postgresql://localhost:5432/postgres";
  String username="postgres";
  String password="root";
  String driverClassName="org.postgresql.Driver";
    response.setContentType("text/html");
    
    ArrayList<String> al = null;
    ArrayList pid_list = new ArrayList();
    
    Connection conn = null;
    Statement stmt = null;
 try {
  Class.forName(driverClassName);
  //System.out.println("Class found in report");
 } catch (ClassNotFoundException e1) {
  // TODO Auto-generated catch block
  //System.out.println("CLASS NOT FOUND!");
  e1.printStackTrace();
 }
 
 String query =  buildQuery(request);
 try(Connection conn1=DriverManager.getConnection(url, username, password)){ // gets a new connection
      //  System.out.println("Connected!");
        
        PreparedStatement ps = conn1.prepareStatement(QueryBuilder(request));
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            al = new ArrayList<String>();
           
          //  System.out.println(rs.getString(1));
            //System.out.println(rs.getString(2));
            //System.out.println(rs.getString(3));
           // System.out.println(rs.getInt(4));
            al.add(rs.getString(1));
            al.add(rs.getString(2));
            al.add(rs.getString(3));
            al.add(rs.getString(4));
            al.add(rs.getString(5));
            al.add(rs.getString(6));
            al.add(rs.getString(7));
            al.add(rs.getString(8));
           // al.add(rs.getString(9));
            

            //System.out.println("al :: " + al);
            pid_list.add(al);
        } 
        
        request.setAttribute("piList", pid_list);
        RequestDispatcher view = request.getRequestDispatcher("./Search.jsp");
        view.forward(request, response);
        //conn1.close();
        //System.out.println("Disconnected!");
        
       
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  private String buildQuery(HttpServletRequest request) {
 // TODO Auto-generated method stub
 return null;
}

  public String QueryBuilder(HttpServletRequest request) throws ParseException {
   
   String u_name=request.getParameter("username1");
   String s_date =request.getParameter("s_date");
   String e_date=request.getParameter("e_date");
   String e_type=request.getParameter("entry_type");
   String[] v_type=request.getParameterValues("vehicle_type");	 
  /* ArrayList<String> v_type=new ArrayList();
   for(String obj:str) {
	   v_type.add(obj);
   }*/
   
  //System.out.println("v_type ="+v_type);
   //System.out.println("Entry Type="+e_type);
   
   java.sql.Date sqlDate1 = null;
      java.sql.Date sqlDate2 = null;
      if(s_date!=null && e_date!=null) {
       
       if(s_date.isEmpty()&& e_date.isEmpty()) {
        
           sqlDate1=null;
           sqlDate2=null;
       }
       else
       {
           System.out.println(s_date);
              System.out.println(e_date);
        java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(s_date); 
           java.util.Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(e_date); 
            sqlDate1 = new java.sql.Date(date1.getTime());
            sqlDate2 = new java.sql.Date(date2.getTime());
       }
       
      }
  
   StringBuilder sql = new StringBuilder("select user_name, vehicle_type, vehicle_charge, vehicle_no, vehicle_model, vehicle_capacity, entry_type, created_dt FROM vehicle_register WHERE 1=1 ");
     // StringBuilder sql = new StringBuilder("SELECT * FROM vehicle_register WHERE 1=1 ");
   if(!(u_name.isEmpty())) {
    sql.append(" AND user_name='"+u_name+"'");
   }
   if(u_name.isEmpty())
    sql.append("");
   
   if((!(s_date.isEmpty()))&&(!(e_date.isEmpty()))) {
    sql.append(" AND created_dt BETWEEN '"+sqlDate1+"' AND '"+sqlDate2+"'");
   }
   else if((s_date.equals(null))&&(e_date.equals(null))) {
    sql.append("");
   }
 /*  
   if(!v_type.isEmpty()) {
    sql.append(" AND vehicle_type IN (");

    for(String s:v_type)
    { if(!s.equals(v_type.get(v_type.size()-1)))
    	sql.append("'"+s+"'"+",");
    	else
    		sql.append("'"+s+"'");
      }
  
    sql.append(")");
   }*/
   
   if(v_type!=null) {
	   sql.append(" AND vehicle_type IN (");
	   for(String s:v_type) {
		   if(!s.equals(v_type[v_type.length-1]))
			   sql.append("'"+s+"'"+",");
	    	else
	    		sql.append("'"+s+"'");
	   }
	   sql.append(")");
   }
   
  	  if(e_type.equalsIgnoreCase("InOut"))
  		 sql.append("");
	  else
		sql.append(" AND entry_type='"+e_type+"'");
		 
   
   String s=sql.toString();
   //System.out.println(s);
   return s;
  }

@Override
  public String getServletInfo() {
      return "Short description";
  }
}