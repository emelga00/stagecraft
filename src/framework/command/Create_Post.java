package framework.command;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Project;

import data.ProjectsDB;
import framework.controller.Command;

public class Create_Post implements Command 
{
  //add any file types that should be considered valid for the banner picture here
  private List<String> _validImgExtensions = Arrays.asList("jpeg", "jpg", "gif", "bmp", "png");
  
  public String perform(HttpServletRequest request,HttpServletResponse response) 
  {
  	HttpSession session = request.getSession();
  	
	boolean emptyName        = false;
	boolean emptyDescription = false;
	boolean invalidFileType  = false;
	
    String projectName       = request.getParameter("name");
    String description       = request.getParameter("description");
    String bannerPicFileName = request.getParameter("bannerPicture");

    //check if the project name is empty/null
    if (projectName == null || projectName.isEmpty())
    {
    	emptyName  = true;
    }
    
    //repeat for the description
    if (description == null || description.isEmpty())
    {
    	emptyDescription = true;
    }
    
    byte[] bannerPicture = null; //holds bytes from file
    String fileExtension = null;
    
    //banner picture is optional
    if (bannerPicFileName != null && !bannerPicFileName.isEmpty())
    {
        /*
         * We have to make sure that the file being submitted is a valid
         * image file (valid extensions are stored in a list above)
         */
     
        fileExtension = bannerPicFileName.substring(bannerPicFileName.lastIndexOf('.') + 1);
        
        boolean validImage = false;

        //loop through each valid extension in the list
        for (String validExt : _validImgExtensions)
        {
        	//if the uploaded file extension equals, the 
        	//current item in the list, store the bytes
        	//from the file and the extension
        	if (fileExtension.equalsIgnoreCase(validExt))
        	{
        		Path filePath = Paths.get(bannerPicFileName);
        		try 
        		{
        			//read the bytes from the file
    				bannerPicture = Files.readAllBytes(filePath);
    				
    				//mark the file as a valid image and break the loop
    				validImage = true;
    	    		break;
    			} 
        		catch (IOException e) 
    			{
        			//on an error, return to the view all projects page
        			return "Explore";
    			}
        	}
        }
        
        if (!validImage)
        {
        	invalidFileType = true;
        }
    }
    
    if (emptyName || emptyDescription || invalidFileType)
    {
    	//set error flags out in request scope and return to add project
    	request.setAttribute("emptyName", emptyName);
    	request.setAttribute("emptyDescription", emptyDescription);
    	request.setAttribute("invalidFileType", invalidFileType);
    	
    	return "/WEB-INF/views/add_project.jsp";
    }
    else
    {
    	//no errors were found, add the project to the database
    	Project project = new Project();
    	
    	project.setName(projectName);
    	project.setDesc(description);
    	project.setBannerPicture(bannerPicture);
    	project.setBannerPicExt(fileExtension);
    	project.setUserID((int)session.getAttribute("currentUserID"));
    	
    	ProjectsDB.addProject(project);
    	
    	Project newProject = ProjectsDB.getProjectByBean(project);
    	
    	request.setAttribute("projectID", newProject.getProjectID());
    	
    	//go to the add submission page
    	return "Submission_Add";
    }
  }
}
