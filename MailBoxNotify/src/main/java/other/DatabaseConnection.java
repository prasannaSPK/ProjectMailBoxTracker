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

public class DatabaseConnection {
	public static Connection initializeDatabase()  throws SQLException, ClassNotFoundException {
	
			
			 final String driver =  "com.mysql.cj.jdbc.Driver";
			 final String username = "root";
			 final String password = "";
			 String dBurl = "jdbc:mysql://localhost:3306/mailcenter";
			 Class.forName(driver);
			 Connection con = DriverManager.getConnection(dBurl,username,password);
			 
			 return con;
			
		
		
		 
	}
}
