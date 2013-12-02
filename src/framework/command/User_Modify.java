package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import framework.controller.Command;
import beans.User;
import beans.Credentials;
import data.UserDB;
import data.CredentialsDB;

public class User_Modify implements Command {
	/***************************************************************************************
	 * Class...................................................................User_Modify *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class pulls in the userID of the user selected to be modified and then         *
	 * retrieves a Bean of the user to populate on the user modify form.                   *
	 * 																					   *
	 * perform - required method from Command interface                                    *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String user_ID = (String)request.getParameter("userID");
		int userID = Integer.parseInt(user_ID);
		Credentials creds = new Credentials();
		creds = CredentialsDB.getCedentialByUser_ID(userID);
		User user = new User();
		user = UserDB.getUserByUserID(userID);
		
		user.setCreds_Email(creds.getEmail());
		user.setRole(creds.getRole());
		user.setValid(creds.getValid());
		
		session.setAttribute("user", user);
		return "/WEB-INF/views/user_modify.jsp";
	}
}
