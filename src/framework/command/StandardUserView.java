package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

public class StandardUserView implements Command{
	/***************************************************************************************
	 * Class..............................................................StandardUserView *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class provides next view destination for the controller -- List Clients page   *
	 *   and generates arraylist of client beans for display                               *
	 *                                                                                     *
	 *    perform - required method from Command interface                                 *
	 *                                                                                     *
	 ***************************************************************************************/
	 @Override
	 public String perform(HttpServletRequest request,HttpServletResponse response) {

	    return "/WEB-INF/views/standard_user_landing.jsp";
	 }
	  
}
