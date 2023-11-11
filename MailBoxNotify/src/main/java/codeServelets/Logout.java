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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
		
		@Override
		public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			HttpSession session = request.getSession();
			System.out.println("welcome to logout!!");
			Cookie ck = new Cookie("auth","username");
			ck.setMaxAge(0); // removing cookie by making age is 0
			response.addCookie(ck);
			response.sendRedirect("login.jsp");
		}
}
