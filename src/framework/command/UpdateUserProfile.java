package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

public class UpdateUserProfile implements Command {
	/***************************************************************************************
	 * Class.............................................................UpdateUserProfile *
	 * Author..........................................................................JLH *
	 * ----------------------------------------------------------------------------------- *
	 * This class provides directs the nextview to the update_user_profile form.           *                             *
	 *                                                                                     *
	 *    perform - required method from Command interface                                 *
	 *                                                                                     *
	 ***************************************************************************************/
	@Override
	 public String perform(HttpServletRequest request,HttpServletResponse response) {
		
	    return "/WEB-INF/views/update_user_profile.jsp";
	 }
}
