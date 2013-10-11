package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserDB;
import data.CredentialsDB;

import beans.User;
import beans.Credentials;

import framework.controller.Command;

public class User_Modify  implements Command{

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String status = "Client not updated...";
		
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
		int    clientID = Integer.parseInt(request.getParameter("userID"));
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String cDate = request.getParameter("date");
		
		//** 2. Instantiate a User Bean and Load the Info from the Form ******
		User client = new User();
		client.setUser_ID(clientID);
		client.setFirst_Name(fName);
		client.setLast_Name(lName);
		client.setPhone(phone);
		client.setAddress(address);
		client.setCity(city);
		client.setState(state);
		client.setZIP(zip);
		client.setDate(cDate);
		
		//** 3. Add the Bean to the Database ****
		int results = UserDB.updateUser(client);
		if(results > 0) {
			status = "Client updated......";
		}
		session.setAttribute("status", status);
		//check to see if updating credentials too
		if(request.getParameter("modCreds") != null)
			if(request.getParameter("modCreds").equals("yes") && results > 0) {
				//get cred info
				Credentials cred = CredentialsDB.getCredentialByUserID(Integer.toString(clientID));
				//load session scope
				session.setAttribute("cred", cred);
				return "CredentialUpdate";
			}
		if(request.getParameter("addCreds") != null)
			if(request.getParameter("addCreds").equals("yes") && results > 0) {
				//load session scope
				session.setAttribute("wizard", "true");
				session.setAttribute("clientID", Integer.toString(clientID));
				return "CredentialAdd";
			}
			
			
		return "CAClientListView";
	}
}
