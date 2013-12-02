<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.ArrayList,beans.*,data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Your Password</title>
</head>

<%
	/*****************
	 *  Author: JLH
	 *****************/

	String userID = (String) request.getParameter("resetpass");
	String key = (String) request.getParameter("key");
	int user_ID = 0;
	String email = null;
	String status = (String) session.getAttribute("status");
	if (userID != null || key != null) {
		user_ID = Integer.parseInt(userID);
		Credentials resetCreds = CredentialsDB
				.getCredentialByUserIDKey(user_ID, key);
		if (resetCreds != null) {
			if (status == null) {
				status = "";
			}
			session.removeAttribute("status");
			email = (String) resetCreds.getEmail();
		}

	}
%>

<body>

	<h2>Reset Password</h2>
	<div class="centerIt" id="status"><%=status%></div>

	<div id="inputForm">
		<form action="Reset_PostCred" method="post">
			<table class="formTable">
				<colgroup>
					<col class="formLabelBox">
					<col>
					<col class="formLabelBox">
				</colgroup>
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr>

					<td class="rightIt">Email Address</td>
					<td><input type="text" name="email" value="<%=email%>"
						disabled="disabled" /> <input type="hidden" name="userID"
						value="<%=userID%>" /></td>
					<td></td>
					<td></td>

				</tr>
				<tr>
					<td class="rightIt">Password</td>
					<td><input type="password" name="pass1" maxlength="20"
						size="27" /></td>
					<td></td>
				</tr>
				<tr>
					<td class="rightIt">Confirm Password</td>
					<td><input type="password" name="pass2" maxlength="20"
						size="27" /></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
			</table>
			<div class="formControls">
				<input class="jBtn" type="reset" name="reset" value="Reset" />
				&nbsp; <input class="jBtn" type="submit" name="submit"
					value="Reset Password" />
			</div>
		</form>
	</div>
</body>
</html>