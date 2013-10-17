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

public class Cred_PostAdd implements Command {

	String submit;
	String email;
	String pass1;
	String pass2;
	String fName;
	String lName;
	String[] terms;
	int checkEmail;
	HttpSession session;
	String returnClass;
	String status;

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {

		session = request.getSession();
		status = "User Not Added, Try Again";
		// ** 1. Retrieve the Form Input 
		submit = request.getParameter("submit");
		email = request.getParameter("email");
		pass1 = request.getParameter("pass1");
		pass2 = request.getParameter("pass2");
		fName = request.getParameter("fName");
		lName = request.getParameter("lName");
		terms = request.getParameterValues("terms");
		// ** 2. Check if parameter is null, if not, puts parameter 
		//in session scope incase of page redirect
		if (fName != null) {
			session.setAttribute("fName", fName);
		}
		if (lName != null) {
			session.setAttribute("lName", lName);
		}
		if (email != null) {
			session.setAttribute("email", email);
		}
		// ** 3. Checks which button on registration form was pressed
		String button = request.getParameter("submit");
		if (button.equals("Resend Verification")) {
			returnClass = sendVerify();
			return returnClass;
		}

		returnClass = checkFields();
		return returnClass;

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
		// ** 4. Verifies that these fields are populated
		if (fName == "" || lName == "" || email == "") {

			status = "You Must Fill Out First Name, Last Name And Email";
			session.setAttribute("status", status);
		} else {
			// ** 5. Checks if the email is indeed in the database
			checkEmail = CredentialsDB.checkCred(email);

			if (checkEmail == 0) {
				status = "This Email Address Not Yet Been Registered";
				session.setAttribute("status", status);

			} else {
				// ** 6. Checks for "blocked due to spam"(not validated & no key in database)
				int currentValid = (int) CredentialsDB.checkVerification(email);

				if (currentValid != 0) {

					int user_ID = (int) CredentialsDB.getUserIDByEmail(email);
					String key = (String) CredentialsDB.getKeyBYUserID(user_ID);
					if (key.equals("")) {
						session.setAttribute("status", "You Have Been Blocked Due To Spam");
						return "Login";
					} else {
						// ** 7. Builds User Bean to send to "Send Verification"
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
		// ** 8. Verifies that all the proper fields are populated and checked
		if (terms == null) {

			status = "You Must Agree To The Terms";
			session.setAttribute("status", status);

		} else {

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
				// ** 10. Verifies password and confirm password match
				if (!pass1.equals(pass2)) {
					session.setAttribute("email", email);
					status = "Passwords Did Not Match, Try Again";
					session.setAttribute("status", status);
					return "Login";
				}
				// ** 11. Checks if the email is already registered in the database
				checkEmail = CredentialsDB.checkCred(email);

				if (checkEmail != 0) {
					status = "This Email Address Is Already Registered";
					session.setAttribute("status", status);
					return "Login";
				}
				// ** 12. Builds a Credential Bean
				String uuid = UUID.randomUUID().toString();

				Credentials cred = new Credentials();
				cred.setEmail(email);
				cred.setPass(pass1);
				cred.setRole("user");
				cred.setValid(1);
				cred.setRegKey(uuid);
				// ** 13. Adds a new row to the credential table in the database
				int results = CredentialsDB.addCred(cred);
				int cred_ID = 0;
				User user = new User();
				int user_ID = 0;
				// ** 14. Adds a row to the user table if the addCred is successful
				if (results > 0) {

					cred_ID = CredentialsDB.getCred_IDByBean(cred);
					if (cred_ID != 0) {
						results = UserDB.addUserFandL(fName, lName, cred_ID);
						if (results > 0) {
							user_ID = UserDB.getUserID(fName, lName, cred_ID);
							CredentialsDB.addUserID(user_ID, cred_ID);
							status = "Account Successfully Added, Check Email For Validation Link";
							// ** 15. Builds a User Bean from all of the added rows and puts the 
							//Bean in session scope
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
							// ** 16. If the User row creation fails, row created in the credentials table
							//will be deleted based on the cred_ID
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

	
}
