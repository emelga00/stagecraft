package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/******************
 * Author BDS
 ******************/


public class Create implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) 
	{		
		return "/WEB-INF/views/add_project.jsp";
	}
}
