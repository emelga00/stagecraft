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
 * This Class validates using credentials entered into the Login Form  *
 * on view 'login.jsp'                                                 *
 * 																	   *
 ***********************************************************************/
public class Authenticate implements Command {

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String button = request.getParameter("submit");
		if (button.equals("Forgot Password?")) {
			return "Forgot_Cred";
		}
		// ** 1. Grabs Username and Password from the login.jsp
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextView = "Login";
		// ** 2. Authenticates the user, determines whether the username
		// and password are valid
		Credentials currentUser = CredentialsDB.authenticate(username, password);
		// ** 3. Determines whether the user's email has been verified and that
		// they are not blocked for spam. Then it pulls role, email, and
		// user ID data from Credential Bean
		if (currentUser != null) {
			int currentValid = (int) currentUser.getValid();
			if (currentValid == 0) {
				String currentRole = (String) currentUser.getRole();
				String currentUsername = (String) currentUser.getEmail();
				int currentUserID = (int) currentUser.getUserID();
				// ** 4. Sets Username,UserID, and Role of user that logged in
				// into session scope
				session.setAttribute("currentUsername", currentUsername);
				session.setAttribute("currentRole", currentRole);
				session.setAttribute("currentUserID", currentUserID);
				// ** 5. Sets homepage relative to the user's role
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
				// ** 6. If not validated, it determines, based on the presence
				// of a key, if the user has been blocked due to spam. In either
				// case, a status message is set to notify the user as to why
				// they cannot login
			} else if (currentValid == 1) {
				if (currentUser.getRegKey().equals("")) {
					session.setAttribute("status", "You Have Been Blocked Due To Spam");
				} else {
					session.setAttribute("status", "You Must First Validate Your Email");
				}
			}
			// ** 7. Status set if user's username and password are not correct
		} else {
			session.setAttribute("status", "Invalid Username/Password Combination");
		}

		return nextView;

	}

}
