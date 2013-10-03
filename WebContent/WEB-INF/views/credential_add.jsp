<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,beans.User,data.UserDB,data.CredentialsDB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a New Credential</title>
</head>

<%
	/*****************
 *  Author: JLH
 *****************/

	
	
	String status = (String)session.getAttribute("status");
	session.removeAttribute("status");
	String email = (String)session.getAttribute("email");
	if(email==null) 
	{email="";}
	if(status==null) 
		{status="";}
	User client = (User)session.getAttribute("client");
	int cliID = client.getUser_ID();
	String userID = cliID+"";
	String clientName = client.getLast_Name() + ", "+ client.getFirst_Name();
%>

<body>

<h2>Create Username and Password</h2>
<div class="centerIt" id="status"><%=status%></div>

<div id="inputForm">
	<form action="Cred_PostAdd" method="post">
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
				
				<td class="rightIt">Client</td>
				<td>
					<input type="text" name="clientIDDisp" value="<%=clientName%>" disabled="disabled" />
					<input type="hidden" name="userID" value="<%=userID%>" />
				</td>
				<td></td>
				<td></td>
				
			</tr>
			<tr>
				<td class="rightIt">Email</td>
				<td><input type="text" name="email" maxlength="40" size="27" value="<%=email%>"/></td>
				<td></td>
			</tr>
			<tr>
				<td class="rightIt">Password</td>
				<td><input type="password" name="pass1" maxlength="20" size="27" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="rightIt">Confirm Password</td>
				<td><input type="password" name="pass2" maxlength="20" size="27" /></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
		</table>
		<div class="formControls">
			<input class="jBtn" type="reset" name="reset" value="Reset" /> &nbsp; 
			<input class="jBtn" type="submit" name="submit" value="Add credentials" />
		</div>
	</form>
</div>
</body>
</html>