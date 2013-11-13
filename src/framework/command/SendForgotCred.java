package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import framework.controller.Command;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import data.CredentialsDB;
/***********************************************************************
 * Class............................................SendForgotCred     *
 * Author...................................JLH(found code online)     *
 *---------------------------------------------------------------------*
 * This Class sends the specified email a link that enables them to    *
 * reset their password                                				   *
 * 																	   *
 ***********************************************************************/
public class SendForgotCred implements Command {

	HttpSession httpsession;
	String email;
	String returnClass;
	
	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {

		getParameters(request);
		returnClass = sendLink();
		return returnClass;
	}

	private void getParameters(HttpServletRequest request) {
		/***********************************************************************
		 * Method............................................getParameters     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method pulls in parameters using the HttpServletRequest from   *
		 * the forgot password fields on the forgot_cred.jsp					     		   *
		 * 																	   *
		 * Return Value 													   *
		 * (void)                                                              *
		 ***********************************************************************/
		httpsession = request.getSession();
		email = (String) request.getParameter("email");
	}
	
	private String sendLink() {
		/***********************************************************************
		 * Method.................................................sendLink     *
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
		String status = "";

		if (email != null) {
			int checkEmail = CredentialsDB.checkCred(email);

			int userID = 0;
			if (checkEmail == 0) {
				status = "This Email Address Has Not Been Registered!";
				httpsession.setAttribute("status", status);
				httpsession.setAttribute("email", email);
				return "Forgot_Cred";
			} else {
				userID = CredentialsDB.getUserIDByEmail(email);
			}
			//This generates a random key. It then adds the key to the database and emails a copy of the key
			//to the user. It makes it so that only someone with access to the user's email address can 
			//reset the password.
			String key = UUID.randomUUID().toString();
			int results = CredentialsDB.setKey(userID, key);
			if (results > 0) {

				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(
										"capstonefall2013@gmail.com", "Security_Pr0bs12!@");
							}
						});

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("capstonefall2013@gmail.com"));
					message.setRecipients(Message.RecipientType.TO, 
							
							InternetAddress.parse(email));
					message.setSubject("Testing Subject");
					message.setText("Dear "	+ email	+ ","
							+ "\n\n Please disregard if you did not request a password change! \n"
							+ "\nIf you did request a password change, go to the link below.\n\n"
							+ "http://localhost:8080/StageCraftMVCFramework/Reset_Cred?resetpass="
							+ userID + "&key=" + key);

					Transport.send(message);

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			}
		} else {
			status = "You Didn't Enter A Valid Email Address.";
			httpsession.setAttribute("status", status);
			return "Forgot_Cred";
		}
		return "Login";
	}
}