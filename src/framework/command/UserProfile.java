package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.UserDB;
import beans.User;
import framework.controller.Command;

public class UserProfile implements Command {
	/***************************************************************************************
	 * Class...................................................................UserProfile *
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

		HttpSession session = request.getSession();
		String userID; 
		int User_ID = 0;
		userID = (String)request.getParameter("userID");
		if (userID==null){
			User_ID = (Integer)session.getAttribute("currentUserID");
		}else{
			User_ID = Integer.parseInt(userID);	
		}
		
	    User userProfile = UserDB.getUserProfile(User_ID);

	    session.setAttribute("userProfile", userProfile);
	    return "/WEB-INF/views/user_profile.jsp";
	 }
}
