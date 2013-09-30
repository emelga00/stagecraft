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
					credentials.setEmail(rs.getString(2));
					credentials.setPass(rs.getString(3));
					credentials.setUserID(rs.getInt(4));
					credentials.setRole(rs.getString(5));
					credentials.setValid(rs.getInt(6));
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
		System.out.println("Client name is ");
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
			    
			   
				String preparedSQL = "";
				PreparedStatement statement=null;	
				try{
					connection=DBConnector.getConnection();
					statement = connection.prepareStatement(preparedSQL);
					statement.setString(1, cred.getEmail());
					statement.setString(2, cred.getPass());
					statement.setInt(3, cred.getUserID());
					statement.setString(4, cred.getRole());
					status = statement.executeUpdate();
					statement.close();
					connection.close();
				}catch (SQLException ex){
					System.out.println("Error: " + ex);
					System.out.println("Query: " + statement.toString());
				}

			return status;
		}		
	
	public static synchronized int updateCred(Credentials cred){
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
			    
			   
				String preparedSQL = "";
				PreparedStatement statement=null;	
				try{
					connection=DBConnector.getConnection();
					statement = connection.prepareStatement(preparedSQL);		
					statement.setString(1, cred.getEmail());
					statement.setString(2, cred.getPass());
					statement.setInt(3, cred.getUserID());
					statement.setString(4, cred.getRole());
					statement.setInt(5, cred.getCredID());
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

	
	
	public static synchronized Credentials getCredentialByUserID(String userID){
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
			String preparedSQL = "";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setInt(1,Integer.parseInt(userID));
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
	
}
