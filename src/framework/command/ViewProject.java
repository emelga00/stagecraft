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
    String nextView = "/WEB-INF/views/view_project.jsp";
    
    String projectID = (String)request.getParameter("projectID");
    
    //if there is no project id, send them back to the view all page
    if (projectID == null)
    {
      nextView = "/WEB-INF/views/view_projects.jsp";
    }
    
    Project project = ProjectsDB.getProjByPID(projectID); 
    
    request.setAttribute("project", project);

    return nextView;
  }
}
