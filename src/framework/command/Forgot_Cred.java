/**************************
 *  Author: JLH
 **************************/

package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

public class Forgot_Cred implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
	    return "/WEB-INF/views/forgot_cred.jsp";
	}
}
