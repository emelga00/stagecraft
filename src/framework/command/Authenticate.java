package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Credentials;
import data.CredentialsDB;


import framework.controller.Command;

/**************
 *  Author: JLH
 **************/
public class Authenticate implements Command {
	
	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
					
		HttpSession session = request.getSession();
		
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextView="Login";
			
		//** 2. Check For Valid UserName and Password Combination ******
		Credentials currentUser = CredentialsDB.authenticate(username, password);
				
		//** 3. Directs users to respective landing page ******
		if(currentUser!=null){
			String 	currentRole = (String) currentUser.getRole();
			String 	currentUsername = (String) currentUser.getEmail();
			int		currentUserID	= (int) currentUser.getUserID();
			
			session.setAttribute("currentUsername", currentUsername);
			session.setAttribute("currentRole", currentRole);
			session.setAttribute("currentUserID", currentUserID);
			
			if(currentRole.equals("user")){
				nextView="StandardUserView";
				session.setAttribute("homepage", "StandardUserView");
			}else if(currentRole.equals("admin")){
				nextView="AdminView";
				session.setAttribute("homepage", "AdminView");
			}else if(currentRole.equals("moderator")){
				nextView="ModUserView";
				session.setAttribute("homepage", "ModUserView");
			}
		}
		else{
			session.setAttribute("status","Invalid Username/Password Combination");
		}
		
		
		
		return nextView;
		
	}


}
