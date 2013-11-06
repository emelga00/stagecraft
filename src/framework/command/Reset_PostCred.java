package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.CredentialsDB;
import framework.controller.Command;
/***********************************************************************
 * Class............................................Reset_PostCred     *
 * Author......................................................JLH     *
 *---------------------------------------------------------------------*
 * This Class processes the information on the change password form. If*
 * the password and confirm password fields match, it changes the      *
 * user's password                                   				   *
 * 																	   *
 ***********************************************************************/
public class Reset_PostCred  implements Command{
	
	HttpSession session;
	String email;
	String pass1;
	String pass2;
	String status;
	String returnClass;
	int userID;
	
	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		/***********************************************************************
		 * Method..................................................perform     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method overrides the method in the Command Interface giving the*
		 * function for HttpServlet requests and responses                     *
		 * 																	   *
		 * Return Value 													   *
		 * (String) nextView:  Returns a String value that indicates the       *
		 * correct page for redirection										   *
		 ***********************************************************************/
		getParameters(request);
		returnClass = checkFields();
		return returnClass;
	}
	private void getParameters(HttpServletRequest request) {
		/***********************************************************************
		 * Method............................................getParameters     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method pulls in parameters using the HttpServletRequest from   *
		 * the forgot password form in reset_creds.jsp			     		   *
		 * 																	   *
		 * Return Value 													   *
		 * (void)                                                              *
		 ***********************************************************************/
		session = request.getSession();
		email = request.getParameter("email");
		pass1 = request.getParameter("pass1");
		pass2 = request.getParameter("pass2");
		status = "";
		userID = Integer.parseInt((String)request.getParameter("userID"));
	}
	private String checkFields() {
		/***********************************************************************
		 * Method..............................................checkFields     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method verifies that all of the necessary fields were properly *
		 * filled out. This method checks to see if the new password created   *
		 * and the confirm password match. If they match, it changes the user's*
		 * password.														   *
		 * 																	   *
		 * Return Value 													   *
		 * (String) returnClass:  Returns a String value that indicates the    *
		 * correct page for redirection										   *
		 ***********************************************************************/
		if (!pass1.equals(pass2)){
			session.setAttribute("email", email);
			status = "Passwords did not match. Try Again!";
			session.setAttribute("status", status);
			return "Forgot_Cred";
		}
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