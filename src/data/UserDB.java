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
		
	
	public static synchronized ArrayList<User> getUserByAlpha(String userInitial){
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
			String preparedSQL = "Select u.User_ID, u.First_Name, u.Last_Name, " +
					"u.Phone, u.Address, u.City, u.State, u.ZIP, u.Date From user u, credential c Where u.User_ID = c.User_ID AND concat(u.Last_Name, ', ', u.First_Name) Like ?;";
			
			try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, userInitial+"%");
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
		public static synchronized User getUserByUserID(int userID){
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
				String preparedSQL = "Select * From user Where User_ID = ?";
				
			    try{
			    	connection = DBConnector.getConnection();
			    	statement = connection.prepareStatement(preparedSQL);
			    	statement.setInt(1, userID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						user = new User();
						user.setUser_ID(rs.getInt(1));
						user.setCreds_Credential_ID(rs.getInt(2));
						user.setFirst_Name(rs.getString(3));
						user.setLast_Name(rs.getString(4));
						user.setPhone(rs.getString(5));
						user.setAddress(rs.getString(6));
						user.setCity(rs.getString(7));
						user.setState(rs.getString(8));
						user.setZIP(rs.getString(9));
						user.setDate(rs.getString(10));
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
		public static synchronized User getUserProfile(int userID){
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
				String preparedSQL = "Select u.User_ID, u.First_Name, u.Last_Name, u.Phone, u. Address, u.City, " +
						"u.State, u.ZIP, u.Date, c.Role, c.Email From user u, credential c Where u.User_ID = c.User_ID AND u.User_ID = ?";
				
			    try{
			    	connection = DBConnector.getConnection();
			    	statement = connection.prepareStatement(preparedSQL);
			    	statement.setInt(1, userID);
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
						user.setCreds_Email(rs.getString(11));
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
			String preparedSQL = "Select * From user Where First_Name = ? And Last_Name= ? And Phone= ? And Address= ? And City= ? And State= ? And ZIP= ? And Date= ?";
			
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
					user.setDate(rs.getString(9));
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
	
	public static synchronized int getUserID(String fName, String lName, int cred_ID){
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
			String preparedSQL = "Select User_ID From user Where First_Name = ? And Last_Name= ? And Cred_ID= ?";
			
		    try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, fName);
				statement.setString(2, lName);
				statement.setInt(3, cred_ID);
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
			}
			return user_ID;
			
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

		String preparedSQL = "INSERT INTO user (First_Name, Last_Name, Phone, Address, City, State, Zip, Date) VALUES(?,?,?,?,?,?,?,?)";
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
	
	public static synchronized int addUserFandL(String fName, String lName, int cred_ID, String cDate){
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

		String preparedSQL = "INSERT INTO user (First_Name, Last_Name, Cred_ID, Date) VALUES(?,?,?,?)";
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, fName);
			statement.setString(2, lName);
			statement.setInt(3, cred_ID);
			statement.setString(4, cDate);
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
	
	
	
	public static synchronized int updateUser(String fName,String lName,String phone,String address,
			String city,String state,String zip,int user_ID){
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

		String preparedSQL = "Update user Set First_Name = ?, Last_Name = ?, Phone = ?, Address = ?," +
				"City = ?, State = ?, ZIP = ? Where User_ID = ?";
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, fName);
			statement.setString(2, lName);
			statement.setString(3, phone);
			statement.setString(4, address);
			statement.setString(5, city);
			statement.setString(6, state);
			statement.setString(7, zip);
			statement.setInt(8, user_ID);
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
	
	
	public static synchronized int delUser(int userID){
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
		Connection connection; 
		
		String preparedSQL2 = "DELETE FROM Credential WHERE User_ID=?";
		String preparedSQL3 = "DELETE FROM User WHERE User_ID=?";
				
		PreparedStatement statement = null;	
		try{
			connection = DBConnector.getConnection();
			statement = connection.prepareStatement(preparedSQL2);
			statement.setInt(1, userID);
			results = statement.executeUpdate();
			if(results > 0){
				statement = connection.prepareStatement(preparedSQL3);
				statement.setInt(1, userID);
				results = statement.executeUpdate();
				statement.close();
				connection.close();
			}
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		
		return results;
	}
	
	
	
	
}
