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
  //private List<String> _validImgExtensions = Arrays.asList("jpeg", "jpg", "gif", "bmp", "png");
  
  public String perform(HttpServletRequest request,HttpServletResponse response) 
  {
  	HttpSession session = request.getSession();
  	
	boolean emptyName        = false;
	boolean emptyDescription = false;
	boolean invalidFileType  = false;
	
    String projectName       = request.getParameter("name");
    String description       = request.getParameter("description");
    //String bannerPicFileName = request.getParameter("bannerPicture");

    Project project = new Project();
    
	project.setUserID((int)session.getAttribute("currentUserID"));
    
    //check if the project name is empty/null
    if (projectName == null || projectName.isEmpty())
    {
    	emptyName  = true;
    }
    else
    {
    	project.setName(projectName);
    }
    
    //repeat for the description
    if (description == null || description.isEmpty())
    {
    	emptyDescription = true;
    }
    else
    {
    	project.setDesc(description);
    }
    
//    byte[] bannerPicture = null; //holds bytes from file
//    String fileExtension = null;
//    
//    //banner picture is optional
//    if (bannerPicFileName != null && !bannerPicFileName.isEmpty())
//    {
//        /*
//         * We have to make sure that the file being submitted is a valid
//         * image file (valid extensions are stored in a list above)
//         */
//     
//        fileExtension = bannerPicFileName.substring(bannerPicFileName.lastIndexOf('.') + 1);
//        
//        boolean validImage = false;
//
//        //loop through each valid extension in the list
//        for (String validExt : _validImgExtensions)
//        {
//        	//if the uploaded file extension equals, the 
//        	//current item in the list, store the bytes
//        	//from the file and the extension
//        	if (fileExtension.equalsIgnoreCase(validExt))
//        	{
//        		Path filePath = Paths.get(bannerPicFileName);
//        		try 
//        		{
//        			//read the bytes from the file
//    				bannerPicture = Files.readAllBytes(filePath);
//    				
//    				//mark the file as a valid image and break the loop
//    				validImage = true;
//    	    		break;
//    			} 
//        		catch (IOException e) 
//    			{
//        			//on an error, return to the view all projects page
//        			return "Explore";
//    			}
//        	}
//        }
//        
//        if (!validImage)
//        {
//        	invalidFileType = true;
//        }
//        else
//  	  {
//          project.setBannerPicture(bannerPicture);
//          project.setBannerPicExt(fileExtension);
//        }
//    }
    
    if (emptyName || emptyDescription || invalidFileType)
    {
    	//set the invalid project out in session scope
    	session.setAttribute("invalidProject", project);
    	
    	//redirect to the add project form
    	return "Create";
    }
    else
    {
    	//no errors were found, add the project to the database
    	ProjectsDB.addProject(project);
    	
    	Project newProject = ProjectsDB.getProjectByBean(project);
    	
    	request.setAttribute("projectID", newProject.getProjectID());
    	
    	//go to the add submission page
    	return "Explore";
    }
  }
}
