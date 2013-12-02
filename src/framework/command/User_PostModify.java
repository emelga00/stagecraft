package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CredentialsDB;

import framework.controller.Command;

public class User_PostModify  implements Command{
	/***************************************************************************************
	 * Class...............................................................User_PostModify *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class is responsible for pulling data from user modify forms and altering the  *
	 * database.                                                                           *
	 * 																					   *
	 * perform - required method from Command interface                                    *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String status = "User not updated!";
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
		String user_ID = request.getParameter("userID");
		int userID = Integer.parseInt(user_ID);
		String role = request.getParameter("role");
		String enabled = request.getParameter("enabled");
		int valid = 1;
		if (enabled.equals("yes")){
			valid = 0;
		}
		
		int results = CredentialsDB.modUser(userID, role, valid);
		if(results > 0) {
			status = "Successfully Modified User!";
			session.setAttribute("status", status);
					
		}
		
		return "ViewUsers";
	}
}
