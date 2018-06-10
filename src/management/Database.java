package management;

import java.awt.List;
//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;

import javax.sql.rowset.*; 
import com.sun.rowset.*;

import java.util.*;

public class Database {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

 //  Database credentials
 private final String USER;
 private final String PASS;
 private final String HOST;
 
 private Connection con;
 
 Database(String user, String pass, String host) throws BadLoginException {
	 this.USER = user;
	 this.PASS = pass;
	 this.HOST = host;
	// Class.forName("com.mysql.jdbc.Driver").newInstance();
	 //Try if the connection works
	 
	 try {
		connect();
	}  catch (SQLException e) {
			throw new BadLoginException();
		}
	}
 
 Database() throws BadLoginException
 {
	 System.out.print("Geen argumenten gedetecteerd. Wilt u zelf een databaseconnectie invoeren? Op github.com/Goldfive staat de CREATE SQL."
	 		+ "+\n\n Host:");
	 try (Scanner sc = new Scanner(System.in))
	 {
		String host = "jdbc:mysql://";
		host += sc.nextLine();
		System.out.print("Username: ");
		String user = sc.nextLine();
		System.out.print("Password: ");
		String pass = sc.nextLine();
		
		this.USER = user;
		this.PASS = pass;
		this.HOST = host;
	 }
	 
	try {
		connect();
	}  catch (SQLException e) {
			throw new BadLoginException();
	}
 }


 public void connect() throws BadLoginException {
	 try
	 {
		 con = DriverManager.getConnection(HOST, USER, PASS);
		 System.out.println(con);
	 }
	 catch (BadLoginException e)
	 {
			 throw e;
	 } catch (Exception e) { 
	 }
   /* while (rs.next()) {
        int x = rs.getInt("value");
        String s = rs.getString("type");
        String b = rs.getString("time");
        System.out.println(x + s + b);
    }*/}
 
 void close() 
 {
	 try {
		 con.close();
	 } catch (SQLException se) {
		 se.printStackTrace();
	 } catch (Exception e) {
		 e.printStackTrace();
	 }
 }
 
 public boolean query(String q)
 {
	 
	 try {
		 connect();
		 Statement stmt = con.createStatement();
		 stmt.executeUpdate(q);
		 return true;
	 } catch (SQLException se) {
		 se.printStackTrace();
	 } catch (Exception e) {
		e.printStackTrace(); 
	 } finally {
		 close();
	 }
	 return false;
 }
 

 @SuppressWarnings("restriction")
public CachedRowSet select(String ss)
 {
	 CachedRowSet crs = null;
	 
	 System.out.println(ss);
	 try
	 {
		connect();
	    Statement stmt = con.createStatement();
	   ResultSet a = stmt.executeQuery(ss);
	   crs = new CachedRowSetImpl();
	 }
	 catch (SQLException se)
	 {
		se.printStackTrace(); 
		System.out.println("hier");
	 }
	 catch (Exception e)
	 {
		e.printStackTrace(); 
		System.out.println("id - waarde - tijd - extra");
	 }
	 return null;
 }
}

class BadLoginException extends SQLException{}