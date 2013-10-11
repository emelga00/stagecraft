package data;

import java.sql.*; 
import java.util.*;
import beans.*;

public class CredentialsDB {
	
	public static synchronized Credentials authenticate(String userName, String password){
		/***********************************************************************
		 * Method.............................................authenticate     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method authenticates a user via username and password          *
		 * 																	   *
		 * Return Value 													   *
		 * (User) user:  Returns a user bean of the user that logged in.       *
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
		 * Method.............................................authenticate     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method authenticates a user via username and password          *
		 * 																	   *
		 * Return Value 													   *
		 * (User) user:  Returns a user bean of the user that logged in.       *
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
		 * Method...............................................getClientByUID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Client Bean object based on the clientID              *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) clientID - the id of the client to search for          *
		 *     Return Value                                                    *
		 *     (User) user - Returns a user bean object.                       *
		 ***********************************************************************/
		
			int cred_ID=0;
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select Credentials_ID From credential Where Email = ? And Pass= ? And Role= ? And Validated= ? And RegKey= ?";
			
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
					//add credentials columns
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
		 * Method...............................................getClientByUID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Client Bean object based on the clientID              *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) clientID - the id of the client to search for          *
		 *     Return Value                                                    *
		 *     (User) user - Returns a user bean object.                       *
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
					//add credentials columns
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
		 * Method...............................................getClientByUID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Client Bean object based on the clientID              *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) clientID - the id of the client to search for          *
		 *     Return Value                                                    *
		 *     (User) user - Returns a user bean object.                       *
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
					//add credentials columns
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
		 * Method...............................................getClientByUID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Client Bean object based on the clientID              *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) clientID - the id of the client to search for          *
		 *     Return Value                                                    *
		 *     (User) user - Returns a user bean object.                       *
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
					//add credentials columns
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
	
	
	public static synchronized Credentials getCedentialByCredID(String credID){
		/***********************************************************************
		 * Method........................................getCredentialByCredID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
		 ***********************************************************************/
			Connection connection;
			Credentials cred = null;
		 	PreparedStatement statement = null;
			String preparedSQL = "";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1,Integer.parseInt(credID));
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					cred = new Credentials();
					cred.setCredID(rs.getInt(1));
					cred.setEmail(rs.getString(2));
					cred.setPass(rs.getString(3));
					cred.setUserID(rs.getInt(4));
					cred.setRole(rs.getString(5));
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
	public static synchronized Credentials getCedentialByUser_ID(int userID){
		/***********************************************************************
		 * Method........................................getCredentialByCredID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
		 ***********************************************************************/
			Connection connection;
			Credentials cred = null;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select * From user Where User_ID = ?";
			
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
	
	public static synchronized ArrayList<Credentials> getCredentialByAlpha(String alpha){
		/***********************************************************************
		 * Method.........................................getCredentialByAlpha *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
		 ***********************************************************************/
	
			Connection connection;
	 		Credentials cred=null;
	 		ArrayList<Credentials> credList = new ArrayList<Credentials>();
	 	 	PreparedStatement statement = null;
			String preparedSQL = "";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1,alpha+"%");
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					cred = new Credentials();
					cred.setCredID(rs.getInt(1));
					cred.setClientName(rs.getString(2));
					cred.setEmail(rs.getString(3));
					cred.setPass(rs.getString(4));
					cred.setUserID(rs.getInt(5));
					cred.setRole(rs.getString(6));
					credList.add(cred);
				}	
				rs.close();		
				statement.close();
				connection.close();
				System.out.println("Client name is "+ cred.getUsername());
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return credList;
		}
	
	
	public static synchronized int addCred(Credentials cred){
		/***********************************************************************
		 * Method......................................................addCred *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method adds new users to the database 			               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials  bean containing the info      *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			    
			   
				String preparedSQL = "INSERT INTO credential (Email, Pass, User_ID, Role, Validated, RegKey) VALUES(?,?,?,?,?,?) ";
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
		 * Method..................................................updateCred  *
		 * Author.........................................................JLH  *
		 *---------------------------------------------------------------------*
		 * This method adds new products to the inventory table.               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials bean containing the info       *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
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
	public static synchronized int validated(int userID, String key){
		/***********************************************************************
		 * Method..................................................updateCred  *
		 * Author.........................................................JLH  *
		 *---------------------------------------------------------------------*
		 * This method adds new products to the inventory table.               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials bean containing the info       *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
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
		 * Method..................................................updateCred  *
		 * Author.........................................................JLH  *
		 *---------------------------------------------------------------------*
		 * This method adds new products to the inventory table.               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials bean containing the info       *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
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
		 * Method..................................................updateCred  *
		 * Author.........................................................JLH  *
		 *---------------------------------------------------------------------*
		 * This method adds new products to the inventory table.               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials bean containing the info       *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
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
	public static synchronized int deleteCred(int credID){
		/***********************************************************************
		 * Method...................................................deleteCred *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method adds new products to the inventory table.               *
		 *     Required parameters                                             *
		 *     (Credentials) cred - Credentials bean containing the info       *
		 *     							to be added                            *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/
			int status=0;	
			Connection connection; 
			    
			   
				String preparedSQL = "";
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

	

	public static synchronized ArrayList<Credentials> listCreds(){
	/***********************************************************************
	 * Method....................................................listCreds *
	 * Author..........................................................JLH *
	 *---------------------------------------------------------------------*
	 * This method retrieves a list of Credentials in the database		   *
	 * 														  			   *
	 *  Required parameters                                                *
	 *  none                                                               *
	 *  Return Value                                                       *
	 *  (ArrayList) credList - Returns an arrayList of credentials		   *
	 *     						    One bean for each record found.        *
	 ***********************************************************************/
		Connection connection;
	 	Credentials cred=null;
	 	ArrayList<Credentials> credList = new ArrayList<Credentials>();
	 	PreparedStatement statement=null;
		String preparedSQL = "";
		
	    try{
	    	connection=DBConnector.getConnection();
	    	statement = connection.prepareStatement(preparedSQL);
	    
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				cred = new Credentials();
				cred.setCredID(rs.getInt(1));
				cred.setClientName(rs.getString(2));
				cred.setEmail(rs.getString(3));
				cred.setPass(rs.getString(4));
				cred.setUserID(rs.getInt(5));
				cred.setRole(rs.getString(6));
				credList.add(cred);			
			}	
			rs.close();		
			statement.close();
			connection.close();
			System.out.println("Client name is "+ cred.getUsername());
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		
		return credList;
	}	

	
	
	public static synchronized Credentials getCredentialByUserIDKey(int userID, String key){
		/***********************************************************************
		 * Method......................................getCredentialByClientID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
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
	public static synchronized Credentials getCredentialByUserID(int userID){
		/***********************************************************************
		 * Method......................................getCredentialByClientID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
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
	public static synchronized int checkCred(String email){
		/***********************************************************************
		 * Method......................................getCredentialByClientID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
		 ***********************************************************************/
			Connection connection;
			int status = 1;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select Count(*) From credential Where Email = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1,email);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					status = rs.getInt(1);
				}	
				rs.close();
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
				
			}
			return status;
		}
	public static synchronized int getUserID(String email){
		/***********************************************************************
		 * Method......................................getCredentialByClientID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method a Credentials Bean object based on the credID           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     String credID                                                   *
		 *     Return Value                                                    *
		 *     (Credentials) cred - Returns a user bean object.                *
		 ***********************************************************************/
			Connection connection;
			int userID = 0;
		 	PreparedStatement statement = null;
			String preparedSQL = "Select User_ID From credential Where Email = ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1,email);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					userID = rs.getInt(1);
				}	
				rs.close();
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
				
			}
			return userID;
		}
}
