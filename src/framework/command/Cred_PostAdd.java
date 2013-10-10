/**************************
 *  Author: JLH
 **************************/

package framework.command;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Credentials;
import data.UserDB;
import data.CredentialsDB;
import beans.User;

import framework.controller.Command;

public class Cred_PostAdd  implements Command{

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String status = "Credentials not added...";
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
				
				String email = request.getParameter("email");
				String pass1 = request.getParameter("pass1");
				String pass2 = request.getParameter("pass2");
				String fName = request.getParameter("fName");
				String lName = request.getParameter("lName");
				String[] terms = request.getParameterValues("terms");
				
				if (terms == null){
					
					session.setAttribute("email",email);
					session.setAttribute("fName",fName);
					session.setAttribute("lName",lName);
					status = "You Must Agree To The Terms";
					session.setAttribute("status", status);
				
					return "Login";	
				}
			
				if (fName==""||lName==""||email==""||pass1==""||pass2==""){
					if(fName!=null){
						session.setAttribute("fName",fName);
					}
					if(lName!=null){
						session.setAttribute("lName",lName);
					}
					if(email!=null){
						session.setAttribute("email",email);
					}
					
					status = "You Must Fill Out All Fields";
					session.setAttribute("status", status);
					
				return "Login";	
				}
				
				String var1="@";
				String var2=".";
				if(email.indexOf(var1) == -1 ||email.indexOf(var2) == -1){
					status = "Invalid E-Mail. Try Again!";
					session.setAttribute("status", status);
					return "Credential_Add";
				}
				if (!pass1.equals(pass2)){
					session.setAttribute("email", email);
					status = "Passwords did not match. Try Again!";
					session.setAttribute("status", status);
					return "Credential_Add";
				}
				
				int checkEmail = CredentialsDB.checkCred(email);
				
				if (checkEmail!=0){
					status = "This Email Address Is Already Registered!";
					session.setAttribute("status", status);
					return "Credential_Add";
				}
				String uuid = UUID.randomUUID().toString();

				//** 2. Instantiate a Credentials Bean and Load the Info from the Form ******
				Credentials cred = new Credentials();
				cred.setEmail(email);
				cred.setPass(pass1);
				cred.setRole("user");
				cred.setValid(1);
				cred.setRegKey(uuid);
				//** 3. Add the Bean to the Database ****
				int results = CredentialsDB.addCred(cred);
				int cred_ID = 0;
				User user = new User();
				int user_ID = 0;
				if(results > 0) {
					
					
					
					cred_ID = CredentialsDB.getCred_IDByBean(cred);
					if(cred_ID!=0){
						results = UserDB.addUserFandL(fName, lName, cred_ID);
						if(results > 0){
							user_ID = UserDB.getUserID(fName,lName,cred_ID);
							CredentialsDB.addUserID(user_ID);
							status = "Account Successfully Added, Check Email For Validation Link";
							
							user.setFirst_Name(fName);
							user.setLast_Name(lName);
							user.setCreds_Credential_ID(cred_ID);
							user.setCreds_RegKey(uuid);
							user.setCreds_Email(email);
							user.setUser_ID(user_ID);
							session.setAttribute("user", user);
						}else{
							CredentialsDB.deleteCred(cred_ID);
						}
					}

				}
				else{
					
					status = "Account Not Added, Try Again";
					
				}
				session.setAttribute("status", status);
		
		
		return "SendValidation";
	}
}
