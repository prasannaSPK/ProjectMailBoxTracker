
/************************************************************
* 
This Project is implemented as a part of Academics by students of University of central Missouri,LeesSummit,MO 
Students: Sai Prasanna Kumar Korlakunta, Gowtami
Instructor: Dr.Sam Ramanujan
Stream Of Study: Big Data Analytics and Information Technology
Date Of Project Submitted: 30th April 2022
 

*************************************************/
package codeServelets;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OwnerBean;
import other.DatabaseConnection;

@WebServlet("/newuserregister")
public class NewRegisteration extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String OwnerName = checkVal(request.getParameter("OwnerName"), "Missing Owner name");
//		
//		String FlatNo = checkVal(request.getParameter("FlatNo"), "Missing FLat Number");
//		String MailBoxNo = checkVal(request.getParameter("MailBoxNo"), "Missing MailBox Number");
//		String EmailAddress = checkVal(request.getParameter("EmailAddress"), "Missing email address");
		String OwnerName = request.getParameter("OwnerName");
		String FlatNo = request.getParameter("FlatNo");
		String MailBoxNo = request.getParameter("MailBoxNo");
		String EmailAddress = request.getParameter("EmailAddress");
		
		System.out.println(OwnerName + "....." + FlatNo);
		
		
		try {
			 //final String driver =  "com.mysql.jdbc.Driver";// depricated so using below
			 DatabaseConnection  dbc = new DatabaseConnection();
			   Connection c = dbc.initializeDatabase();
			   Statement stmt = c.createStatement();

			System.out.println("Creating Tables!!!");
			
			
			OwnerBean owner1 = new OwnerBean(); // connecting to model here
			owner1.setOWNER_NAME(OwnerName);
			owner1.setFLAT_NO(Integer.parseInt(FlatNo));
			owner1.setMAIL_BOX_NO(Integer.parseInt(MailBoxNo));
			owner1.setEMAIL(EmailAddress);
			
			
//			String values = owner1.getOWNER_NAME() + "," +owner1.getFLAT_NO() +"," +owner1.getMAILBOX_NO() + "," +owner1.getEMAIL();
//			System.out.println("values are :" + values);
			
//			
			
			
			 
			//String values = owner1.getOWNER_NAME() + "," +owner1.getFLAT_NO() +"," +owner1.getMAILBOX_NO() + "," + owner1.getEMAIL();
			//System.out.println(values);
			stmt.executeUpdate("INSERT INTO OWNER(OWNER_NAME,FLAT_NO,MAIL_BOX_NO,EMAIL)"+ "VALUES('"+ owner1.getOWNER_NAME() + "','" + owner1.getFLAT_NO()+ "','" + owner1.getMAIL_BOX_NO() + "','" + owner1.getEMAIL()+"')");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String title = "Registering";
			String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
			out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
					+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" + "<UL>\n"
					+ "  <LI><B>Owner Name</B>: " + OwnerName + "\n" + "  <LI><B>Flat No</B>: " + FlatNo + "\n" 
					+  "  <LI><B>MailBoxNo</B>: " + MailBoxNo + "\n" 
					+ "  <LI><B>EmailAddress</B>: " + EmailAddress + "\n" + "</UL>\n" + "<h1> Sucessfully Registered !! </h1>" +
					"<BR>" +
					"click here to"+ "<a href="+"'UserRegisteration.jsp'"+">RegisterNewUser</a>" +
					 "<br>"+
 	                "<form action ="+"index.jsp"+"  "+ "method="+">"+
 	                "<input type=" + "\"submit\"" + " name = \"GOHOME\"" + "value=\"Go to Home\">" +
 	                "</form>"+
					"</BODY></HTML>");
			return;

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("failed to insert data!!!!");
			e.printStackTrace();
		}
		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Registering";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + "failed to register" + "</H1>\n" + "<UL>\n" +
			  
"<BR>" +
"click here to"+ "<a href="+"'UserRegisteration.jsp'"+">RegisterNewUser</a>" +
 "<br>"+
 "<form action ="+"index.jsp"+"  "+ "method="+">"+
 "<input type=" + "\"submit\"" + " name = \"GOHOME\"" + "value=\"Go to Home\">" +
 "</form>"
				+"</BODY></HTML>");
		
		

	}
	

	
}
