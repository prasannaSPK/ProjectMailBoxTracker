/************************************************************

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
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.DatabaseConnection;

@WebServlet("/changepassword")
public class ChangePassword extends HttpServlet{
	
	@Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		System.out.println("welcome to change password!!");
		String email = request.getParameter("emailaddress");
		String newpassword = request.getParameter("newpassword");
//		System.out.println(email);
	
		
		try {
			
			System.out.println("welcome....." + email);
			
			   DatabaseConnection  dbc = new DatabaseConnection();
			   Connection con = dbc.initializeDatabase();
			   Statement stmt = con.createStatement();
			   
			   stmt.execute("update OWNER set PASSWORD = '"+newpassword+"'" +"where EMAIL ='"+email+"'");
			   con.close();
			   
			   
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();// for reponse sake we throws IOException
			String title = "Successfully updated PASSWORD!!";
			String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
			out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
					+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H2 ALIGN=\"CENTER\">" + title + "<BR>" +
					"click here to"+ "<a href="+"'login.jsp'"+">login</a>" + "<br>"+
	                "<form action ="+"index.jsp"+"  "+ "method="+">"+
	                "<input type=" + "\"submit\"" + " name = \"GOHOME\"" + "value=\"Go to Home\">" +
	                "</form>"
					+"</BODY></HTML>");
			return;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
