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
    String projectID = (String) request.getParameter("projectID");
    int projID = Integer.parseInt(projectID);
    session.setAttribute("projectID", projectID);
    
    ArrayList<Submission> vidSubmissions  = new ArrayList<Submission>();
    ArrayList<Submission> imgSubmissions  = new ArrayList<Submission>();
    ArrayList<Submission> planSubmissions = new ArrayList<Submission>();
    
    vidSubmissions  = SubmissionDB.getSubmissionsByTypeSortCategory("video", projID);
    imgSubmissions  = SubmissionDB.getSubmissionsByTypeSortCategory("image", projID);
    planSubmissions = SubmissionDB.getSubmissionsByTypeSortCategory("plan" , projID);
    
    session.setAttribute("videos", vidSubmissions);
    session.setAttribute("images", imgSubmissions);
    session.setAttribute("plans" , planSubmissions);
  
    return "/WEB-INF/views/view_submissions.jsp";
  }
}
