<%@ page import="java.util.Date, java.text.*,beans.*" %>
<%
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
div id="status"><%=status%></div>
<div id="login">
	<form name="login-form" class="login-form" action="Authenticate" method="post">
		<div class="header">
		<h1>Login</h1>
		</div>
	
		<div class="content">
		<input name="username" type="text" class="input username" placeholder="Username" />
		<div class="user-icon"></div>
		<input name="password" type="password" class="input password" placeholder="Password" />
		<div class="pass-icon"></div>		
		</div>

		<div class="footer">
		<input type="submit" name="submit" value="Login" class="button" />
		<input type="submit" name="submit" value="Register" class="register" />
        <input type="submit" name="submit" value="Password" class="password" />
		</div>
	</form>
</div>
