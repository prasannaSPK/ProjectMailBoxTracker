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

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/showchangepasswordscreen")
public class ShowChangePasswordScreen extends HttpServlet{
	@Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		System.out.println("welcome to show change password!!");
		String email = request.getParameter("email");
		System.out.println(email);
	
		
		try {
			
			 HttpSession session = request.getSession();
			   
			   // as a key, value pairs we are sending
			   session.setAttribute("email",email);
			
			   response.sendRedirect("ResetPassword.jsp");
			   // we have another way..instead of redirecting to another jsp ..we can create that JSP page here.
			   
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
