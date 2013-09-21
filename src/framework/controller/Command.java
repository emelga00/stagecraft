package framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public String perform(HttpServletRequest request, HttpServletResponse response);
}
