package framework.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import framework.command.*;

/*******************************
 *  Author: JLH
 *******************************/
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<RequestEntry> requests;
	
	@Override
	public void init(){
		requests = new ArrayList<RequestEntry>();
		//--------------Authentication----------------------
		requests.add(new RequestEntry("/Home","Forward",new Home(),"public", ""));
		requests.add(new RequestEntry("/Login","Forward",new Login(),"public", ""));
		requests.add(new RequestEntry("/Logout","Redirect",new Logout(),"public", ""));
		requests.add(new RequestEntry("/Authenticate","Redirect",new Authenticate(),"public", ""));
		requests.add(new RequestEntry("/SendValidation","Redirect",new SendValidation(),"public", ""));
		//--------------Reset Password----------------------
		requests.add(new RequestEntry("/Forgot_Cred","Forward",new Forgot_Cred(),"public", ""));
		requests.add(new RequestEntry("/SendForgotCred","Redirect",new SendForgotCred(),"public", ""));
		requests.add(new RequestEntry("/Reset_Cred","Forward",new Reset_Cred(),"public", ""));
		requests.add(new RequestEntry("/Reset_PostCred","Redirect",new Reset_PostCred(),"public", ""));
		//--------------Register User-------------------------
		requests.add(new RequestEntry("/Credential_Add","Forward",new Credential_Add(),"public", ""));
		requests.add(new RequestEntry("/Cred_PostAdd","Redirect",new Cred_PostAdd(),"public", ""));
		//--------------User-------------------------
		requests.add (new RequestEntry("/User_Modify","Forward",new User_Modify(),"public", ""));
		requests.add (new RequestEntry("/User_PostModify","Redirect",new User_PostModify(),"protected", "user,moderator,admin"));
		requests.add (new RequestEntry("/UserProfile_PostModify","Redirect",new UserProfile_PostModify(),"protected", "user,moderator,admin"));
		requests.add (new RequestEntry("/UserProfile","Forward",new UserProfile(),"public", ""));
		requests.add (new RequestEntry("/UpdateUserProfile","Forward",new UpdateUserProfile(),"protected", "user,moderator,admin"));
		//--------------Projects---------------------
		requests.add(new RequestEntry("/Explore", "Forward", new Explore(), "public", ""));
		requests.add(new RequestEntry("/Create","Forward",new Create(),"protected", "user,admin,moderator")); //"user,admin"
		requests.add(new RequestEntry("/Create_Post", "Redirect", new Create_Post(), "protected", "user,admin,moderator"));
		requests.add(new RequestEntry("/ViewProject", "Forward", new ViewProject(), "public", ""));
		//--------------Admin/Moderator Modify User---------------------
		requests.add(new RequestEntry("/ViewUsers","Forward",new ViewUsers(),"protected", "admin,moderator"));
		requests.add(new RequestEntry("/User_Delete","Redirect",new User_Delete(),"protected", "admin"));
		//--------------Submissions---------------------
		requests.add(new RequestEntry("/ViewSubmissions", "Forward", new ViewSubmissions(), "public", ""));
		requests.add(new RequestEntry("/Submission_Add" , "Forward", new Submission_Add (), "public", ""));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		String nextView="";
		String responseType="";
		String templatePage="/WEB-INF/template/layout.jsp";
		HttpSession session = request.getSession();
		//*** (1)  Get the URL from the Request  ****
		String urlMapping = request.getServletPath();
		//*** (2) Lookup the URL in the Table  ****
		RequestEntry entry = getRequestEntry(urlMapping);
		session.setAttribute("urlMapping",urlMapping);
		//****(3) Security Check ****
		
		boolean accessGranted = accessCheck(request,entry);
		if(accessGranted){
			//*** (4) Call the Perform method for the object connected to the URL ****
			if(entry!=null){
				nextView = entry.getCommand().perform(request, response);
				responseType = entry.getResponseType();
			}			
		}else{
			//*** (4) If Access Not Granted Redirect to Login Page ****
			nextView="Home";
			responseType="Redirect";
		}
		
		//*** (5) Forward or Redirect to the Next View  ****
		if(responseType.equals("Forward")){
			request.setAttribute("nextView", nextView);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(templatePage);
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect(nextView);
		}
	}
	
	private RequestEntry getRequestEntry(String urlMapping){
		int index;
		RequestEntry found=null;
		
		for(index=0;index<requests.size();index++)
		{
			RequestEntry entry = requests.get(index);
			
			if(urlMapping.equals(entry.getUrlMapping()))
			{
				found = entry;
				break;
			}
		}
		
		return found; 
	}
	
	private boolean accessCheck(HttpServletRequest request,RequestEntry entry){
		
		boolean accessGranted=false;
		HttpSession session = request.getSession();
		
		if(entry.getSecurity().equals("public")){
			accessGranted=true;
		}else{
			//*** Protected Page with Role****
			String currentUsername = (String) session.getAttribute("currentUsername");
			String role = (String)session.getAttribute("currentRole");
			if(currentUsername!=null&&entry.getRole().contains(role)){
				accessGranted=true;
			}else{
				accessGranted=false;
			}
		}
		return accessGranted;
	}

}
