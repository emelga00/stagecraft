package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.UserDB;

import framework.controller.Command;

public class UserProfile_PostModify  implements Command{
	/***************************************************************************************
	 * Class...............................................................User_PostModify *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class pulls in the data entered into the fields of the user's profile modify   *
	 * form. It then interacts with the database and updates the fields.                   *
	 * 																					   *
	 * 		perform - required method from Command interface                               *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String status = "User not updated!";
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String user_ID = request.getParameter("userid");
		int userID = Integer.parseInt(user_ID);
		
		int results = UserDB.updateUser(fName,lName,phone,address,city,state,zip,userID);
		if(results > 0) {
			status = "Successfully Modified User!";
			session.setAttribute("status", status);
					
		}
		
		return "UserProfile";
	}
}
