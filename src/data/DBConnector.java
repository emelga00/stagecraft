package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	/***************************************************************************************
	 * Class...................................................................DBConnector *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class provides the data access layer code for the Client table.  It provides   *
	 * the following methods:															   *
	 * 																					   *
	 *		getConnection	    - Connects to the stagecraft database                         *
	 *                                                                                     *
	 ***************************************************************************************/
	
	public static Connection getConnection(){
		/***********************************************************************
		 * Method................................................getConnection *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method obtains a connection to the database                    *
		 *                                                                     *
		 * Return Value 													   *
		 * (Connection) conn:  Returns a connection to the database or null if *
		 *                     one cannot be obtained.                         *
		 ***********************************************************************/
		Connection conn=null;
		 
		try {
		    Class.forName("com.mysql.jdbc.Driver"); 
		    conn = DriverManager.getConnection("jdbc:mysql://70.178.114.2:3306/stagecraft?user=root&password=Security_Pr0bs12!@");
		}
		catch (SQLException ex) {
		    System.out.println("Error: " + ex);
		    return null;
		}
		catch(NullPointerException ex) {
			System.out.println("Error: " + ex);
		    	return null;
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	    return conn;		
	}
}
