package controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import data.UserDAO;

public class Login extends Action {

	@Override
	public View doGet(HttpServletRequest request, HttpServletResponse response) {
		View view = new View();
		view.setResponseType("FORWARD");
		view.setPage("/WEB-INF/views/login.jsp");
		return view;
	}

	@Override
	public View doPost(HttpServletRequest request, HttpServletResponse response) {
		View view = new View();
		HttpSession session = request.getSession();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		User currentUser = userDAO.authenticate(userName, password);
		session.setAttribute("currentUser", currentUser);
		
		view.setResponseType("REDIRECT");
		view.setPage("admin");
		return view;
	}
	
	@Override
	public boolean isAuthorized(HttpServletRequest request){
		return true;
	}

}
