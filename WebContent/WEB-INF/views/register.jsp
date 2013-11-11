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

		<form name="registration" class="form" action="Cred_PostAdd"
			method="post">
			<div class="header">
				<h1>Register</h1>
			</div>
			<div class="content">
				First Name <input type="text" name="fName" class="fname" value="<%=fName%>" size="30" />
				<br> 
				Last Name 
				<input type="text" name="lName" class="lname" value="<%=lName%>" size="30" />
				<br> 
				Email 
				<input type="text" name="email" class="email" value="<%=email%>" size="30" />
				<br> 
				Password 
				<input type="password" name="pass1" class="password" size="30" /> 
				<br> 
				Confirm Password 
				<input type="password" name="pass2" class="confpassword" size="30" />
				<br>
				<input type="checkbox" name="terms" value="agree" /> I accept the Terms of Service<br />
				
			</div>
			<div class="footer">
				<input type="reset" name="reset" value="Reset" class="reset" /> 
				<input type="submit" name="submit" value="Resend Verification"	class="submit" />
				<input type="submit" name="submit" value="Sign Up" class="submit" /> 
			</div>
		</form>
	</div>
	<div id="login">
		<form name="login-form" class="form" action="Authenticate" method="post">
			<div class="header">
				<h1>Already a Member?</h1>
			</div>
			<div class="content">
				<% if(loginEmail!=""){
					out.println("<input name='username' type='text' class='input username' value='"+loginEmail+"'/>");
				}else{
					out.println("<input name='username' type='text' class='input username' placeholder='Username'/>");
				}
				%>
				<br>
				<input name="password" type="password" class="input password"
					placeholder="Password" /> <br>
			</div>
			<div class="login-footer">
				<input type="submit" name="submit" value="Login" class="button" />
				<input type="submit" name="submit" value="Forgot Password?"
					class="password" />
			</div>
		</form>
	</div>
</div>
<div class="error message"><%=status%></div>