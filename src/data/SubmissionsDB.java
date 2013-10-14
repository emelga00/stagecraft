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
  						submission.setSubID (rs.getInt(1));
  						submission.setProjID(rs.getInt(2));
  						submission.setUserID(rs.getInt(3));
  						submission.setURL   (rs.getString(4));
  						submission.setBlob  (rs.getBytes(5));
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
	public static synchronized Submission getSubmissionsByBean(Submission submission)
	{
		/***********************************************************************
		 * Method................................................getProjByBean *
		 * Author..........................................................BDS *
		 * --------------------------------------------------------------------*
		 * This method a calls a Submission Bean object based on the projectID *
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
		    	connection   = DBConnector.getConnection  ();
		    	statement    = connection.prepareStatement(preparedSQL);
		    	statement.setInt   (1, submission.getSubID ());
  				statement.setInt   (2, submission.getProjID());
  				statement.setInt   (3, submission.getUserID());
  				statement.setString(4, submission.getURL   ());
  				statement.setBytes (5, submission.getBlob  ());
  				ResultSet rs = statement.executeQuery      ();
  				while(rs.next())
  				{
  					tempSubmission = new Submission();
  					tempSubmission.setSubID (rs.getInt   (1));
  					tempSubmission.setProjID(rs.getInt   (2));
  					tempSubmission.setUserID(rs.getInt   (3));
  					tempSubmission.setURL   (rs.getString(4));
  					tempSubmission.setBlob  (rs.getBytes (5));
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
		 * This method adds a submission to the Submissions table.             *
		 *     Required parameters                                             *
		 *     (Submission) submission - Submission bean containing the info   *
		 *                               to be added                           *
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
			//statement.setString(1, project.getName  ());
			//statement.setString(2, project.getDesc  ());
			//statement.setInt   (3, project.getUserID());
			//statement.setInt   (4, project.getOrgID ());
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
	
	
	public static synchronized int updateProject(Project project)
{
		/***********************************************************************
		 * Method................................................updateProject *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method modifies an existing project record in the Project    . *
		 * Table.                                                              *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (Project) project - Project bean with the info to be modified   *
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
			statement.setString(1, project.getName  ());
			statement.setString(2, project.getDesc  ());
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
	
	
	public static synchronized String delProject(int projectID)
	{
		/***********************************************************************
		 * Method...................................................delProject *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method deletes all records for a projectID in the Project      *
		 *     table                                                           *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (int) projectID - Primary Key for Project record                *
		 *     Return Value                                                    *
		 *     (String) status - Results of deletions                          *
		 **********************************************************************/
	  
	  Connection connection;
		int        results     = 0;
		String     status      = "";
		String     preparedSQL = "DELETE FROM Projects WHERE ProjectID=?";
				
		PreparedStatement statement = null;	
		try
		{
			connection = DBConnector.getConnection  ();
			statement  = connection.prepareStatement(preparedSQL);
			statement.setInt                        (1, projectID);
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
			status = "Error deleteing project# " + projectID;
		else
			status = "Success deleting " + status + " for project # " + projectID;
		return status;
	}
}