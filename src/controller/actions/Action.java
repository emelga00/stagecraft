package controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

public abstract class Action {
	abstract public View doGet(HttpServletRequest request, HttpServletResponse response);
	abstract public View doPost(HttpServletRequest request, HttpServletResponse response);
	
	public boolean isAuthorized(HttpServletRequest request){
		boolean status=false;
		
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser!=null) status=true;
		return status;
	}
}
