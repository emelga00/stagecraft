<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
/*****************
 *  Author: JLH
 *****************/
	User user = (User) session.getAttribute("user");
	String currentRole = (String) session.getAttribute("currentRole");
%>
<div id="modify">

	<form name="registration" class="modify-form" action="User_PostModify"
		method="post">
		<div class="modify-header">
			<h1>User Info</h1>
		</div>
		<div class="modify-content">

			<label>First Name</label> <input type="text" name="fName"
				class="modify-field" value="<%=user.getFirst_Name()%>" size="30"
				disabled="disabled" /> <label>Last Name</label> <input type="text"
				name="lName" class="modify-field" value="<%=user.getLast_Name()%>"
				size="30" disabled="disabled" /> <label>Email</label> <input
				type="text" name="email" class="modify-field"
				value="<%=user.getCreds_Email()%>" size="30" disabled="disabled" />
			<label>Role</label>
			<%
				if (currentRole.equals("admin")) {
					if (user.getRole().equals("admin")) {
						out.println("<select name='role' style='width:10em'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " <option value='admin' selected>admin</option>"
								+ " </select> ");
					} else if (user.getRole().equals("moderator")) {
						out.println("<select name='role' style='width:10em'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator' selected>moderator</option>"
								+ " <option value='admin'>admin</option>"
								+ " </select> ");
					} else if (user.getRole().equals("user")) {
						out.println("<select name='role' style='width:10em'>"
								+ " <option value='user' selected>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " <option value='admin'>admin</option>"
								+ " </select> ");
					}
				} else if (currentRole.equals("moderator")) {
					if (user.getRole().equals("moderator")) {
						out.println("<select name='role' style='width:10em'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator' selected>moderator</option>"
								+ " </select> ");
					} else if (user.getRole().equals("user")) {
						out.println("<select name='role' style='width:10em'>"
								+ " <option value='user' selected>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " </select> ");
					}
				}
			%>
			<br /> <label>Enabled?</label>
			<%
				if (user.getValid() == 0) {
					out.println("<select name='enabled' style='width:10em'>"
							+ " <option value='yes' selected>enabled</option>"
							+ " <option value='no'>disabled</option>"
							+ " </select> ");
				} else {
					out.println("<select name='enabled' style='width:10em'>"
							+ " <option value='yes'>enabled</option>"
							+ " <option value='no' selected>disabled</option>"
							+ " </select> ");
				}
			%>
			<br>
			<input type="hidden" name="userID" value="<%=user.getUser_ID()%>" />
			<div class="clear"></div>
			<br>
			<br>
		</div>
		<div class="modify-footer">
			<input type="submit" name="submit" value="Cancel" class="submit" />
			<input type="reset" name="reset" value="Reset" class="reset" /> <input
				type="submit" name="submit" value="Submit" class="submit" />

		</div>
	</form>
</div>