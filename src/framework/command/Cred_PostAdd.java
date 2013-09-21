/**************************
 *  Author: JLH
 **************************/

package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.Credentials;
import data.UserDB;
import data.CredentialsDB;

import framework.controller.Command;

public class Cred_PostAdd  implements Command{

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String status = "Credentials not added...";
		User client;
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
				
				String email = request.getParameter("email");
				String pass1 = request.getParameter("pass1");
				String pass2 = request.getParameter("pass2");
				
				String var1="@";
				String var2=".";
				if(email.indexOf(var1) == -1 ||email.indexOf(var2) == -1){
					status = "Invalid E-Mail. Try Again!";
					session.setAttribute("status", status);
					return "CredentialAdd";
				}
				if (!pass1.equals(pass2)){
					session.setAttribute("email", email);
					status = "Passwords did not match. Try Again!";
					session.setAttribute("status", status);
					return "CredentialAdd";
				}
				int clientID = Integer.parseInt((String)request.getParameter("clientID"));
				//** 2. Instantiate a Credentials Bean and Load the Info from the Form ******
				Credentials cred = new Credentials();
				cred.setEmail(email);
				cred.setPass(pass1);
				cred.setUserID(clientID);
				cred.setRole("user");
				
				//** 3. Add the Bean to the Database ****
				int results = CredentialsDB.addCred(cred);
				if(results > 0) {
					status = "Credentials added......";
					
					
			

				}
				else{
					client = new User();
					client = (User)session.getAttribute("client");
					int client_ID = client.getUser_ID();
					UserDB.delUser(client_ID);
					
				}
				session.setAttribute("status", status);
		
		
		return "Login";
	}
}
