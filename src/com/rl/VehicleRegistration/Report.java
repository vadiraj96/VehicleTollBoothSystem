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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Report")
public class Report extends HttpServlet {
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
  System.out.println("Class found in vehicle_register");
 } catch (ClassNotFoundException e1) {
  // TODO Auto-generated catch block
  System.out.println("CLASS NOT FOUND!");
  e1.printStackTrace();
 }
 
 String query =  buildQuery(request);
 try(Connection conn1=DriverManager.getConnection(url, username, password)){ // gets a new connection
        System.out.println("Connected!");
        
        PreparedStatement ps = conn1.prepareStatement(QueryBuilder(request));
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            al = new ArrayList<String>();
           
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getInt(4));
            al.add(rs.getString(1));
            al.add(rs.getString(2));
            al.add(rs.getString(3));
            al.add(rs.getString(4));
            al.add(rs.getString(5));
            al.add(rs.getString(6));
            al.add(rs.getString(7));
            al.add(rs.getString(8));
            al.add(rs.getString(9));
            

            System.out.println("al :: " + al);
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
   
   String id=request.getParameter("username1");
   String s_date =request.getParameter("s_date");
   String e_date=request.getParameter("e_date");
   String v_type=request.getParameter("vehicle_type");
   String e_type=request.getParameter("in");
   System.out.println("Entry Type="+e_type);
   
   java.sql.Date sqlDate1 = null;
      java.sql.Date sqlDate2 = null;
      if(s_date!=null && e_date!=null) {
       
       if(s_date.equals("mm/dd/yyyy")&& e_date.equals("mm/dd/yyyy")) {
        LocalDate pid3 = LocalDate.now();
        LocalDate pid4 = LocalDate.now();
        String pid5=pid3.toString();
        String pid6=pid4.toString();
          System.out.println(pid5);
             System.out.println(pid6);
          java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(pid5); 
          java.util.Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(pid6); 
           sqlDate1 = new java.sql.Date(date1.getTime());
           sqlDate2 = new java.sql.Date(date2.getTime());
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
  
   StringBuilder sql = new StringBuilder("select * FROM vehicle_register WHERE 1=1 ");
   
   if(!(id.isEmpty())) {
    sql.append(" AND user_name='"+id+"'");
   }
   if(id.isEmpty())
    sql.append("");
   
   if((s_date!=null)&&(e_date!=null)) {
    sql.append(" AND created_dt BETWEEN '"+sqlDate1+"' AND '"+sqlDate2+"'");
   }

   /*if(v_type!=null) {
    sql.append(" AND vehical_type="+v_type);
   }
   */
   if(!(e_type==null)) {
//    sql.append(" AND entry_type="+e_type);
    if(e_type=="inout")
       sql.append("");
    else
       sql.append(" AND entry_type="+e_type);
   }
  
   
   String s=sql.toString();
   System.out.println(s);
   return s;
  }

@Override
  public String getServletInfo() {
      return "Short description";
  }
}