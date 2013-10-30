package framework.command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.controller.Command;
import data.*;
import beans.*;

public class ViewSubmissions implements Command 
{
  public String perform(HttpServletRequest request, HttpServletResponse response) 
  {
    HttpSession session = request.getSession();
    String projectID = (String) request.getAttribute("projectID");
    session.setAttribute("projectID", projectID);

    return "/WEB-INF/views/view_submissions.jsp";
  }
}
