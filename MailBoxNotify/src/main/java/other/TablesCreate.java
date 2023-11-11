/************************************************************
* 
This Project is implemented as a part of Academics by students of University of central Missouri,LeesSummit,MO 
Students: Sai Prasanna Kumar Korlakunta, Gowtami
Instructor: Dr.Sam Ramanujan
Stream Of Study: Big Data Analytics and Information Technology
Date Of Project Submitted: 30th April 2022
 

*************************************************/

package other;

import java.sql.*;


public class TablesCreate {
		public static void main(String[] args) {

			try {
				 final String driver =  "com.mysql.cj.jdbc.Driver";
				 final String username = "root";
				 final String password = "";
				 Class.forName(driver);

				String url = "jdbc:mysql://localhost:3306/mailcenter";// you are supposed to create a database named mailcenter in your mysql
				
				Connection con = DriverManager.getConnection(url,username,password);
				Statement stmt = con.createStatement(); 
	
				System.out.println("Creating Tables!!!");
				
				
				String createTableOwner = "CREATE TABLE OWNER " + "( FLAT_NO int NOT NULL UNIQUE, OWNER_NAME varchar(255) NOT NULL,MAIL_BOX_NO int NOT NULL UNIQUE,LIMIT_INBOX_COUNT int DEFAULT 0,LIMIT_ALERT_DAYS int DEFAULT 0,EMAIL varchar(255) NOT NULL UNIQUE , PASSWORD varchar(255) NOT NULL DEFAULT 1234, PRIMARY KEY(FLAT_NO,MAIL_BOX_NO,EMAIL))";
				String createTableMailCenter = "CREATE TABLE MAILS" + "(Letter_ID int NOT NULL AUTO_INCREMENT,MAIL_BOX_NO int NOT NULL,FROM_ADDRESS varchar(255) NOT NULL,TO_ADDRESS varchar(255) NOT NULL,DELIVERY_DATE_OF_LETTER DATE NOT NULL,PRIMARY KEY(Letter_ID))" ;
				
				
				stmt.executeUpdate(createTableOwner);
				stmt.executeUpdate(createTableMailCenter);
				
				
				
			} catch (Exception e) {

				System.err.println( e.getMessage());
				return;
			}
			System.err.println("Tables Created Successfully");
			
		}
}
