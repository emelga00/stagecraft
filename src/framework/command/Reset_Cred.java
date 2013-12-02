package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/***************************************************************************************
 * Class.....................................................................ResetCred *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class provides directs the nextview to the reset_creds form.                   *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/
public class Reset_Cred implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
	    return "/WEB-INF/views/reset_creds.jsp";
	}
}
