package data;

import java.sql.*; 
import java.util.ArrayList;

import beans.User;

public class UserDB {
	/***************************************************************************************
	 * Class........................................................................UserDB *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * 																					   *
	 * This Class contain all of the methods for interacting with the      				   *
	 * user table in the database   								           			   *
	 ***************************************************************************************/
	
	public static synchronized ArrayList<User> getUsers(){
		/***********************************************************************
		 * Method.....................................................getUsers *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retrieves a list of all user beans                      *
		 * 																	   *
		 *     Return Value                                                    *
		 *     ArrayList<User> user - Returns an arraylist of user beans        *
		 ***********************************************************************/
			Connection connection;
		 	User user = null;
		 	ArrayList<User> userList = new ArrayList<User>();
		 	PreparedStatement statement = null;
			String preparedSQL = "Select u.User_ID, u.First_Name, u.Last_Name, " +
					"u.Phone, u.Address, u.City, u.State, u.ZIP, u.Date From user u, credential c Where u.User_ID = c.User_ID;";
			
			try{
		    	connection = DBConnector.getConnection();
		    	statement = connection.prepareStatement(preparedSQL);
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
	
	public static synchronized User getUserByUserID(int userID){
		/***********************************************************************
		 * Method..............................................getUserByUserID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retrieves a User Bean object based on the userID        *
		 *                                                                     *
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
			 * Method...............................................getUserProfile *
			 * Author..........................................................JLH *
			 * --------------------------------------------------------------------*
			 * This method returns a User Bean object based on the userID          *
			 *                                                                     *
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
	public static synchronized int getUserID(String fName, String lName, int cred_ID){
		/***********************************************************************
		 * Method....................................................getUserID *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retieves a user ID based on fName, lName and credID     *
		 *                                                                     *
		 *     Return Value                                                    *
		 *     (int) user_ID - Returns a user's id number                      *
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
	public static synchronized int addUserFandL(String fName, String lName, int cred_ID, String cDate){
		/***********************************************************************
		 * Method.................................................addUserFandL *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method adds a user's first and last name, their credential Id  *
		 * and the date they registered.                                       *
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
		 * Method...................................................updateUser *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method modifies an existing user record in the User table.     *
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
		 * Method......................................................delUser *
		 * Author..........................................................JLH *
		 *---------------------------------------------------------------------*
		 * This method deletes all records for a user id in the User table,    *
		 *     and the Credential table                                        *
		 *     Return Value                                                    *
		 *     (String) status - Results of 1 or multiple deletions     *
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
