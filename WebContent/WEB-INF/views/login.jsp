<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="data.*,beans.*" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
	
		<p>Username<br/>
		<input type="text" name="userName" /></p>
		
		<p>Password<br/>
		<input type="password" name="password" /></p>
		
		<input type="submit" name="submit" value="Login" />
	
	</form>
	
</body>
</html>