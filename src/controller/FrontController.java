package controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.actions.*;

@WebServlet({"/index","/admin","/addStudent","/login","/logout","/updateStudent"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Hashtable<String,Action> actions;


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		actions = new Hashtable<String,Action>();
		actions.put("/index", new Index());
		actions.put("/login", new Login());
		actions.put("/logout", new Logout());
		actions.put("/admin", new Admin());
		actions.put("/addStudent", new AddStudent());
		actions.put("/updateStudent", new UpdateStudent());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String defaultURL = "/index";
		View view;
		//****** Templating Step One ********
		//** Name and Location of the template
		String templatePage = "/WEB-INF/template/layout.jsp";  
		
		//*** Lookup the URL Mapping in the Hash Table
		Action action = actions.get(request.getServletPath());
		if(action==null) action=actions.get(defaultURL);  //** The default if a mapping isn't found
		
		if(action.isAuthorized(request)){
			//*** Execute the Controller Code for the Retrieved URL Mapping *****
			if(request.getMethod().equalsIgnoreCase("POST")){
				view=action.doPost(request, response);
			}else{
				view=action.doGet(request, response);
			}
		}else{
			view = new View();
			view.setResponseType("REDIRECT");
			view.setPage("login");
		}
		
		//*** Forward or Redirect to the Next View *******
		if(view.getResponseType().equalsIgnoreCase("REDIRECT")){
			response.sendRedirect(view.getPage());
		}else{
			//****** Templating Step Two *******
			//Add the Page Content from the Request to Request Scope
			request.setAttribute("content", view.getPage());  
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(templatePage);
			dispatcher.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//**** Process Post Request with doGet Method
		doGet(request,response);
	}

}
