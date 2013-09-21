package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/******************
 * Author JLH
 ******************/


public class User_Add implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		return "/WEB-INF/views/user_add.jsp";
	}
}
