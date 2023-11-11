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
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import other.DatabaseConnection;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet {
	Boolean flag = false; // based on this flag,message values we display notification in userscreen.jsp 
	String message = null;
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		   String email = request.getParameter("EmailAddress");
		   String password = request.getParameter("Password");
		   try {
			   
			   DatabaseConnection  dbc = new DatabaseConnection();
			   Connection c = dbc.initializeDatabase();
			   Statement stmt = c.createStatement();
			   ResultSet rs = stmt.executeQuery("select * from OWNER where EMAIL = '"+email+"' and PASSWORD = '"+password+"'");
			 
			  int count = 0;
			  int MAIL_BOX_NO = -10;
			  int LIMIT_INBOX_COUNT = 0;
			  int LIMIT_ALERT_DAYS = 0;
			  String OWNER_NAME = null;
			   while (rs.next()) {
				   MAIL_BOX_NO = rs.getInt("MAIL_BOX_NO");
				   LIMIT_INBOX_COUNT = rs.getInt("LIMIT_INBOX_COUNT");
				   LIMIT_ALERT_DAYS = rs.getInt("LIMIT_ALERT_DAYS");
				   OWNER_NAME = rs.getString("OWNER_NAME");
				   count++;
				}
			   System.out.println("count value is : " +count);
			   if(count==1) {
				   Cookie ck = new Cookie("auth",email);// for sake of sessions
				   ck.setMaxAge(60*3); // session time 60 sec = 1min
				   response.addCookie(ck);
				   
				   ConditionChecksForNotifyingUsers(email,MAIL_BOX_NO,LIMIT_INBOX_COUNT,LIMIT_ALERT_DAYS);
				   HttpSession session = request.getSession();
				   // as a key, value pairs we are sending
				   session.setAttribute("EMAIL",email);// sending person email to other page ..simce we need to retreive respective mailbox letters.
				   session.setAttribute("MAIL_BOX_NO",MAIL_BOX_NO);
				   session.setAttribute("inbox_full_count",LIMIT_INBOX_COUNT);
				   session.setAttribute("alert_me_after_days", LIMIT_ALERT_DAYS);
				   session.setAttribute("OWNER_NAME", OWNER_NAME);
				   session.setAttribute("flag",this.flag);
				   session.setAttribute("message",this.message);
				   
				  // I should verify conditions and send 
				   //1.inbox count and user configured count
				   //2.calculate difference b/w system date(today date) and last letter inserted date -->send difference days , configured notification days count.	   
				  
				   response.sendRedirect("UserScreen.jsp");
					return;
			   }
			   
			   
			   
			   
			   response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				String title = "Invalid Credentials !";
				String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
				out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
						+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" + 
						"<h3>  please enter correct Email and Password!! </h3>" +
						"<BR>" +
	 	                "<form action ="+"index.jsp"+"  "+ "method="+">"+
	 	                "<input type=" + "\"submit\"" + " name = \"GOHOME\"" + "value=\"Go to Home\">" +
	 	                "</form>"
						+"</BODY></HTML>");	  
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		  
	}
	
	
	
	public void  ConditionChecksForNotifyingUsers(String email,int mailbox_num,int inbox_full_count,int days_for_auto_alert) {
		/*
		1.return boolean + message
		 * a.True : number of mails/letters received  >= configured inbox_full_count || message: your inbox is full
		 * b.True:  no.of letters received < configured inbox_full_count && difference (Todays date - last letter received date) >= configured alert days || message : your inbox is not full ..but the last letter  received on YYYY-MM-DD.
		 * c.False || message :"you have no letters in mail box...we will notify if any."
		 * 
		 * 
		 */
		
		/*
		 * 2. IN JSP side we display alert based on conditions sent to JSP.
		 * 
		 * 
		 */
		
		try {
			 
			DatabaseConnection  dbc = new DatabaseConnection();
			   Connection c = dbc.initializeDatabase();
			   Statement stmtOne = c.createStatement();
			   Statement stmtTwo = c.createStatement();
			    ResultSet rsetOne = stmtOne.executeQuery("select count(*) from mails where mail_box_no ='"+mailbox_num+"'");
			    ResultSet rsetTwo = stmtTwo.executeQuery("select * from mails where mail_box_no ='"+mailbox_num+"'"+"order by DELIVERY_DATE_OF_LETTER desc");// desc --> because we need recent letter first
			    int LettersInMailBox = -1;//setting initial value to a negative number
			    Date LastLetterReceivedDate = null;// default we put as null
			    long millis = System.currentTimeMillis();
				Date today = new Date(millis);
			    System.out.println("todays date is :" +today);
			    
			    	while(rsetOne.next()) {
			    		LettersInMailBox = rsetOne.getInt(1);
			    	}
			    	
			    	
			    	while(rsetTwo.next()) {
			    		LastLetterReceivedDate = rsetTwo.getDate("DELIVERY_DATE_OF_LETTER");
			    		break;
			    		/*Note we are breaking the loop because all we need is just one date ,i.e the last inserted letter DATE*/
			    	}
			    	
			    	long difference = calculatingNotificationPeriod(today,LastLetterReceivedDate);
			    	System.out.println("the dates difference is : " + difference);
			      System.out.println("configured inbox full count is:" + inbox_full_count);
			 	   System.out.println("letters in inbox is :" +LettersInMailBox);
			 	   System.out.println("Last letter received date is:" + LastLetterReceivedDate);
			   if(LettersInMailBox>=inbox_full_count) {
				   this.flag = true;
				   this.message = LettersInMailBox +"";
				   System.out.println("ALERT TYPE:INBOX FULL ");
				   System.err.println("You have "+LettersInMailBox +"letters in inbox..pls collect");
				   return;
				    
			   }
			   
			  
			   else if(LettersInMailBox < inbox_full_count) {
				   System.out.println("difference is: "+difference +" auto alert days is:"+ days_for_auto_alert);
				   if(difference >= days_for_auto_alert) {
					   this.flag = true;
					   this.message = ""+LettersInMailBox;
					   System.out.println("ALERT TYPE:Configured Alert days ");
					   System.err.println("You have "+LettersInMailBox +"letters in inbox..please collect");
					   return;
				   }
				   else {
					   this.flag = false;
					   this.message = "no_mails";
					   System.err.println("You have No mails");
					   return;
					   
				   }
			   }
			  
			   
			   
			   System.out.println(this.message);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		
		
		
		
		
		    
		   
	}
	
	
	public long calculatingNotificationPeriod(Date today,Date LastLetterReceivedDate ) {
		String today_date = today + "";
		String letter_date = LastLetterReceivedDate + "";
		System.out.println("today date :" +today_date +"..."+"last letter date is:" +letter_date);
		LocalDate ld1 = LocalDate.parse(today_date);
		LocalDate ld2 = LocalDate.parse(letter_date);
		
		long days = Period.between(ld2,ld1).getDays();
		System.out.println("days is:" + days);
		if(days<0) {
			return 0; // because we don't permit -ve(negative) case..i.e letter can't be delivered in future date 
		}
		return days;
		//return 0;
	}

}
