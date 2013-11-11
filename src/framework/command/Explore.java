package framework.command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;
import data.*;
import beans.*;

public class Explore implements Command 
{
  public String perform(HttpServletRequest request, HttpServletResponse response) 
  {
    //get an array list of all projects from the DB
    ArrayList<Project> projects = ProjectsDB.getAllProjects();
    
    //set the arraylist in request scope
    request.setAttribute("projects", projects);

    //return the view_projects page as the next view
    return "/WEB-INF/views/view_projects.jsp";
  }
}
