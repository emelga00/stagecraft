package data;

import java.sql.*;
import java.util.ArrayList;
import beans.Submission;

public class SubmissionDB
{
  public static synchronized ArrayList<Submission> getSubmissionsByTypeSortCategory(String type, int projID)
  {
    System.out.println("In getSubmissionsByType method");
    
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
    
    System.out.println("Returning Submissions");
    return submissions;
  }
}