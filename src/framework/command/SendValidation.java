package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import framework.controller.Command;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import beans.User;
/***********************************************************************
 * Class............................................SendValidation     *
 * Author...................................JLH(found code online)     *
 *---------------------------------------------------------------------*
 * This Class sends the specified email a link that enables them to    *
 * validate their email address.                       				   *
 * 																	   *
 ***********************************************************************/
public class SendValidation implements Command {
	int userID;
	String key;
	String email;
	String userName;
	User user;
	Session session;
	String returnClass;
	HttpSession httpsession;

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
		 * the registration fields on the login.jsp					     		   *
		 * 																	   *
		 * Return Value 													   *
		 * (void)                                                              *
		 ***********************************************************************/
		httpsession = request.getSession();
		User user = (User)httpsession.getAttribute("user");
		httpsession.removeAttribute("user");
		
		userID = user.getUser_ID();
		key = user.getCreds_RegKey();
		email= user.getCreds_Email();
		userName= user.getFirst_Name()+" "+user.getLast_Name();
	}
	
	private String sendLink() {
		/***********************************************************************
		 * Method.................................................sendLink     *
		 * Author......................................................JLH     *
		 *---------------------------------------------------------------------*
		 * This method verifies that all of the necessary fields were properly *
		 * filled out. If they all filled out properly, it sends a link to the *
		 * entered email address so they can validate their email address	   *
		 * 																	   *
		 * Return Value 													   *
		 * (String) returnClass:  Returns a String value that indicates the    *
		 * correct page for redirection										   *
		 ***********************************************************************/
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("capstonefall2013@gmail.com","Security_Pr0bs12!@");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("capstonefall2013@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject("Global Performance Space (Do Not Reply)");
			message.setText("Dear "+userName+"," +
					"\n\n Please click on the link below to validate your e-mail address!\n" +
					"http://localhost:8080/StageCraftMVCFramework/Login?user="+userID+"&key="+key);
 
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
return "Login";
	}
}
