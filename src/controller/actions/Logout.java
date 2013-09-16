package controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends Action {

	@Override
	public View doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		View view = new View();
		view.setResponseType("REDIRECT");
		view.setPage("index");
		return view;
	}

	@Override
	public View doPost(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	@Override
	public boolean isAuthorized(HttpServletRequest request){
		return true;
	}

}
