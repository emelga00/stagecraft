package data;

import java.sql.*;
import java.util.ArrayList;
import beans.Submission;

public class SubmissionDB
{
  public static synchronized ArrayList<Submission> getSubmissionsByTypeSortCategory(String type, int projID)
  {
    Submission submission = new Submission();
    ArrayList<Submission> submissions = new ArrayList<Submission>();
    
    PreparedStatement statement = null;
    Connection connection;
    String preparedSQL = "SELECT s.subID, s.projID, s.userID, s.URL, s.blob, s.type, s.date, s.category, CONCAT(u.First_Name, \" \", u.Last_Name) FROM submission s JOIN user u ON s.userID = u.User_ID WHERE s.`type` = ? AND s.projID = ?;";
    
    try
    {
      connection = DBConnector.getConnection();
      statement = connection.prepareStatement(preparedSQL);
      statement.setString(1, type);
      statement.setInt(2, projID);
      
      ResultSet rs = statement.executeQuery();
      while(rs.next())
        {
          submission.setSubID	  (rs.getInt(1));
          submission.setProjID	(rs.getInt(2));
          submission.setUserID	(rs.getInt(3));
          submission.setURL		  (rs.getString(4));
          submission.setBlob	  (rs.getBytes(5));
          submission.setType	  (rs.getString(6));
          submission.setDate    (rs.getString(7));
          submission.setCategory(rs.getString(8));
          submission.setUserName(rs.getString(9));
          submissions.add(submission);
        }
      rs.close();
      statement.close();
      connection.close();
    }
    catch(SQLException ex)
    {
      System.out.println("Error: " +ex);
      System.out.println("Query: " +statement.toString());
    }
    
    return submissions;
  }
  public static synchronized int addSubmissions(ArrayList<Submission> submissions)
  {
    Submission submission = new Submission();
    
    PreparedStatement statement = null;
    Connection connection;
    String preparedSQL = "INSERT INTO submissions VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    int status = 0;
    ResultSet rs = null;
    
    try
    {
      connection = DBConnector.getConnection();
      for(int i = 0; i< submissions.size(); i++)
        {
          submission = submissions.get(i);
          statement = connection.prepareStatement(preparedSQL);
          
          statement.setInt   (1, submission.getSubID());
          statement.setInt   (2, submission.getProjID());
          statement.setInt   (3, submission.getUserID());
          statement.setString(4, submission.getURL());    //A SUBMISSION SHOULD CONTAIN EITHER BE URL OR BLOB, NOT BOTH
          statement.setBytes (5, submission.getBlob());
          statement.setString(6, submission.getType());
          statement.setString(7, submission.getDate());
          statement.setString(8, submission.getCategory());
          
          status += statement.executeUpdate();
          rs = statement.executeQuery();
        }
      rs.close();
      statement.close();
      connection.close();
    }
    catch(SQLException ex)
    {
      System.out.println("Error: " +ex);
      System.out.println("Query: " +statement.toString());
    }
    
    return status;
  }
}