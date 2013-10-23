package data;

import java.sql.*; 
import beans.*;
/***********************************************************************
 * Class.............................................CredentialsDB     *
 * Author......................................................JLH     *
 *---------------------------------------------------------------------*
 * This Class contain all of the methods for interacting with the      *
 * credential table in the database								       *
 * 																	   *
 ***********************************************************************/
public class CredentialsDB {
	
	public static synchronized Credentials authenticate(String userName, String password){
		/***********************************************************************
		 * Method.............................................authenticate     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method authenticates a user via username and password          *
		 * 																	   *
		 * Return Value 													   *
		 * (Credentials) credentials:  Returns a credentials bean of the user  *
		 * that logged in.       											   *
		 ***********************************************************************/
			Connection connection;
		 	Credentials credentials=null;
		 	
		 	PreparedStatement statement=null;
			String preparedSQL = "SELECT * FROM Credential WHERE Email = ? and Pass = ?";
			
		    try{
		    	connection=DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, userName);
		    	statement.setString(2, password);
				ResultSet rs = statement.executeQuery();
				if(rs.next()){
					credentials = new Credentials();
					credentials.setCredID(rs.getInt(1));
					credentials.setUserID(rs.getInt(2));
					credentials.setEmail(rs.getString(3));
					credentials.setPass(rs.getString(4));
					credentials.setRole(rs.getString(5));
					credentials.setValid(rs.getInt(6));
					credentials.setRegKey(rs.getString(7));
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}catch (SQLException ex){
					System.out.println("Error: " + ex);
					System.out.println("Query: " + statement.toString());
					credentials = null;
				}	
			return credentials;
	}	
	public static synchronized int checkVerification(String userName){
		/***********************************************************************
		 * Method........................................checkVerification     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves the "validated" field based on the user's 	   *
		 * email address													   *
		 * 																	   *
		 * Return Value 													   *
		 * (int) validated:  Returns an int value that signifies if the user's *
		 * email address has been verified 									   *
		 ***********************************************************************/
			Connection connection;
		 	int valid = 1;
		 	PreparedStatement statement=null;
			String preparedSQL = "SELECT Validated FROM Credential WHERE Email = ?";
			
		    try{
		    	connection=DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, userName);
				ResultSet rs = statement.executeQuery();
				if(rs.next()){
					valid = rs.getInt(1);
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}catch (SQLException ex){
					System.out.println("Error: " + ex);
					System.out.println("Query: " + statement.toString());
					valid = 1;
				}	
			return valid;
	}	
	public static synchronized int getCred_IDByBean(Credentials cred){
		/***********************************************************************
		 * Method.........................................getCred_IDByBean     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves the Credential ID based on the Credential Bean*
		 * 																	   *
		 * Return Value 													   *
		 * (int) cred_ID:  Returns a credential ID, this is an int that is     *
		 * automatically incremented in the database when the credential is    *
		 * created						           							   *
		 ***********************************************************************/
			int cred_ID=0;
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select Credentials_ID From credential Where Email = ? " +
					"And Pass= ? And Role= ? And Validated= ? And RegKey= ?";
			try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, cred.getEmail());
				statement.setString(2, cred.getPass());
				statement.setString(3, cred.getRole());
				statement.setInt(4, cred.getValid());
				statement.setString(5, cred.getRegKey());
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					cred_ID = rs.getInt(1);
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return cred_ID;
			
	}
	public static synchronized String getKeyBYUserID(int user_ID){
		/***********************************************************************
		 * Method...........................................getKeyBYUserID     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves a key based on the user_ID                    *
		 * 																	   *
		 * Return Value 													   *
		 * (String) key:  Returns an alphanumeric string generated when the    *
		 * user is created and removed when the user validates their email     *
		 ***********************************************************************/
		
			String key = "";
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select RegKey From credential Where User_ID = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1, user_ID);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					key = rs.getString(1);
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return key;
			
	}
	public static synchronized String getEmailByUserID(int user_ID){
		/***********************************************************************
		 * Method.........................................getEmailByUserID     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves the user's email based on the user_ID         *
		 * 																	   *
		 * Return Value 													   *
		 * (String) email:  Returns the user's email address				   *
		 ***********************************************************************/
		
			String email = "";
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select Email From credential Where User_ID = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1, user_ID);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					email = rs.getString(1);
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return email;
			
	}
	public static synchronized int getUserIDByEmail(String email){
		/***********************************************************************
		 * Method.........................................getUserIDByEmail     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves the user ID based on the user's email address *
		 * 																	   *
		 * Return Value 													   *
		 * (int) user_ID:  Returns the user's user_ID, an int automatically    *
		 * incremented in the database  									   *
		 ***********************************************************************/
		
			int user_ID = 0;
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select User_ID From credential Where Email = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, email);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					user_ID = rs.getInt(1);
				}	
				rs.close();		
				statement.close();
				connection.close();
				
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
				user_ID=0;
			}
			return user_ID;
			
	}
	public static synchronized Credentials getCedentialByUser_ID(int userID){
		/***********************************************************************
		 * Method...................................getCredentialByUser_ID     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retrieves the Credential Bean based on the userID       *
		 * 																	   *
		 * Return Value 													   *
		 * (Credentials) credentials:  Returns a credentials bean of the user  *
		 * based on the userID, an int automatically incremented in the        *
		 * database       											           *
		 ***********************************************************************/
			Connection connection;
			Credentials cred = null;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select * From credential Where User_ID = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1,userID);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					cred = new Credentials();
					cred.setCredID(rs.getInt(1));
					cred.setUserID(rs.getInt(2));
					cred.setEmail(rs.getString(3));
					cred.setPass(rs.getString(4));
					cred.setRole(rs.getString(5));
					cred.setValid(rs.getInt(6));
					cred.setRegKey(rs.getString(7));
				}	
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return cred;
	}
	public static synchronized int addCred(Credentials cred){
		/***********************************************************************
		 * Method..................................................addCred     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method creates a new entry in the credential database table    *
		 * 																	   *
		 * Return Value 													   *
		 * (int) status:  Returns an int that indicates the success or failure *
		 * of the insert command											   *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			String preparedSQL = "INSERT INTO credential (Email, Pass, User_ID, Role, " +
					"Validated, RegKey) VALUES(?,?,?,?,?,?) ";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);
				statement.setString(1, cred.getEmail());
				statement.setString(2, cred.getPass());
				statement.setInt(3, cred.getUserID());
				statement.setString(4, cred.getRole());
				statement.setInt(5, cred.getValid());
				statement.setString(6, cred.getRegKey());
				status = statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}

			return status;
	}		
	public static synchronized void addUserID(int user_ID, int cred_ID){
		/***********************************************************************
		 * Method................................................adduserID     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method adds the automatically incremented int, user_ID, from 
		 * the user table to the credentials table                             *
		 * 																	   *
		 * Return Value 													   *
		 * (Void)      											 			   *
		 ***********************************************************************/
			Connection connection; 
			String preparedSQL = "Update credential Set User_ID = ? Where Credentials_ID = ?";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);		
				statement.setInt(1, user_ID);
				statement.setInt(2, cred_ID);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
	}
	public static synchronized int modUser(int user_ID, String role, int valid){
		/***********************************************************************
		 * Method................................................adduserID     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method adds the automatically incremented int, user_ID, from 
		 * the user table to the credentials table                             *
		 * 																	   *
		 * Return Value 													   *
		 * (Void)      											 			   *
		 ***********************************************************************/
			Connection connection; 
			int result =0;
			String preparedSQL = "Update credential Set Role = ?, Validated = ? Where User_ID = ?";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);		
				statement.setString(1, role);
				statement.setInt(2, valid);
				statement.setInt(3, user_ID);
				result = statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return result;
	}
	public static synchronized int deleteCred(int credID){
		/***********************************************************************
		 * Method...............................................deleteCred     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method deletes the row in the credentials table based on the   *
		 * credID 															   *
		 * 																	   *
		 * Return Value 													   *
		 * (int) status:  Returns an int that indicates the success or failure *
		 * of the delete command											   *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			    
			   
				String preparedSQL = "DELETE FROM credential WHERE Credentials_ID = ?";
				PreparedStatement statement=null;	
				try{
					connection=DBConnector.getConnection();
					statement = connection.prepareStatement(preparedSQL);
					statement.setInt(1, credID);
					status = statement.executeUpdate();
					statement.close();
					connection.close();
				}catch (SQLException ex){
					System.out.println("Error: " + ex);
					System.out.println("Query: " + statement.toString());
				}

			return status;
	}
	public static synchronized int validated(int userID, String key){
		/***********************************************************************
		 * Method................................................validated     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method changes the validated int from 1 to 0 signifying that   *
		 * the user has validated their email address                          *
		 * 																	   *
		 * Return Value 													   *
		 * (int) status:  Returns an int that indicates the success or failure *
		 * of the updated validated int	     								   *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			String preparedSQL = "Update credential Set Validated = ? Where User_ID = ? And RegKey = ?";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);
				statement.setInt(1, 0);
				statement.setInt(2, userID);
				statement.setString(3, key);
				status = statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return status;
	}
	public static synchronized int setKey(int userID, String key){
		/***********************************************************************
		 * Method...................................................setKey     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method sets an alphanumeric string, key, in the credential 
		 * table. This is the key users need in order to validate their email  *
		 * 																	   *
		 * Return Value 													   *
		 * (int) status:  Returns an int that indicates the success or failure *
		 * of the updated key	      										   *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			String preparedSQL = "Update credential Set RegKey = ? Where User_ID = ?";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);
				statement.setString(1, key);
				statement.setInt(2, userID);
				status = statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return status;
	}
	public static synchronized int resetCred(int userID, String password){
		/***********************************************************************
		 * Method................................................resetCred     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method changes the users password                              *
		 * 																	   *
		 * Return Value 													   *
		 * (int) status:  Returns an int that indicates the success or failure *
		 * of the updated password	  											   *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			String preparedSQL = "Update credential Set Pass = ? Where User_ID = ?";
			PreparedStatement statement=null;	
			try{
				connection=DBConnector.getConnection();
				statement = connection.prepareStatement(preparedSQL);
				statement.setString(1, password);
				statement.setInt(2, userID);
				status = statement.executeUpdate();
				statement.close();
				connection.close();
			}catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return status;
	}
	public static synchronized Credentials getCredentialByUserIDKey(int userID, String key){
		/***********************************************************************
		 * Method..................................getCrdentialByUserIDKey     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method retieves a Credential Bean based on the userID and key  *
		 * 																	   *
		 * Return Value 													   *
		 * (Credentials) credentials:  Returns a credentials bean              *       											   
		 ***********************************************************************/
			Connection connection;
			Credentials cred = null;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select * From credential Where User_ID = ? And RegKey =?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1,userID);
		    	statement.setString(2,key);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					cred = new Credentials();
					cred.setCredID(rs.getInt(1));
					cred.setEmail(rs.getString(2));
					cred.setPass(rs.getString(3));
					cred.setUserID(rs.getInt(4));
					cred.setRole(rs.getString(5));
					cred.setValid(rs.getInt(6));
					cred.setRegKey(rs.getString(7));
				}	
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return cred;
	}	
	public static synchronized int checkCred(String email){
		/***********************************************************************
		 * Method................................................checkCred     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method checks to see if any instances of the email are already *
		 * in the database. This determines if the user's email is already     *
		 * registered 														   *
		 * 																	   *
		 * Return Value 													   *
		 * (int) count:  Returns an int that counts if any instances of the    *
		 * entered email are already in the database                           *
		 * of the insert command	    									   *
		 ***********************************************************************/
			Connection connection;
			int count = 1;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select Count(*) From credential Where Email = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1,email);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					count = rs.getInt(1);
				}	
				rs.close();
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
				
			}
			return count;
		}
}
