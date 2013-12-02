/**************************
 *  Author: JLH
 **************************/

package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/***************************************************************************************
 * Class...................................................................Forgot_Cred *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class provides directs the nextview to the forgot_cred.jsp.                    *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/
public class Forgot_Cred implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
	    return "/WEB-INF/views/forgot_cred.jsp";
	}
}
