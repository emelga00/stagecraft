<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("user");
	String currentRole = (String) session.getAttribute("currentRole");
%>
<div id="register">

	<form name="registration" class="register-form"
		action="User_PostModify" method="post">
		<div class="register-header">
			<h1>Register</h1>
		</div>
		<div class="register-content">
			First Name 
			<input type="text" name="fName" class="modify-fName" value="<%=user.getFirst_Name()%>" size="30" /><br> 
			<br>
			Last Name 
			<input type="text" name="lName" class="modify-lName" value="<%=user.getLast_Name()%>" size="30" /><br> 
			<br>
			Phone 
			<input type="text" name="phone" class="modify-phone" value="<%=user.getPhone()%>" size="30" /> <br> 
			<br>
			Address 
			<input type="text" name="address" class="modify-address" value="<%=user.getAddress()%>" size="30" /><br> 
			<br> 
			City
			<input type="text" name="city" class="modify-city" value="<%=user.getCity()%>" size="30" /><br> 
			<br> 
			State 
			<input type="text" name="state" class="modify-state" value="<%=user.getState()%>" size="30" /><br> 
			<br> 
			Zip 
			<input type="text" name="zip" class="modify-zip" value="<%=user.getZIP()%>" size="30" /> <br> 
			<br>
			Role
			<%
				if (currentRole.equals("admin")) {
					if (user.getRole().equals("admin")) {
						out.println("<select name='role'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " <option value='admin' selected>admin</option>"
								+ " </select> ");
					} else if (user.getRole().equals("moderator")) {
						out.println("<select name='role'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator' selected>moderator</option>"
								+ " <option value='admin'>admin</option>"
								+ " </select> ");
					} else if (user.getRole().equals("user")) {
						out.println("<select name='role'>"
								+ " <option value='user' selected>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " <option value='admin'>admin</option>"
								+ " </select> ");
					}
				} else if (currentRole.equals("moderator")) {
					if (user.getRole().equals("moderator")) {
						out.println("<select name='role'>"
								+ " <option value='user'>user</option>"
								+ " <option value='moderator' selected>moderator</option>"
								+ " </select> ");
					}else if (user.getRole().equals("user")) {
						out.println("<select name='role'>"
								+ " <option value='user' selected>user</option>"
								+ " <option value='moderator'>moderator</option>"
								+ " </select> ");
					}
				}
			%>
			Enabled
			<%
				if (user.getValid()==0) {
					out.println("<select name='enabled'>"
							+ " <option value='yes' selected>enabled</option>"
							+ " <option value='no'>disabled</option>" + 
							" </select> ");
				}else{
					out.println("<select name='enabled'>"
							+ " <option value='yes'>enabled</option>"
							+ " <option value='no' selected>disabled</option>" + 
							" </select> ");
				}
			%>
			<br> <input type="hidden" name="userID" value="<%=user.getUser_ID()%>" /><br />

		</div>
		<div class="register-footer">
			<input type="submit" name="submit" value="Cancel" class="submit" />
			<input type="reset" name="reset" value="Reset" class="reset" /> <input
				type="submit" name="submit" value="Sign Up" class="submit" />

		</div>
	</form>
</div>