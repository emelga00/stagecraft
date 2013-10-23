package data;

import java.sql.*; 
import java.util.ArrayList;
import beans.Project;

public class ProjectsDB {
	/***************************************************************************************
	 * Class....................................................................ProjectsDB *
	 * Author..........................................................................BDS *
	 * ----------------------------------------------------------------------------------- *
	 ***************************************************************************************/
	
  public static synchronized ArrayList<Project> getAllProjects()
  {
    ArrayList<Project> projects = new ArrayList<Project>();
    
    StringBuilder query = new StringBuilder();

    query.append("SELECT p.projid, p.name, p.description, ");
    query.append("(SELECT CONCAT_WS(' ', first_name, last_name) FROM user WHERE user_id = p.userID), ");
    query.append("o.name, p.createddate, p.lastupdated, ");
    query.append("(SELECT CONCAT_WS(' ', first_name, last_name) FROM user WHERE user_id = p.updateduserid), ");
    query.append("(SELECT s.blob FROM submission s WHERE p.bannerpicid = s.subid) ");
    query.append("FROM projects p ");
    query.append("JOIN organizations o ON p.orgID = o.organizationid ");
    query.append("ORDER BY p.createddate, p.lastupdated ");
    
    try
    {
      Connection connection       = DBConnector.getConnection();
      PreparedStatement statement = connection.prepareStatement(query.toString());      
      ResultSet resultSet         = statement.executeQuery();
      
      while(resultSet.next())
      {
        Project project = new Project();
        
        project.setProjectID(resultSet.getInt(1));
        project.setName(resultSet.getString(2)); 
        project.setDesc(resultSet.getString(3));   
        project.setSubmittedBy(resultSet.getString(4));
        project.setOrganization(resultSet.getString(5));
        project.setCreatedDate(resultSet.getString(6));
        project.setLastUpdatedDate(resultSet.getString(7));
        project.setLastUpdatedBy(resultSet.getString(8));
        project.setBannerPicture(resultSet.getBytes(9));

        if(project.getName() == null) 
        {
          project.setName("New Project");
        }

        projects.add(project);  
      }
      
      resultSet.close();   
      statement.close();
      connection.close();
    }
    catch (SQLException ex)
    {
      System.out.println("Error: " + ex);
      System.out.println("Query: " + query.toString());
    }
    
    return projects;
  }
	
