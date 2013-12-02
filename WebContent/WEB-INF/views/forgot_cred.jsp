<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList,beans.User,data.UserDB,data.CredentialsDB"%>
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

	String status = (String) session.getAttribute("status");
	session.removeAttribute("status");
	String email = (String) session.getAttribute("email");
	if (email == null) {
		email = "";
	}
	if (status == null) {
		status = "";
	}
%>

<body>

	<h2>Enter Your Email Address</h2>
	<div class="centerIt" id="status"><%=status%></div>

	<div id="inputForm">
		<form action="SendForgotCred" method="post">
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
					<td class="rightIt">Email</td>
					<td><input type="text" name="email" maxlength="40" size="27"
						value="<%=email%>" /></td>
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