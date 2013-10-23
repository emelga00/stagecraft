package framework.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDB;

import beans.User;


import framework.controller.Command;

public class ViewUsers implements Command {
	/***************************************************************************************
	 * Class..............................................................ClientViewerList *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class provides next view destination for the controller -- List Clients page   *
	 *   and generates arraylist of client beans for display                               *
	 *                                                                                     *
	 *    perform - required method from Command interface                                 *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	 public String perform(HttpServletRequest request,HttpServletResponse response) {

		String letter ="";
		if(request.getParameter("letter") != null) {
	  		letter = request.getParameter("letter");
	  	}
	    ArrayList<User> users = UserDB.getUserByAlpha(letter);

	    request.setAttribute("userList", users);
	    return "/WEB-INF/views/view_users.jsp";
	 }
}
