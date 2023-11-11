/************************************************************
* 
This Project is implemented as a part of Academics by students of University of central Missouri,LeesSummit,MO 
Students: Sai Prasanna Kumar Korlakunta, Gowtami
Instructor: Dr.Sam Ramanujan
Stream Of Study: Big Data Analytics and Information Technology
Date Of Project Submitted: 30th April 2022

References: Professor Sam Ramanujan Lectures, W3 schools,Java T point.

*************************************************/


package codeServelets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/microservicecall")
public class GetOwnersListMicroServiceCall extends HttpServlet {
     
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Micro Service Call";
		String ownerId= request.getParameter("num");
	    HttpURLConnection conn=null;
	    BufferedReader reader=null;
	    StringBuilder strBuf = new StringBuilder();  
	    String apiUrl="http://localhost:8091/api/mailboxowners/" + ownerId;
	   
	   try {
	    URL url = new URL(apiUrl);  
	    conn = (HttpURLConnection)url.openConnection();  
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    if(conn.getResponseCode() != 200) {
	    	System.out.println("connection error: "+conn.getResponseCode());
	    }
	    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	    String output = null;  
	    while ((output = reader.readLine()) != null)  
	        strBuf.append(output);  
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
	
	   //System.out.println(strBuf.toString());
	  
	   JSONObject json=null;
	   String name="";
	   String id="";
	   String address="";
	try {
		json = new JSONObject(strBuf.toString());
		id=json.getString("id");// here the id refers to mail box number.
		name=json.getString("name");
		address=json.getString("address");
		

	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
	                "<UL>\n" +
	                "  <LI><B>MailBox Num:</B>: "
	                + id + "\n" +
	                "  <LI><B>Owner Name:</B>: "
	        
	                + name + "\n" +
	                "  <LI><B>Address:</B>: "
	                + address+ "\n" +
	                "</UL>\n" +
	                "</BODY></HTML>");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		 String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
			    out.println(docType +
			                "<HTML>\n" +
			                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
			                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
			                
			                "<H1 ALIGN=\"CENTER\">" + "Sorry,Invalid Owner Id " + "</H1>\n" +
			                "<H2 ALIGN=\"CENTER\">" + "Owner Doesn't exist " + "</H2>\n" +
			                "</BODY></HTML>");
	}
	}
}