	public static synchronized ArrayList<Project> getProjectsByAlpha(String projectName)
	{
		/***********************************************************************
		 * Method............................................getProjectByAlpha *
		 * Author..........................................................JLH *
		 * --------------------------------------------------------------------*
		 * This method retrieves a list of project beans based on projectName  *
		 * If the projectName is an empty string the contents of the entire    *
		 * table will be returned, otherwise the method will return all records*
		 * that begin with the projectName string.(Use LIKE)                   *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) projectName - the string to find                       *
		 *                                                                     *
		 *     Return Value                                                    *
		 *     ArrayList<Project> projectList - Returns an projectList of      *
		 *                                      project beans                  *
		 ***********************************************************************/
			Connection connection;
		 	Project            project     = null;
		 	ArrayList<Project> projList    = new ArrayList<Project>();
		 	PreparedStatement  statement   = null;
			String             preparedSQL = "";
			
			try
			{
		    	connection   = DBConnector.getConnection  ();
		    	statement    = connection.prepareStatement(preparedSQL);
		    	statement.setString                       (1, projectName+"%");
		    	ResultSet rs = statement.executeQuery     ();
  				
		    	while(rs.next())
  				{
  					project = new Project();

  					//kill of any nulls in Name column
  					if(project.getName() == null) 
  					{
  						project.setName("New Project");
  					}
  					
  					//add info columns
  					projList.add(project);			
  				}	
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return projList;
		}
	
	public static synchronized ArrayList<Project> getProjectByAlphaExact(String projectName)
	{
		/***********************************************************************
		 * Method.......................................getProjectByAlphaExact *
		 * Author..........................................................BDS *
		 * --------------------------------------------------------------------*
		 * This method retrieves a list of project beans based on projectName. *
		 * If the projectName is an empty string the contents of the entire    *
		 * table will be returned, otherwise the method will return all records*
		 * that begin with the projectName string.(Use LIKE)                   *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) projectName - the name of the project to search for    *
		 *     Return Value                                                    *
		 *     ArrayList<Project> projectList - Returns an arrayList of project*
		 *                                      beans                          *
		 ***********************************************************************/
			Connection connection;
		 	Project project             = null;
		 	ArrayList<Project> projList = new ArrayList<Project>();
		 	PreparedStatement statement = null;
			String preparedSQL          = "";
			
			try
			{
		    	connection   = DBConnector.getConnection  ();
		    	statement    = connection.prepareStatement(preparedSQL);
		    	statement.setString                       (1, projectName);
		    	ResultSet rs = statement.executeQuery     ();
		    	while(rs.next())
		    	{
  					project = new Project();
  				
  					//kill of any nulls in DV_UN column
  					if(project.getName() == null)
  					{
  						project.setName("New Project");
  					}
  					
  					//add info columns
  					projList.add(project);			
  				}
				rs.close();		
				statement.close();
				connection.close();
			}
		    catch (SQLException ex){
				System.out.println("Error: " + ex);
				System.out.println("Query: " + statement.toString());
			}
			return projList;
		}

	//getProjectByPID -- will be used for pull by projID in project update form
	public static synchronized Project getProjByPID(String projectID)
		{
			/***********************************************************************
			 * Method..............................................getProjectByPID *
			 * Author..........................................................BDS *
			 * --------------------------------------------------------------------*
			 * This method calls a Project Bean object based on the projectID      *
			 * variable                                                            *
			 *                                                                     *
			 *     Required parameters                                             *
			 *     (String) projectID - the id of the project to search for        *
			 *     Return Value                                                    *
			 *     (Project) project  - Returns a project bean object.             *
			 ***********************************************************************/
				Connection connection;
				Project           project     = null;
			 	PreparedStatement statement   = null;
				String            preparedSQL = "Select * from projects where projID = "+projectID;
				
			    try
			    {
			    	connection   = DBConnector.getConnection  ();
			    	statement    = connection.prepareStatement(preparedSQL);
			    	statement.setInt                          (1, Integer.parseInt(projectID));
			    	ResultSet rs = statement.executeQuery     ();
			    	while(rs.next())
			    	{
  						project = new Project();

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
				return project;
			}
	
	//getProjectByPID -- will be used for pull by projID in project update form
	public static synchronized Project getProjectByBean(Project project)
	{
		/***********************************************************************
		 * Method................................................getProjByBean *
		 * Author..........................................................BDS *
		 * --------------------------------------------------------------------*
		 * This method a calls a Project Bean object based on the project      *
		 *                                                                     *
		 *     Required parameters                                             *
		 *     (String) projectID - the id of the project to search for        *
		 *     Return Value                                                    *
		 *     (Project) project - Returns a project bean object.              *
		 ***********************************************************************/
		
  	  Connection        connection;	
  	  Project           tempProject = new Project();
		 	PreparedStatement statement   = null;
			String            preparedSQL = "";
			
		    try
		    {
		    	connection   = DBConnector.getConnection();
		    	statement    = connection.prepareStatement(preparedSQL);
		    	statement.setString(1, project.getName());
  				statement.setString(2, project.getDesc());

  				ResultSet rs = statement.executeQuery     ();
  				while(rs.next())
  				{
  					tempProject = new Project();
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
		    
			return tempProject;
		}
	
	
	public static synchronized int addProject(Project project)
	{
		/***********************************************************************
		 * Method......................................................addProj *
		 * Author..........................................................BDS *
		 *---------------------------------------------------------------------*
		 * This method adds a project to the Project table.                    *
		 *     Required parameters                                             *
		 *     (Project) project - Project bean containing the info to be      *
		 *                         added                                       *
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
			statement.setString(2, project.getDesc());

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
			statement.setString(2, project.getDesc());

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