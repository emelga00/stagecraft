package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class UserDAO {
	private static Connection connection=null;
	
	public synchronized User authenticate(String userName, String password) {
	 	User user = null;
	 	PreparedStatement statement=null;
		String preparedSQL = "SELECT * FROM users WHERE userName = ? and password = ?;";
		
	    try{
	    	connection = StudentDAO.getConnection();
	    	statement = connection.prepareStatement(preparedSQL);
	    	statement.setString(1, userName);
	    	statement.setString(2,password);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setLastName(rs.getString("lastName"));
				user.setFirstName(rs.getString("firstName"));
			}	
			rs.close();		
			statement.close();
			connection.close();
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return user;
	}
}
