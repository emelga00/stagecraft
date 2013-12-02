package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import framework.controller.Command;

/***************************************************************************************
 * Class........................................................................Logout *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class invalidates the user's session and then provides directs the nextview to *
 * the Login page.                                                                     *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/

public class Logout implements Command{
	
	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		session.invalidate();
	
		
		return "Login";
	}

}
