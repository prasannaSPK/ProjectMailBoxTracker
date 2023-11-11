<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>

<H1 ALIGN="CENTER">Password Reset Page</H1>
<%
 String email = (String)session.getAttribute("email");
%>

<!-- <p> the value of email is <%=email %></p> -->

<FORM ACTION="changepassword" METHOD="post">
  Email Address:  <INPUT TYPE="TEXT" NAME="emailaddress" value=<%=email %> ><BR>
  Enter new Password:  <INPUT TYPE="PASSWORD" NAME="newpassword"><BR>
  <CENTER><INPUT TYPE="SUBMIT" VALUE="Update"></CENTER>
</FORM>
</head>

<form action = "index.jsp" method="">
		<input type="submit" name = "GOHOME" value="Go to Home">
	</form>
<body>

</body>
</html>