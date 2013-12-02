package framework.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDB;

import beans.User;


import framework.controller.Command;

public class ViewUsers implements Command {
	/***************************************************************************************
	 * Class.....................................................................ViewUsers *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class creates an ArrayList of users in the user DB and puts it in scope then   *
	 * sends the nextview to the view_users.jsp                                            *
	 *                                                                                     *
	 *    perform - required method from Command interface                                 *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	 public String perform(HttpServletRequest request,HttpServletResponse response) {

		ArrayList<User> users = UserDB.getUsers();

	    request.setAttribute("userList", users);
	    return "/WEB-INF/views/view_users.jsp";
	 }
}
