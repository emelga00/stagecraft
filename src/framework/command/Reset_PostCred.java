/**************************
 *  Author: JLH
 **************************/

package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CredentialsDB;

import framework.controller.Command;

public class Reset_PostCred  implements Command{

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
				String email = request.getParameter("email");
				String pass1 = request.getParameter("pass1");
				String pass2 = request.getParameter("pass2");
				String status = "";
				
				if (!pass1.equals(pass2)){
					session.setAttribute("email", email);
					status = "Passwords did not match. Try Again!";
					session.setAttribute("status", status);
					return "Forgot_Cred";
				}
				int userID = Integer.parseInt((String)request.getParameter("userID"));
								
				int results = CredentialsDB.resetCred(userID, pass1);
				if(results > 0) {
					
					status = "Password Successfully Changed!";
					session.setAttribute("status", status);
					
				}
				else{
					status = "Password Not Successfully Changed!";
					session.setAttribute("status", status);
				}
		
		return "Login";
	}
}
