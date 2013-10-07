package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import framework.controller.Command;

public class Home implements Command{

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		
		return "/WEB-INF/views/home.jsp";
	}

}
