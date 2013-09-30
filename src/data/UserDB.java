package data;

import java.sql.*; 
import java.util.ArrayList;
import beans.User;

public class UserDB {
	/***************************************************************************************
	 * Class........................................................................UserDB *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 ***************************************************************************************/
		
	
	public static synchronized ArrayList<User> getUserByAlpha(String companyInitial){
		/***********************************************************************
		 * Method.............................................getClientByAlpha *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retrieves a list of client beans based on companyName   *
		 * If the companyName is an empty string the contents of the entire    *
		 * table will be returned, otherwise the method will return all records*
		 * that begin with the comapnyName string.(Use LIKE)                   *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) companyInitial - the letter of the alphabet to find    *
		 *                               matches for                           *
		 *     Return Value                                                    *
		 *     ArrayList<Client> clientList - Returns an arralist of client    *
		 *                                    beans                            *
		 ***********************************************************************/
			Connection connection;
		 	User user = null;
		 	ArrayList<User> userList = new ArrayList<User>();
		 	PreparedStatement statement = null;
			String preparedSQL = "";
			
			try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, companyInitial+"%");
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					user = new User();
					user.setUser_ID(rs.getInt(1));
					user.setFirst_Name(rs.getString(2));
					user.setLast_Name(rs.getString(3));
					user.setPhone(rs.getString(4));
					user.setAddress(rs.getString(5));
					user.setCity(rs.getString(6));
					user.setState(rs.getString(7));
					user.setZIP(rs.getString(8));
					
					
					user.setDate(rs.getString(9));
					//add credentials columns
					user.setCreds_Credential_ID(rs.getInt(10));
					user.setCreds_Email(rs.getString(11));
					//kill of any nulls in DV_UN column
					if(user.getCreds_Email() == null) {
						user.setCreds_Email("TBD");
					}
					user.setCreds_Pass(rs.getString(12));
					//kill of any nulls in DV_PW column
					if(user.getCreds_Pass() == null) {
						user.setCreds_Pass("TBD");
					}
					user.setRole(rs.getString(13));
					//add info columns
					userList.add(user);			
				}	
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return userList;
		}
	
	public static synchronized ArrayList<User> getUserByAlphaExact(String companyName){
		/***********************************************************************
		 * Method........................................getClientByAlphaExact *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retrieves a list of client beans based on companyName   *
		 * If the companyName is an empty string the contents of the entire    *
		 * table will be returned, otherwise the method will return all records*
		 * that begin with the comapnyName string.(Use LIKE)                   *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) companyName - the name of the client to search for     *
		 *     Return Value                                                    *
		 *     ArrayList<Client> clientList - Returns an arralist of client    *
		 *                                    beans                            *
		 ***********************************************************************/
			Connection connection;
		 	User user = null;
		 	ArrayList<User> userList = new ArrayList<User>();
		 	PreparedStatement statement = null;
			String preparedSQL = "";
			
			try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, companyName);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					user = new User();
					user.setUser_ID(rs.getInt(1));
					user.setFirst_Name(rs.getString(2));
					user.setLast_Name(rs.getString(3));
					user.setPhone(rs.getString(4));
					user.setAddress(rs.getString(5));
					user.setCity(rs.getString(6));
					user.setState(rs.getString(7));
					user.setZIP(rs.getString(8));
					
					//next line from info/lookup
					
					user.setDate(rs.getString(9));
					//add credentials columns
					user.setCreds_Credential_ID(rs.getInt(10));
					user.setCreds_Email(rs.getString(11));
					//kill of any nulls in DV_UN column
					if(user.getCreds_Email() == null) {
						user.setCreds_Email("TBD");
					}
					user.setCreds_Pass(rs.getString(12));
					//kill of any nulls in DV_PW column
					if(user.getCreds_Pass() == null) {
						user.setCreds_Pass("TBD");
					}
					user.setRole(rs.getString(13));
					//add info columns
					userList.add(user);			
				}
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return userList;
		}
	//getClientByUID -- will be used for pull by userID in client update form
		public static synchronized User getUserByUID(String userID){
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
				Connection connection;
				User user = null;
			 	PreparedStatement statement = null;
				String preparedSQL = "";
				
			    try{
			    	connection = DBConnector.getConnection();
			    	statement = connection.prepareStatement(preparedSQL);
			    	statement.setInt(1, Integer.parseInt(userID));
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						user = new User();
						user.setUser_ID(rs.getInt(1));
						user.setFirst_Name(rs.getString(2));
						user.setLast_Name(rs.getString(3));
						user.setPhone(rs.getString(4));
						user.setAddress(rs.getString(5));
						user.setCity(rs.getString(6));
						user.setState(rs.getString(7));
						user.setZIP(rs.getString(8));
						user.setDate(rs.getString(9));
						user.setRole(rs.getString(10));
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
				return user;
			}
	
	//getClientByUID -- will be used for pull by userID in client update form
	public static synchronized User getUserByBean(User user2){
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
		
			User user;
			user = new User();
			Connection connection;
		 	PreparedStatement statement = null;
			String preparedSQL = "";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, user2.getFirst_Name());
				statement.setString(2, user2.getLast_Name());
				statement.setString(3, user2.getPhone());
				statement.setString(4, user2.getAddress());
				statement.setString(5, user2.getCity());
				statement.setString(6, user2.getState());
				statement.setString(7, user2.getZIP());
				statement.setString(8, user2.getDate());
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					user = new User();
					user.setUser_ID(rs.getInt(1));
					user.setFirst_Name(rs.getString(2));
					user.setLast_Name(rs.getString(3));
					user.setPhone(rs.getString(4));
					user.setAddress(rs.getString(5));
					user.setCity(rs.getString(6));
					user.setState(rs.getString(7));
					user.setZIP(rs.getString(8));
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
			return user;
			
		}
	
	
	public static synchronized int addUser(User user){
		/***********************************************************************
		 * Method......................................................addUser *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method adds a client to the Client table.                      *
		 *     Required parameters                                             *
		 *     (Client) client - Client bean containing the info to be added   *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/
		int status = 0;
		Connection connection;

		String preparedSQL = "INSERT INTO user (First_Name, Last_Name, Phone, Address, City, State, Zip, Date) VALUES(?,?,?,?,?,?,?,?) ";
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, user.getFirst_Name());
			statement.setString(2, user.getLast_Name());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getCity());
			statement.setString(6, user.getState());
			statement.setString(7, user.getZIP());
			statement.setString(8, user.getDate());
			status = statement.executeUpdate();
			statement.close();
			connection.close();
		}
		catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return status;
	}
	
	
	public static synchronized int updateUser(User user){
		/***********************************************************************
		 * Method.................................................updateClient *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method modifies an existing client record in the Client table. *
		 *     Required parameters                                             *
		 *     (Client) client - Client bean with the info to be modified      *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/
		int status = 0;
		Connection connection;

		String preparedSQL = "";
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, user.getFirst_Name());
			statement.setString(2, user.getLast_Name());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getCity());
			statement.setString(6, user.getState());
			statement.setString(7, user.getZIP());
			
			statement.setString(8, user.getDate());
			statement.setInt(9, user.getUser_ID());
			status = statement.executeUpdate();
			statement.close();
			connection.close();
		}
		catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return status;
	}
	
	
	public static synchronized String delUser(int userID){
		/***********************************************************************
		 * Method....................................................delClient *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method deletes all records for a client id in the Client table,*
		 *     the Client_Credential table, and the Lookup_Bridge table.       *
		 *     Required parameters                                             *
		 *     (int) clientID - Primary Key for Client record                  *
		 *     Return Value                                                    *
		 *     (String) status - Results of 1 or multiple deletions across     *
		 *                       3 tables                                      *
		 ***********************************************************************/
		int results = 0;
		String status = "";
		Connection connection; 
		
		String preparedSQL2 = "DELETE FROM Credential WHERE User_ID=?";
		String preparedSQL3 = "DELETE FROM User WHERE User_ID=?";
				
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL2);
			statement.setInt(1, userID);
			results = statement.executeUpdate();
			if(results > 0)
				status += "Credentials- ";
			statement = connection.prepareStatement(preparedSQL3);
			statement.setInt(1, userID);
			results = statement.executeUpdate();
			if(results > 0)
				status += "Client- ";
			statement.close();
			connection.close();
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		if(status == "")
			status = "Error deleteing client # " + userID;
		else
			status = "Success deleting " + status + " for client # " + userID;
		return status;
	}
	
	
	
	
}
