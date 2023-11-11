
Tech stack used:
   JSP, SERVLETS, MYSQL 8, JDBC, ApacheTomcat 9,Eureka server for micro service registeration and integration,
   HTML4, HTML5
   
 Instructions To run Project:
 1.Run other/TablesCreate.java file as java application to create respective tables in MYSQL.
 2.Right click the project and RunAS -->RunAsserver (select tom cat server)-->finish : Application war file is run in tom cat server.
 3.hit 'http://localhost:8080/MailBoxNotify/'  in your browser.





errors I faced in project and its fixes :
1)java.lang.ClassNotFoundException: com.mysql.jdbc.Driver 
fix: make sure mysql connector jar file included in project -->properties -->classpath (here) and also
in webapp/WEB-INF/lib/mysql-connector-java-8.0.19.jar

2)org.apache.jasper.JasperException: /UserScreen.jsp (line: [55], column: [1]) Attribute [username] invalid for tag [setDataSource] according to TLD
fix:
add jar file jstl-1.2 jar to WEB-INF/lib and also in properties
  add below lines of code in respective jsp page :
        <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
		<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
		
 rename respective tags correctly and also variable names : its not username just user as below:
     <sql:setDataSource var="snapshot" driver = "com.mysql.cj.jdbc.Driver"
 		url = "jdbc:mysql://localhost:3306/mailcenter"
 		user = "root"
 		password = ""
 		/>
 		
 		
SILLY MISTAKES:
1)restarted my computer and trying to login to application but couldn't ..facing below error:
Communications link failure
The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.

FIX:
   XAMP and MYSQL servers are in off..turned them ON.
 2)*For servelet InsertLettersToMailBox.java --> I used doGet method...so I need not add method="post" in respective jsp-->insertMails.jsp 
 
 3)While connecting to DB ..syntaxes are same but declare variable names twice for 2 statements.
 Statement stmtOne = c.createStatement(); 
 Statement stmtTwo = c.createStatement();
 ResultSet rsetOne = stmtOne.executeQuery("	SQL QUERY 1");
 ResultSet rsetTwo = stmtTwo.executeQuery("  Query 2");
 
 
 
 
Enhancements and addons:
* All all necessary supporting wings and beauty
 1.Do all validations and messages correctly.
 2.Able to fit all business constraints.
 3.Auto scripts to check if tables exists and delete , create accordingly ..instead of manually deleting tables and running other/TablesCreate.java code.
 4.Improve CSS and HTML5
 5.Provide messaging /email services.
 6.Prevent pages from clicking and going back and front (i don't remember but there is a specific term)
 7.Highlight Owner mails , Add individual phone numbers to send alerts.
 8.Able to insert multiple mails at once in to mail box
 9.Insert mail: From and To address : add images (UCM, BOFA : Person photos)
 10.Admin screen..to update Apt flatno, owner name,emails, mailbox details etc.. || Highlevel monitoring of mailboxes..just count of letters in each mailbox .
 admin should have capability to add extra functionalities or remove existing ones.(USE JOINS for extreme querying if required) 
 || admin should be able to send alerts manually on button click with configured messages(events, ocassions ,important news etc..)
 11.**Implement threads ..which runs continuously and could monitor the insertions into mailbox,track user operations and simultaneously send notifications.
 
 12.***Try not to implement java code in jsps ..keep the pages lite..all updations,conditions should happen at servlet level ||
 where ever required use Ajax calls, javascript..to make application more dynamic.
 13.Follow All Industry standards at every where before deploying(every validations, security checks at all levels application level, database level etc... )
 "so far its just a project...not a product!!..MAKE IT A PRODUCT ..and SELL"
 14.Performance issues...Some sessions are not clearing.
 15.Manage flags and attributes also in Database.
 16.Keep even deleted mails track in database(DB), use analytics on DB to show visual image ..who gets more mails.
 17.Try to make it a SPA.(single page application)
 18.Improve sessions for login /logout.
 19.Add many services possible and make application more attractive.(recurring....never ending...)
 20.Add this exception feature:
      1.If last letter received is today , (EDGE CASE: LETTER RECEIVED , but missed while adding)but there is a letter 
      which has to be added was few weeks or month ago date, in that case:
         a.implement a button saying exception .
         b.then in conditions we will add ..if exception then display in box letters.
 
 
 
 
 
 
 
 
 scope of selling:
 1.IIIT hyd mails tracking --> hostels linking
 2.Cyan south creek mail box and other apartments
 3.Improve necessary hardware also.
 
 
 important points:
 1.We can transfer data from one jsp to another jsp pages using request,sessions, application
 refer for scope of usage : https://codippa.com/how-to-send-data-from-one-jsp-page-to-another/
 2.While retriving those in other jsp page we need to type cast the value
eg:
    session.setAttribute("email",email);(onepage) 
    String email = (String)session.getAttribute("email");; (anotherpage)
    challange faced: above method tried with request instead of session but didn't worked.
    
 3.	observation : When drop down is selected to set the values of alert days , inbox full count...page is refreshing but respective count values are not reflected immediatly..because 
 taking time to connect to database, execute query.
 
 
 
 
 data modeling:
 1.Primary keys , unique keys for table 'owner' || DEFAULT values for password, inbox full cont, alert days count,NOT NULL Attributes.
 2.primary key as letter id for table 'mails' || Auto increment of letter id .
   * these restrictions helps to handle exceptions at data base side instead of application level.
   
 Application level:
 1.Implemented sessions to share attributes between jsp pages.
 2.Used cookies to handle session time for 3 mins.
 3.Followed MVC architecture.
 4.If possible we implement microservice with spring boot
 
 
 Author:
  Sai Prasanna Kumar Korlakunta
  University of Central Missouri,LeesSummit,MO
  Date:27th April 2022
 
 
 
