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
/***********************************************************************
 * Class..............................................Cred_PostAdd     *
 * Author......................................................JLH     *
 *---------------------------------------------------------------------*
 * This Class processes the information on the registration form. It   *
 * either resends a verification or creates a new user				   *
 * 																	   *
 ***********************************************************************/
public class Cred_PostAdd implements Command {
	String submit;
	String email;
	String pass1;
	String pass2;
	String fName;
	String lName;
	String returnClass;
	String status;
	String button;
	String[] terms;
	int checkEmail;
	HttpSession session;
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
		returnClass = checkFields();
		return returnClass;
	}
	private void getParameters(HttpServletRequest request) {
		/***********************************************************************
		 * Method............................................getParameters     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method pulls in parameters using the HttpServletRequest from   *
		 * the registration form in login.jsp					     		   *
		 * 																	   *
		 * Return Value 													   *
		 * (void)                                                              *
		 ***********************************************************************/
		session = request.getSession();
		status = "User Not Added, Try Again";
		// ** Retrieve the Form Input 
		submit = request.getParameter("submit");
		email = request.getParameter("email");
		pass1 = request.getParameter("pass1");
		pass2 = request.getParameter("pass2");
		fName = request.getParameter("fName");
		lName = request.getParameter("lName");
		terms = request.getParameterValues("terms");
		button = request.getParameter("submit");
		// ** Check if parameter is null, if not, puts parameter 
		//    in session scope incase of page redirect
		if (fName != null) {
			session.setAttribute("fName", fName);
		}
		if (lName != null) {
			session.setAttribute("lName", lName);
		}
		if (email != null) {
			session.setAttribute("email", email);
		}
		
	}
	private String checkFields() {
		/***********************************************************************
		 * Method..............................................checkFields     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method verifies that all of the necessary fields were properly *
		 * filled out. This method is only used in new user creation														   *
		 * 																	   *
		 * Return Value 													   *
		 * (String) returnClass:  Returns a String value that indicates the    *
		 * correct page for redirection										   *
		 ***********************************************************************/
		// ** Checks which button on registration form was pressed
		if (button.equals("Resend Verification")) {
			returnClass = sendVerify();
			return returnClass;
		} 
		// ** Verifies that all the proper fields are populated and checked
		if (terms == null) {
			status = "You Must Agree To The Terms";
			session.setAttribute("status", status);
		}
		else {
			if (fName == "" || lName == "" || email == "" || pass1 == ""
					|| pass2 == "") {
				status = "You Must Fill Out All Fields";
				session.setAttribute("status", status);
			} else {
				// ** 9. Verifies that the email is a proper email address
				String var1 = "@";
				String var2 = ".";
				if (email.indexOf(var1) == -1 || email.indexOf(var2) == -1) {
					status = "Invalid E-Mail. Try Again!";
					session.setAttribute("status", status);
					return "Login";
				}
				// ** Verifies password and confirm password match
				if (!pass1.equals(pass2)) {
					session.setAttribute("email", email);
					status = "Passwords Did Not Match, Try Again";
					session.setAttribute("status", status);
					return "Login";
				}
				// ** Checks if the email is already registered in the database
				checkEmail = CredentialsDB.checkCred(email);
				if (checkEmail != 0) {
					status = "This Email Address Is Already Registered";
					session.setAttribute("status", status);
					return "Login";
				}
				// ** Builds a Credential Bean
				String uuid = UUID.randomUUID().toString();
				Credentials cred = new Credentials();
				cred.setEmail(email);
				cred.setPass(pass1);
				cred.setRole("user");
				cred.setValid(1);
				cred.setRegKey(uuid);
				// ** Adds a new row to the credential table in the database
				int results = CredentialsDB.addCred(cred);
				int cred_ID = 0;
				User user = new User();
				int user_ID = 0;
				// ** Adds a row to the user table if the addCred is successful
				if (results > 0) {
					cred_ID = CredentialsDB.getCred_IDByBean(cred);
					if (cred_ID != 0) {
						results = UserDB.addUserFandL(fName, lName, cred_ID);
						if (results > 0) {
							user_ID = UserDB.getUserID(fName, lName, cred_ID);
							CredentialsDB.addUserID(user_ID, cred_ID);
							status = "Account Successfully Added, Check Email For Validation Link";
							// ** Builds a User Bean from all of the added rows and puts the 
							//    Bean in session scope
							user.setFirst_Name(fName);
							user.setLast_Name(lName);
							user.setCreds_Credential_ID(cred_ID);
							user.setCreds_RegKey(uuid);
							user.setCreds_Email(email);
							user.setUser_ID(user_ID);
							session.setAttribute("user", user);
							fName = "";
							session.setAttribute("fName", fName);
							lName = "";
							session.setAttribute("lName", lName);
							email = "";
							session.setAttribute("email", email);
						} else {
							// ** If the User row creation fails, row created in the credentials table
							//    will be deleted based on the cred_ID
							CredentialsDB.deleteCred(cred_ID);
							status = "Account Not Added, Try Again";
							return "Login";
						}
					}
				}
				session.setAttribute("status", status);
				return "SendValidation";
			}
		}
		return "Login";
	}
	private String sendVerify() {
		/***********************************************************************
		 * Method...............................................sendVerify     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method allows the user to resend their email validation. This  *
		 * is helpful to have in place incase the user deletes the email or    *
		 * if a spam filter happen to catch it.								   *
		 * 																	   *
		 * Return Value 													   *
		 * (String) returnClass:  Returns a String value that indicates the    *
		 * correct page for redirection										   *
		 ***********************************************************************/
		// ** Verifies that these fields are populated
		if (fName == "" || lName == "" || email == "") {
			status = "You Must Fill Out First Name, Last Name And Email";
			session.setAttribute("status", status);
		} else {
			// ** Checks if the email is indeed in the database
			checkEmail = CredentialsDB.checkCred(email);
			if (checkEmail == 0) {
				status = "This Email Address Not Yet Been Registered";
				session.setAttribute("status", status);
			} else {
				// ** Checks for "blocked due to spam"(not validated & no key in database)
				int currentValid = (int) CredentialsDB.checkVerification(email);
				if (currentValid != 0) {
					int user_ID = (int) CredentialsDB.getUserIDByEmail(email);
					String key = (String) CredentialsDB.getKeyBYUserID(user_ID);
					if (key.equals("")) {
						session.setAttribute("status", "You Have Been Blocked Due To Spam");
						return "Login";
					} else {
						// ** Builds User Bean to send to "Send Verification"
						User nonValidUser = new User();
						nonValidUser.setUser_ID(user_ID);
						nonValidUser.setCreds_RegKey(key);
						nonValidUser.setCreds_Email(email);
						nonValidUser.setFirst_Name(fName);
						nonValidUser.setLast_Name(lName);
						session.setAttribute("user", nonValidUser);
						return "SendValidation";
					}
				} else {
					status = "This Email Address Has Already Been Validated";
					session.setAttribute("status", status);
				}
			}
		}
		return "Login";
	}
}
