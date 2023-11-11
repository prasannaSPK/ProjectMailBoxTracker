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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MailsBean;
import other.DatabaseConnection;

@WebServlet("/insertMail")
public class InsertLettersToMailBox extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();// we need to throws IOException
		String title = "Insert Mails";
		
		String MailBoxNo = request.getParameter("MailBoxNo");
		String FromAddress = request.getParameter("FROM");
		String ToAddress = request.getParameter("TO");
		String letter_received_date = request.getParameter("dateofletter");
		System.out.println("date of letter is" + letter_received_date);
		
		try {
			DatabaseConnection dbc = new DatabaseConnection();
			Connection c = dbc.initializeDatabase();
			Statement stmt = c.createStatement();
			MailsBean mb = new MailsBean();
			
			mb.setMAIL_BOX_NO(Integer.parseInt(MailBoxNo));
			mb.setFROM_ADDRESS(FromAddress);
			mb.setTO_ADDRESS(ToAddress);
			
			long millis = System.currentTimeMillis();
			
			LocalDate date = LocalDate.parse(letter_received_date);
			stmt.executeUpdate("INSERT INTO MAILS(MAIL_BOX_NO,FROM_ADDRESS,TO_ADDRESS,DELIVERY_DATE_OF_LETTER)"+ "VALUES('"+ mb.getMAIL_BOX_NO() + "','" + mb.getFROM_ADDRESS()+ "','" + mb.getTO_ADDRESS() + "','" + date +"')");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("failed to insert data");
			
			System.out.println(e.getMessage());
		}
		
		
	    String docType =
	    	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    	      "Transitional//EN\">\n";
	    	    out.println(docType +
	    	                "<HTML>\n" +
	    	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	    	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	    	                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
	    	                
	    	                "Inserted successfull\n" + "<br>"+
	    	                "<form action ="+"index.jsp"+"  "+ "method="+">"+
	    	                "<input type=" + "\"submit\"" + " name = \"GOHOME\"" + "value=\"Go to Home\">" +
	    	                "</form>"+
	    	                "</BODY></HTML>");
	    	    
	    	     
	    		 
	    	
		
		
	}

}
