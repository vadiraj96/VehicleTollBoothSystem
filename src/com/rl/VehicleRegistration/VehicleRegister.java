package com.rl.VehicleRegistration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class VehicleRegister
 */
@WebServlet("/VehicleRegister")
public class VehicleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String driverClassName = "org.postgresql.Driver";
	static String url = "jdbc:postgresql://localhost:5432/postgres";
	static String user = "postgres";
	static String password = "root";
	static PrintWriter out = null;
	final static List<String> allowedVehicle = (List<String>) Arrays.asList(new String[] { "2w", "4w", "6w", "8w" });
	static Connection con = null;
	final static String query = "insert into vehicle_register (user_name,vehicle_type,vehicle_charge,vehicle_no,vehicle_model,vehicle_capacity,entry_type) values(?,?,?,?,?,?,?)";

	public VehicleRegister() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String uname =(String) session.getAttribute("uname");
		out.print("<h1>"+"Welcome "+uname+"</h1>");
		String vtype = request.getParameter("vehicleType");
		int vcharge = Integer.parseInt(request.getParameter("Money"));
		String vnumber = request.getParameter("VehicleNo");
		String vmodel = request.getParameter("VehicleModel");
		int vcapacity = Integer.parseInt(request.getParameter("VehicleCapacity"));
		String etype = request.getParameter("EntryType");
		out.println("Attributes Loaded");

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, user, password);

			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, uname);
			st.setString(2, vtype);
			out.println(vtype);
			st.setInt(3, vcharge);
			st.setString(4, vnumber);
			st.setString(5, vmodel);
			st.setInt(6, vcapacity);
			st.setString(7, etype);
			//out.println("query loaded successfully");
			st.executeUpdate();
			//out.println("executed successfully");
			response.sendRedirect("back.html");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
			  try {
			   out = response.getWriter();
			   Class.forName(driverClassName);
			   con = DriverManager.getConnection(url, user, password);
			   PreparedStatement ps = con.prepareStatement(query);
			   //out.println("Before file upload");

			   ServletFileUpload uploadedFiles = new ServletFileUpload(new DiskFileItemFactory());
			   List<FileItem> fileList = uploadedFiles.parseRequest(request);
			   //out.println("After file upload");

			   for (FileItem file : fileList) {
			    try {
			    	
			     file.write(new File("C:\\Users\\rle0595\\eclipse-workspace\\VehicleRegistration\\FileUpload\\" + file.getName()));
			     out.println("File is Uploaded in Server!!");
			    } catch (Exception e) {
			    // out.println("File error");
			     e.printStackTrace();
			    }
			    Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\rle0595\\eclipse-workspace\\VehicleRegistration\\FileUpload\\"+ file.getName()).getAbsoluteFile());
			    //Workbook workbook = WorkbookFactory.create(request.getInputStream());
			    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			     Sheet sheet = workbook.getSheetAt(i);
			     int flag = 0;
			     for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
			      Row row = sheet.getRow(j);
			      if (allowedVehicle.contains(row.getCell(2).getStringCellValue())) {
			       Iterator cellIterator = row.cellIterator();
			       int k = 1;
			       while (cellIterator.hasNext()) {
			        Cell cell = (Cell) cellIterator.next();
			        if (k == 1)
			         cell = (Cell) cellIterator.next();
			        if (k > 7)
			         break;
			        switch (cell.getCellType()) {
			        case Cell.CELL_TYPE_STRING:
			         ps.setString(k, cell.getStringCellValue());
			         break;
			        case Cell.CELL_TYPE_NUMERIC:
			         ps.setInt(k, (int) cell.getNumericCellValue());
			        }
			        flag = 1;
			        k++;
			       }
			       ps.addBatch();
			      } else {
			       out.println("In Sheet "+file.getName()+"  "+row.getRowNum()
			         + " row data not inserted beacuse type of vehicle not allowed in this Toll");
			      }
			     }
			    }
			    ps.executeBatch();
			    response.sendRedirect("back.html");
			/*    out.println("<html><head></head><body><h2>Successfully Data Uploaded</h2>");
			    out.println("<form><input type='button' value='Go Back!' onClick='history.back()'></form></body></html>");*/
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			 }
}

