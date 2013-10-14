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
    //ArrayList<Project> projects = ProjectsDB.getAllProjects();
    
    //request.setAttribute("projects", projects);

    return "/WEB-INF/views/view_projects.jsp";
  }
}
