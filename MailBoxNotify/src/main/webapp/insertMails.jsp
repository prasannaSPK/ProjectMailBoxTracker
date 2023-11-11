<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body BGCOLOR="#FDF5E6">
<H1 ALIGN="CENTER">Post Master Screen ...Insert mails</H1>



<FORM ACTION="insertMail">
	
  MailBoxNo: <INPUT TYPE="number" NAME="MailBoxNo"><BR> 
  From :  <INPUT TYPE="TEXT" NAME="FROM"><BR>
  To :  <INPUT TYPE="TEXT" NAME="TO"><BR>
  Date of Letter :<input type="date" id="dateofletter" name="dateofletter"><BR>
  <CENTER><INPUT TYPE="SUBMIT" VALUE="Insert"></CENTER>
</FORM>

<form action = "index.jsp" method="">
		<input type="submit" name = "GOHOME" value="Go to Home">
	</form>
</body>
</html>