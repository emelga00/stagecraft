package framework.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Submission;
import framework.controller.Command;

/******************
 * Author BDS
 ******************/
public class Submission_Add implements Command {

  @Override
  public String perform(HttpServletRequest request,HttpServletResponse response) 
  {   
    return "/WEB-INF/views/submissions_add.jsp";
  }
}
