package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.Student;


public class StudentDAO {
	private static Connection connection=null;
	
	public synchronized ArrayList<Student> getStudents() {
	 	ArrayList<Student> students = new ArrayList<Student>();
	 	Student student;
	 	PreparedStatement statement=null;
		String preparedSQL = "SELECT * FROM students;";
		
	    try{
	    	connection = getConnection();
	    	statement = connection.prepareStatement(preparedSQL);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				student = new Student();
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setStudentID(rs.getString("studentID"));
				student.setCity(rs.getString("city"));
				student.setState(rs.getString("state"));
				student.setAge(rs.getInt("age"));
				students.add(student);
			}	
			rs.close();		
			statement.close();
			connection.close();
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return students;
	}
	
	public synchronized Student getStudent(String studentID) {
	 	Student student=null;
	 	PreparedStatement statement=null;
		String preparedSQL = "SELECT * FROM students WHERE studentID = ?;";
		
	    try{
	    	connection = getConnection();
	    	statement = connection.prepareStatement(preparedSQL);
	    	statement.setString(1, studentID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				student = new Student();
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setStudentID(rs.getString("studentID"));
				student.setCity(rs.getString("city"));
				student.setState(rs.getString("state"));
				student.setAge(rs.getInt("age"));
			}	
			rs.close();		
			statement.close();
			connection.close();
		}catch (SQLException ex){
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return student;
	}

	
	public synchronized int addStudent(Student student){
		int status=0;
		String preparedSQL = "INSERT INTO students(studentID,firstName,lastName,age,city,state) VALUES(?,?,?,?,?,?);";
		PreparedStatement statement=null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, student.getStudentID());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getAge());
			statement.setString(5, student.getCity());
			statement.setString(6, student.getState());
			status = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public synchronized int updateStudent(Student student){
		int status=0;
		String preparedSQL = "UPDATE students set firstName=?,lastName=?,age=?,city=?,state=? WHERE studentID = ?;";
		PreparedStatement statement=null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setInt(3, student.getAge());
			statement.setString(4, student.getCity());
			statement.setString(5, student.getState());
			statement.setString(6, student.getStudentID());
			status = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public synchronized int deleteStudent(String studentID){
		int status=0;
		String preparedSQL = "DELETE from students where studentID =?;";
		PreparedStatement statement=null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(preparedSQL);
			statement.setString(1, studentID);
			status = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}	
	
	public static Connection getConnection(){
		 Connection conn=null;
		 
	        try {
	        	Class.forName("com.mysql.jdbc.Driver"); 
	            conn = DriverManager.getConnection("jdbc:mysql:70.178.199.218:3306/root?user=roott&password=br0k3n1");
	        }
	        catch (SQLException ex) {
	            System.out.println("Error: " + ex);
	            return null;
	        }
	        catch(NullPointerException ex) {
	        	System.out.println("Error: " + ex);
	        	return null;
	        } catch (ClassNotFoundException ex) {
	        	System.out.println("Error: " + ex);
			}
	        return conn;		
		}		 

}
