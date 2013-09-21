package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/***************************************************************************************
 * Class...................................................................ModUserView *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class provides next view destination for the controller -- HR Landing Page	   *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/
public class ModUserView  implements Command {
	
	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		return "/WEB-INF/views/mod_user_landing.jsp";
	}

}
