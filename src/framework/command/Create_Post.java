package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

public class Create_Post implements Command {

  @Override
  public String perform(HttpServletRequest request,HttpServletResponse response) 
  {
    String projectName = request.getParameter("title");
    String description = request.getParameter("description");
    
    String planUploads = request.getParameter("planUploads");
    
    return "/WEB-INF/views/add_project.jsp";
  }
}
