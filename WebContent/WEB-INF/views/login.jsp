<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
    
<%
/***************
 *  Author: JLH
 ***************/

	
	
	String status = (String)session.getAttribute("status");
	session.removeAttribute("status");
	
	//**** Get the data list placed in Request or Session Scope *******
	
	//reset status incase user cancels out of form
    session.setAttribute("status", "Add client cancelled...");
%>

   <div id="register-page">
   <div id="register">
	<form name="registration" class="register-form" action="User_PostAdd" method="post">
    	<div class="register-header">
        	<h1>Register</h1>
        </div>
        <div class="register-content">
        	Name				<input type="text" name"user" class="register-name" size="30" /><br><br>
         	Email			 	<input type="text" name="user"  class="register-email" size="30"/> <br><br>
            Password	 		<input type="text" name="user"  class="register-password" size="30"/> <br><br>
            Confirm Password	<input type="text" name="user"  class="register-confpassword" size="30"/> <br> <br>
            Organization		<input type="text" name="user"  class="register-org" size="30"/> <br><br>
            Zip					<input type="text" name="user"  class="register-zip" placeholder="or Postal Code" size="30"/> <br><br>
            <input type="checkbox" name="check" value="agree" /> I accept the Terms of Service<br />
        </div>
        <div class="register-footer">
			<input type="reset"  name="reset"  value="Reset"    class="reset"/> 
			<input type="submit" name="submit" value="Sign Up" 	class="submit"/>		
		</div>  
	</form>
    </div>
    <div id="login">
    <form name="login-form" class="login-form" action="Authenticate" method="post">
        <div class="login-header">
            <h2>Already a Member?</h2>
        </div>
        <div class="login-content">
			<input name="username" type="text" class="input username" placeholder="Username" /><br><br>
			<input name="password" type="password" class="input password" placeholder="Password" />	<br><br>	
		</div>
		<div class="login-footer">
			<input type="submit" name="submit" value="Login" class="button" />
        	<input type="submit" name="submit" value="Forgot Password?" class="password" />
		</div>
	</form> 
    </div>
   </div>