<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	/***************
	 *  Author: JLH
	 ***************/

	String user = (String) request.getParameter("user");

	String key = (String) request.getParameter("key");
	int user_ID = 0;
	int results = 1;
	String email = "";
	String fName = "";
	String lName = "";
	String loginEmail ="";	
	String status;
	if (user != null || key != null) {
		user_ID = Integer.parseInt(user);
		loginEmail = (String) CredentialsDB.getEmailByUserID(user_ID);
		results = CredentialsDB.validated(user_ID, key);
		if(results>0){
			key = "";
			CredentialsDB.setKey(user_ID,key);
		}
		
	}else{
		email = (String) session.getAttribute("email");
		fName = (String) session.getAttribute("fName");
		lName = (String) session.getAttribute("lName");
		session.removeAttribute("email");
		session.removeAttribute("fName");
		session.removeAttribute("lName");
		
	}
	//finish forgot pass, put into another landing page

	status = (String) session.getAttribute("status");
	session.removeAttribute("status");
	if (status == null) {
		status = "";
	}
	if (email == null) {
		email = "";
	}
	if (fName == null) {
		fName = "";
	}
	if (lName == null) {
		lName = "";
	}
%>

<div id="register-page">
	<div id="register">

		<form name="registration" class="register-form" action="Cred_PostAdd"
			method="post">
			<div class="register-header">
				<h1>Register</h1>
			</div>
			<div class="register-content">
				<label>First Name:</label> 
				<input type="text" name="fName" class="register-fname" value="<%=fName%>" size="35" />
				<label>Last Name:</label> 
				<input type="text" name="lName" class="register-lname" value="<%=lName%>" size="35" />
				<label>Email: </label> 
				<input type="text" name="email" class="register-email" value="<%=email%>" size="35" />
				<label>Password: </label> 
				<input type="password" name="pass1" class="register-password" size="35" /> 
				<label>Confirm Password:</label> 
				<input type="password" name="pass2" class="register-confpassword" size="35" /><br>
				<div class="clear"></div>
				<input type="checkbox" name="terms" value="agree" /> I accept the Terms of Service<br><br>
			</div>
			<div class="register-footer">
				<input type="reset" name="reset" value="Reset" class="reset" /> 
				<input type="submit" name="submit" value="Resend Verification"	class="submit" />
				<input type="submit" name="submit" value="Sign Up" class="submit" /> 
			</div>
		</form>
	</div>
	<div id="login">
		<form name="login-form" class="form" action="Authenticate"
			method="post">
			<div class="header">
				<h1>Already a Member?</h1>
			</div>
			<div class="content">
				<% if(loginEmail!=""){
					out.println("<input name='username' type='text' class='input username' value='"+loginEmail+"' size='35'/>");
				}else{
					out.println("<input name='username' type='text' class='input username' placeholder='Username' size='35'/>");
				}
				%>
				<br>
				<input name="password" type="password" class="password-text" placeholder="Password" size='35'/> <br>
			</div>
			<div class="footer">
				<input type="submit" name="submit" value="Login" class="button" />
				<input type="submit" name="submit" value="Forgot Password?" class="password" />
			</div>
		</form>
	</div>
</div>
<div class="clear"></div>
<div class="error message"><%=status%></div>