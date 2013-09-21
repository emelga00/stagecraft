package framework.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserDB;

import beans.User;

import framework.controller.Command;

public class User_PostAdd  implements Command{
	/***************************************************************************************
	 * Class................................................................Client_PostAdd *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class processes Client bean from Client add form.                              *
	 * 																					   *
	 * 		perform - required method from Command interface                               *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String status = "Client not added, did you fill all required field?...";
		
		//** 1. Retrieve the Form Input (Name-Value Pairs) from the HTTP Request ***
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address = (address1+ " "+address2);
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String cDate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
		//** 2. Instantiate a User Bean and Load the Info from the Form ******
		if(fName==null || lName==null || phone==null || address==null || city==null ||state==null ||zip==null){
			session.setAttribute("status", status);
			return "User_Add";
		}
		else{
		User client = new User();
		client.setFirst_Name(fName);
		client.setLast_Name(lName);
		client.setPhone(phone);
		client.setAddress(address.trim());
		client.setCity(city);
		client.setState(state);
		client.setZIP(zip);
		client.setDate(cDate);
		
		//** 3. Add the Bean to the Database ****
		int results = UserDB.addUser(client);
		if(results > 0) {
			status = "Successful Registration!";
			session.setAttribute("status", status);
			User clientbybean = UserDB.getUserByBean(client);
			session.setAttribute("client", clientbybean);
					return "Credential_Add";
		}}
		
		return "User_Add";
	}
}
