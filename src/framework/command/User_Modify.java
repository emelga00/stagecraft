package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.controller.Command;
import beans.User;
import beans.Credentials;
import data.UserDB;
import data.CredentialsDB;
/******************
 * Author JLH
 ******************/


public class User_Modify implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String user_ID = (String)request.getParameter("userID");
		int userID = Integer.parseInt(user_ID);
		Credentials creds = new Credentials();
		creds = CredentialsDB.getCredentialByUserID(userID);
		User user = new User();
		user = UserDB.getUserByUserID(userID);
		System.out.println(creds.getEmail());
		user.setCreds_Email(creds.getEmail());
		user.setRole(creds.getRole());
		user.setValid(creds.getValid());
		
		session.setAttribute("user", user);
		return "/WEB-INF/views/user_modify.jsp";
	}
}
