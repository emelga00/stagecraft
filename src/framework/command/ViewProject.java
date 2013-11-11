package framework.command;

import framework.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProjectsDB;
import beans.Project;

public class ViewProject  implements Command
{
  public String perform(HttpServletRequest request, HttpServletResponse response)
  {
    //set the default next view to the view project page
    String nextView = "/WEB-INF/views/view_project.jsp";
    
    //get the project id from request scope
    String projectID = (String)request.getParameter("projectID");
     
    if (projectID == null)
    {
      //if there is no project id, send them back to the view all page
      nextView = "/WEB-INF/views/view_projects.jsp";
    }
    else
    {
      //get the project using the id and put it in request scope
      Project project = ProjectsDB.getProjByPID(projectID); 
      
      request.setAttribute("project", project);
    }

    //return the view
    return nextView;
  }
}
