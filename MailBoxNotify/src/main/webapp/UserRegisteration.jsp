<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body BGCOLOR="#FDF5E6">

<H1 ALIGN="CENTER">Registration Form</H1>

<FORM ACTION="newuserregister" METHOD="post">
  OwnerName:  <INPUT TYPE="TEXT" NAME="OwnerName"><BR>
  FlatNo: <INPUT TYPE="number" NAME="FlatNo"><BR>
  MailBoxNo: <INPUT TYPE="number" NAME="MailBoxNo"><BR>
  Email Address:  <INPUT TYPE="TEXT" NAME="EmailAddress"><BR>
  <CENTER><INPUT TYPE="SUBMIT" VALUE="Register"></CENTER>
</FORM>

<form action = "index.jsp" method="">
		<input type="submit" name = "GOHOME" value="Go to Home">
	</form>
</body>
</html>