package data;

import java.sql.*; 
import java.util.ArrayList;
import beans.*;

public class SubmissionsDB {
	/***************************************************************************************
	 * Class.................................................................SubmissionsDB *
	 * Author..........................................................................BDS *
	 * ----------------------------------------------------------------------------------- *
	 ***************************************************************************************/

	//getSubmissionByTypeSortCateogry-- will be used to pull by categoryID in view submission page
	public static synchronized ArrayList<Submission> getSubmissionsByTypeSortCategory(String type)
	{
		/***********************************************************************
		 * Method.............................getSubmissionsByTypeSortCategory *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method returns an array of  submission Bean object based on    *
		 * the type and sorted by category                                     *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) projectID - the id of the project to search for        *
		 *     Return Value                                                    *
		 *     ArrayList<Submission> subList - Returns a subList of Submission *
		 *                                     beans                           *
		 ***********************************************************************/
		Connection connection;
		Submission            submission  = null;
		ArrayList<Submission> subList     = new ArrayList<Submission>();
		PreparedStatement     statement   = null;
		String                preparedSQL = "SELECT * FROM submission WHERE type = '"+type+"' sort by category;";

		try
		{
			connection   = DBConnector.getConnection  ();
			statement    = connection.prepareStatement(preparedSQL);
			statement.setString                         (1, type);
			ResultSet rs = statement.executeQuery     ();
			while(rs.next())
			{
				submission = new Submission();
				submission.setSubID   (rs.getInt   (1));
				submission.setProjID  (rs.getInt   (2));
				submission.setUserID  (rs.getInt   (3));
				submission.setURL     (rs.getString(4));
				submission.setBlob    (rs.getBytes (5));
				submission.setType    (rs.getString(6));
				submission.setDate    (rs.getString(7));
				submission.setCategory(rs.getString(8));
			}	
			rs.close        ();
			statement.close ();
			connection.close();
		}
		catch (SQLException ex)
		{
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return subList;
	}
	
//getProjectByPID -- will be used for pull by projID in project update form
  public static synchronized ArrayList<Submission> getSubmissionsByPID(String projectID)
  {
    /***********************************************************************
     * Method..........................................getSubmissionsByPID *
     * Author..........................................................BDS *
     * --------------------------------------------------------------------*
     * This method calls a Submission Bean object based on the ProjectID   *
     * variable                                                            *
     *                                                                     *
     *     Required parameters                                             *
     *     (String) projectID - the id of the project to search for        *
     *     Return Value                                                    *
     *     ArrayList<Submission> subList - Returns a subList of Submission *
     *                                     beans                           *
     ***********************************************************************/
    Connection connection;
    Submission            submission  = null;
    ArrayList<Submission> subList     = new ArrayList<Submission>();
    PreparedStatement     statement   = null;
    String                preparedSQL = "";

    try
    {
      connection   = DBConnector.getConnection  ();
      statement    = connection.prepareStatement(preparedSQL);
      statement.setInt                          (1, Integer.parseInt(projectID));
      ResultSet rs = statement.executeQuery     ();
      while(rs.next())
      {
        submission = new Submission();
        submission.setSubID   (rs.getInt   (1));
        submission.setProjID  (rs.getInt   (2));
        submission.setUserID  (rs.getInt   (3));
        submission.setURL     (rs.getString(4));
        submission.setBlob    (rs.getBytes (5));
        submission.setType    (rs.getString(6));
        submission.setDate    (rs.getString(7));
        submission.setCategory(rs.getString(8));
      } 
      rs.close        ();
      statement.close ();
      connection.close();
    }
    catch (SQLException ex)
    {
      System.out.println("Error: " + ex);
      System.out.println("Query: " + statement.toString());
    }
    return subList;
  }


	//getSubmissionsByBean -- will be used for pulling individual submissions on a separate tab
	public static synchronized Submission getSubmissionsByBean(Submission submission)
	{
		/***********************************************************************
		 * Method..........................................getSubmissionByBean *
		 * Author..........................................................BDS *
		 * --------------------------------------------------------------------*
		 * This method a calls a Submission Bean object based on the           *
		 * submission                                                          *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) clientID - the id of the client to search for          *
		 *     Return Value                                                    *
		 *     (User) user - Returns a submission bean object.                 *
		 ***********************************************************************/

		Connection        connection;	
		Submission        tempSubmission = new Submission();
		PreparedStatement statement      = null;
		String            preparedSQL    = "";

		try
		{
			connection   = DBConnector.getConnection   ();
			statement    = connection.prepareStatement (preparedSQL);
			statement.setInt   (1, submission.getSubID   ());
			statement.setInt   (2, submission.getProjID  ());
			statement.setInt   (3, submission.getUserID  ());
			statement.setString(4, submission.getURL     ());
			statement.setBytes (5, submission.getBlob    ());
			statement.setString(6, submission.getType    ());
			statement.setString(7, submission.getDate    ());
			statement.setString(8, submission.getCategory());
			
			ResultSet rs = statement.executeQuery      ();
			while(rs.next())
			{
				tempSubmission = new Submission();
				tempSubmission.setSubID   (rs.getInt   (1));
				tempSubmission.setProjID  (rs.getInt   (2));
				tempSubmission.setUserID  (rs.getInt   (3));
				tempSubmission.setURL     (rs.getString(4));
				tempSubmission.setBlob    (rs.getBytes (5));
				tempSubmission.setType    (rs.getString(6));
				tempSubmission.setDate    (rs.getString(7));
				tempSubmission.setCategory(rs.getString(8));
			}

			rs.close        ();		
			statement.close ();
			connection.close();
		}
		catch (SQLException ex)
		{
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}

		return tempSubmission;
	}


	public static synchronized int addSubmission(Submission submission)
	{
		/***********************************************************************
		 * Method................................................addSubmission *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method adds a submission to the Submission table.              *
		 *     Required parameters                                             *
		 *     (Submission) submission - Submission bean containing the info   *
		 *                               to be added                           *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/

		Connection        connection;
		int               status      = 0;
		String            preparedSQL = "INSERT INTO submission (subID,projID,userID,URL,blob,type,date,category) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement   = null;	
		try
		{
			connection = DBConnector.getConnection  ();
			statement  = connection.prepareStatement(preparedSQL);
			statement.setInt   (1, submission.getSubID   ());
			statement.setInt   (2, submission.getProjID  ());
			statement.setInt   (3, submission.getUserID  ());
			statement.setString(4, submission.getURL     ());
			statement.setBytes (5, submission.getBlob    ());
			statement.setString(6, submission.getType    ());
			statement.setString(7, submission.getDate    ());
			statement.setString(8, submission.getCategory());
			
			status     = statement.executeUpdate    ();
			statement.close                         ();
			connection.close                        ();
		}
		catch (SQLException ex)
		{
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return status;
	}


	public static synchronized int updateSubmission(Submission submission)
	{
		/***********************************************************************
		 * Method.............................................updateSubmission *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method modifies an existing submission record in the           *
		 * Submission table.                                                   *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (Submission) Submission - Submission bean with the info to be   *
		 *                               modified                              *
		 *     Return Value                                                    *
		 *     (int) status - The number of records modified by the query      *
		 ***********************************************************************/

		Connection        connection;
		int               status      = 0;
		String            preparedSQL = "";
		PreparedStatement statement   = null;	

		try
		{
			connection = DBConnector.getConnection  ();
			statement  = connection.prepareStatement(preparedSQL);
			statement.setInt   (1, submission.getSubID   ());
			statement.setInt   (2, submission.getProjID  ());
			statement.setInt   (3, submission.getUserID  ());
			statement.setString(4, submission.getURL     ());
			statement.setBytes (5, submission.getBlob    ());
			statement.setString(6, submission.getType    ());
			statement.setString(7, submission.getDate    ());
			statement.setString(8, submission.getCategory());
			
			status    = statement.executeUpdate     ();
			statement.close                         ();
			connection.close                        ();
		}
		catch (SQLException ex)
		{
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		return status;
	}


	public static synchronized String delSubmission(int submissionID)
	{
		/***********************************************************************
		 * Method................................................delSubmission *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method deletes all records for a submissionID in the           *
		 * Submission table                                                    *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (int) submissionID - Primary Key for Submission record          *
		 *     Return Value                                                    *
		 *     (String) status - Results of deletions                          *
		 **********************************************************************/

		Connection connection;
		int        results     = 0;
		String     status      = "";
		String     preparedSQL = "DELETE FROM submission WHERE subID=?";

		PreparedStatement statement = null;	
		try
		{
			connection = DBConnector.getConnection  ();
			statement  = connection.prepareStatement(preparedSQL);
			statement.setInt                        (1, submissionID);
			results    = statement.executeUpdate    ();
			statement.close                         ();
			connection.close                        ();
		}
		catch (SQLException ex)
		{
			System.out.println("Error: " + ex);
			System.out.println("Query: " + statement.toString());
		}
		if(status == "")
			status = "Error deleteing submission# " + submissionID;
		else
			status = "Success deleting " + status + " for submission# " + submissionID;
		return status;
	}
}