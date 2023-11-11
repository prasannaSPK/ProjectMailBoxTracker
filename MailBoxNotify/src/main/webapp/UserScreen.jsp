<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%

String driver = "com.mysql.cj.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/mailcenter";

String userid = "root";
String password = "";
try {
	Class.forName(driver);
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>User Screen</title>
</head>
<body>

<%
String email = (String) session.getAttribute("EMAIL");
boolean flag = (boolean)session.getAttribute("flag");
String message = (String)session.getAttribute("message");
int MAIL_BOX_NO = (int) session.getAttribute("MAIL_BOX_NO");
%>

<%
// setting to  the default values..these attributes keeps changing as per our operations..

    int inbox_full_count = -200;
	int alert_me_after_days = -300;
	String OWNER_NAME = null;
	int LettersInMailBox = -400;
	boolean NotificationDisplay = false;
	//boolean noSwitchOperations = true;
%>
  
  <%
	//fetching required fields from sessions 
	
   /*
	String OWNER_NAME = (String)session.getAttribute("OWNER_NAME");
	int MAIL_BOX_NO = (int) session.getAttribute("MAIL_BOX_NO");
	int inbox_full_count = (int)session.getAttribute("inbox_full_count");
	int alert_me_after_days = (int)session.getAttribute("alert_me_after_days");
	*/
	
	// improving design to display alert days and  inbox count
	connection = DriverManager.getConnection(connectionUrl, userid, password);
	Statement stmt1 = connection.createStatement();
	Statement stmt2 = connection.createStatement();
	ResultSet rs1 = stmt1.executeQuery("select * from owner where EMAIL='" +email +"'");
	ResultSet rs2 = stmt2.executeQuery("select count(*) from mails where MAIL_BOX_NO ='"+MAIL_BOX_NO+"'");
	//int MAIL_BOX_NO = -100;
	
	  while(rs1.next()){
		  //MAIL_BOX_NO = rs.getInt("MAIL_BOX_NO");
		  inbox_full_count = rs1.getInt("LIMIT_INBOX_COUNT");
		  alert_me_after_days = rs1.getInt("LIMIT_ALERT_DAYS");
		  OWNER_NAME   =  rs1.getString("OWNER_NAME");
	  }
	  
	  while(rs2.next()) {
  		LettersInMailBox = rs2.getInt(1);
  	}
	  
	  if(LettersInMailBox > 0)
		  NotificationDisplay = true;
	
	%>
	<h4>hello ..welcome ,<%=OWNER_NAME %> || InboxCount:<%=inbox_full_count %> || AlertAfterDays:<%=alert_me_after_days %></h4>

 <input type="hidden" id="notification_message" name="notification_message" value= <%=(String)session.getAttribute("message")%> >
 
 
	<%
	Cookie[] cks = request.getCookies();// using cookies for sessions purpose

	if (cks != null) {
		for (int i = 0; i < cks.length; i++) {
			String name = cks[i].getName();
			String value = cks[i].getValue();

			if (name.equals("auth")) {
		break; // exit the loop and continue the page
			}
			if (i == (cks.length - 1)) // if all cookie are not valid redirect to error page
			{
		response.sendRedirect("login.jsp");
		return; // to stop further execution
			}
			//i++;
		}
	} else {
		response.sendRedirect("login.jsp");
		return;
	}
	%>



	
		



	
	<h5>
		email id is :
		<%=email%>
		||    mail box number:
		<%=MAIL_BOX_NO%></h5>

	<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/mailcenter" user="root" password="" />

	<sql:query dataSource="${snapshot}" var="result">
 SELECT * from mails where MAIL_BOX_NO = ? order by DELIVERY_DATE_OF_LETTER desc
 <sql:param value="${MAIL_BOX_NO}" />
	</sql:query>
	
	
	<!-- I put remaining attributes/conditions to null because I don't want to show notification every time on page got refreshed for respective count values updated -->
<%
if(flag==true && LettersInMailBox > 0 && NotificationDisplay==true){
%>

<script type="text/javascript" name="alertbox">
		 alert("you have " + document.getElementById("notification_message").value+" letter/s in mailbox please collect");
</script>

	<table border="1" width="100%">
		<tr>
			<th>FROM_ADDRESS</th>
			<th>TO_ADDRESS</th>
			<th>DELIVERY_DATE_OF_LETTER</th>
		</tr>

		<c:forEach var="row" items="${result.rows}">
			<tr>
				<td><c:out value="${row.FROM_ADDRESS}" /></td>
				<td><c:out value="${row.TO_ADDRESS}" /></td>
				<td><c:out value="${row.DELIVERY_DATE_OF_LETTER}" /></td>
			</tr>
		</c:forEach>
	</table>

<% 	
} else if(flag==false || LettersInMailBox ==0){

%>
<p> <h5>Your Mail Box Is empty</h5></p>

<%} %>
	
<%if(flag==true && LettersInMailBox > 0 && NotificationDisplay==false){ %>

	<table border="1" width="100%">
		<tr>
			<th>FROM_ADDRESS</th>
			<th>TO_ADDRESS</th>
			<th>DELIVERY_DATE_OF_LETTER</th>
		</tr>

		<c:forEach var="row" items="${result.rows}">
			<tr>
				<td><c:out value="${row.FROM_ADDRESS}" /></td>
				<td><c:out value="${row.TO_ADDRESS}" /></td>
				<td><c:out value="${row.DELIVERY_DATE_OF_LETTER}" /></td>
			</tr>
		</c:forEach>
	</table>

<%} %>


<% 
connection = DriverManager.getConnection(connectionUrl, userid, password);

	String InboxFullCount = request.getParameter("InboxFullCount");

	
	Statement st_ifc = connection.createStatement();

	if (InboxFullCount != null) {
		try {
			st_ifc.executeUpdate("update owner set LIMIT_INBOX_COUNT = '" + InboxFullCount + "' where EMAIL='" + email + "'");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	%>
	
	
	<% 
	connection = DriverManager.getConnection(connectionUrl, userid, password);
	
	String SelectedAlertDays = request.getParameter("AlertDays");
	Statement st_ad = connection.createStatement();
	if (SelectedAlertDays != null) {
		try {
			st_ad.executeUpdate("update owner set LIMIT_ALERT_DAYS = '" + SelectedAlertDays + "' where EMAIL='" + email + "'");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
%>


	<form action="logout" method="post">
		<input type="submit" value="Logout">
	</form>
	
	
	<% 
	 //String testing = null;
	// Code for Collect emails functionality
	connection = DriverManager.getConnection(connectionUrl, userid, password);
	Statement stm = connection.createStatement();
	   if(request.getParameter("deleteLetters")!=null){
		   //testing ="prasanna";
		   try{
			   stm.executeUpdate("DELETE from MAILS where MAIL_BOX_NO = '"+MAIL_BOX_NO+"'");
			   connection.close();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	
	%>
	<form method="">
		<input type="submit" name = "deleteLetters" value="Collect your Letters :DELETE">
	</form>
	
	<form action="showchangepasswordscreen" method="post">
	<!-- we pass the email from session we fetched above as value , but it is hidden in html as below
	 -->
	<input type="hidden" name="email" value= <%=email%> />
	<input type="submit"   value="change password : UPDATE">
	</form>
	


	<form method="post" name="SetInBoxFullCount">
		<label>Choose inbox Full Count:</label> <select name="InboxFullCount"
			onchange="document.SetInBoxFullCount.submit()">
			<option value="">select</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select>
	</form>

	

	<form method="post" name="SetAlertDays">
		<label>Set Alert Days:</label> <select name="AlertDays"
			onchange="document.SetAlertDays.submit()">
			<option value="">select</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select>
	</form>
    
<!--  
<p>
		your selected days are:
		<%=SelectedAlertDays %>
	</p>
	
	<p>
		your inbox count value is :
		<%=InboxFullCount%></p>

-->







<!-- Configuring below  variables..inorder to show Java script notification of Alert only once
* we do it by updating NotificationDisplay Flag.
  -->
<%

String btn1 = request.getParameter("InboxFullCount");

String btn2 = request.getParameter("AlertDays");

String btn3   = request.getParameter("deleteLetters");

if(btn1!=null || btn2!=null || btn3!=null ){
	NotificationDisplay = false;
}

	
%>









</body>
</html>