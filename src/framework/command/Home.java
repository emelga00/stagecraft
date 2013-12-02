package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

/***************************************************************************************
 * Class..........................................................................Home *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class provides directs the nextview to the home.jsp                            *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/
public class Home implements Command{

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		
		return "/WEB-INF/views/home.jsp";
	}

}
