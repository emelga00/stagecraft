package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Project;

import framework.controller.Command;

public class Create implements Command 
{
	public String perform(HttpServletRequest request,HttpServletResponse response) 
	{			
		return "/WEB-INF/views/add_project.jsp";
	}
}
