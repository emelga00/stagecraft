<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
    
    <%
    String user = (String) request.getParameter("user");
    
    String key = (String) request.getParameter("key");
    if (user!=null||key!=null){
    	int userID = Integer.parseInt(user);
    	CredentialsDB.validated(userID,key);
    }
    /*****************
     * modified: BAC
     ****************/
    	String status = (String) session.getAttribute("status");
    	if(status==null){
    		status="<br>";
    	}else{
    		session.removeAttribute("status");
    	}
    %>
   <div id="register">
	<form name="registration" class="register-form" action="User_PostAdd" method="post">
    	<div class="register-header">
        	<h1>Register</h1>
        </div>
        <div class="register-content">
         	Email			 	<input type="text" name="user"  class="email" size="35"/> <br><br>
            Username			<input type="text" name="user"  class="username" size="35"/> <br><br>
            Password	 		<input type="text" name="user"  class="password" size="35"/> <br><br>
            Confirm Password	<input type="text" name="user"  class="confpassword" size="35"/> <br> <br>
            Organization		<input type="text" name="user"  class="org" size="35"/> <br><br>
            Zip					<input type="text" name="user"  class="zip" placeholder="or Postal Code" size="35"/> <br><br>
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