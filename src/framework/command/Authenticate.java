package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Credentials;
import data.CredentialsDB;
import framework.controller.Command;
/***********************************************************************
 * Class..............................................Authenticate     *
 * Author......................................................JLH     *
 *---------------------------------------------------------------------*
 * This Class first implements a method from the Command Interface and *
 * then validates using credentials entered into the Login Form        *
 * on view 'login.jsp'                                                 *
 * 																	   *
 ***********************************************************************/
public class Authenticate implements Command {
	String username;
	String password;
	String nextView;
	HttpSession session;
	String button;
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
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
		nextView = authenticateUser(request);
		return nextView;
	}
	private void getParameters(HttpServletRequest request) {
		/***********************************************************************
		 * Method............................................getParameters     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method pulls in parameters using the HttpServletRequest from   *
		 * the login form in login.jsp		         			     		   *
		 * 																	   *
		 * Return Value 													   *
		 * (void)                                                              *
		 ***********************************************************************/
		session = request.getSession();
		username = request.getParameter("username");
		password = request.getParameter("password");
		button = request.getParameter("submit");
		
	}
	private String authenticateUser(HttpServletRequest request) {
		/***********************************************************************
		 * Method.........................................authenticateUser     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method pulls in the email and password parameters using the    *
		 * HttpServlerRequest from the login form on login.jsp. It then        *
		 * determines whether the email and password are valid and have the    *
		 * rights to login							                           *
		 * 																	   *
		 * Return Value 													   *
		 * (String) nextView:  Returns a String value that indicates the       *
		 * correct page for redirection										   *
		 ***********************************************************************/
		// ** Checks to see if the "Forgot Password" button was pressed on the
		// login form on login.jsp
		if (button.equals("Forgot Password?")) {
			return "Forgot_Cred";
		}
		nextView = "Login";
		// ** Authenticates the user, determines whether the username
		// and password are valid
		Credentials currentUser = CredentialsDB.authenticate(username, password);
		// ** Determines whether the user's email has been verified.
		// Then it pulls role, email, and user ID data from Credential Bean
		if (currentUser != null) {
			int currentValid = (int) currentUser.getValid();
			if (currentValid == 0) {
				String currentRole = (String) currentUser.getRole();
				String currentUsername = (String) currentUser.getEmail();
				int currentUserID = (int) currentUser.getUserID();
				// ** Sets Username,UserID, and Role of user that logged in
				// into session scope
				session.setAttribute("currentUsername", currentUsername);
				session.setAttribute("currentRole", currentRole);
				session.setAttribute("currentUserID", currentUserID);
				// ** Sets homepage relative to the user's role
				if (currentRole.equals("user")) {
					nextView = "StandardUserView";
					session.setAttribute("homepage", "StandardUserView");
				} else if (currentRole.equals("admin")) {
					nextView = "AdminView";
					session.setAttribute("homepage", "AdminView");
				} else if (currentRole.equals("moderator")) {
					nextView = "ModUserView";
					session.setAttribute("homepage", "ModUserView");
				}
				// ** If not validated, it determines, based on the presence
				// of a key, if the user has not yet validated their email or
				// has been blocked due to spam. In either case, a status 
				// message is set to notify the user as to why they cannot login
			} else if (currentValid == 1) {
				if (currentUser.getRegKey().equals("")) {
					session.setAttribute("status", "You Have Been Blocked Due To Spam");
				} else {
					session.setAttribute("status", "You Must First Validate Your Email");
				}
			}
			// ** Status set if user's username and password are not correct
		} else {
			session.setAttribute("status", "Invalid Username/Password Combination");
		}
		return nextView;
	}
}
