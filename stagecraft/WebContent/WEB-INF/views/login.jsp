<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

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

<img src="images/logo.png" ></img><br>
<hr style="width:60%; height: 12px; border: 0; box-shadow: inset 0 12px 12px -12px rgba(0,0,0,0.5);">
<div id="main" >
<div id="homePage" >
<img src="images/introTitle.png" ></img>
<p>
The Republican criticism of Attorney General Eric Holder and the Justice Department -- first, over the failed Fast and Furious gun-tracking operation and now, the subpoena of reporters phone and email records -- is growing to include outcry from liberal media outlets and Democrats. 
</p>
<p>
It seems to me clear that the actions of the department have in fact impaired the First Amendment, Rep. Zoe Lofgren, D-Calif., said earlier this month. Reporters who might have previously believed that a confidential source would speak to them would no longer have that level of confidence.
</p>
<p>
The Republican criticism of Attorney General Eric Holder and the Justice Department -- first, over the failed Fast and Furious gun-tracking operation and now, the subpoena of reporters phone and email records -- is growing to include outcry from liberal media outlets and Democrats. 
</p>
</div>

<div id="loginForm" >
<img src="images/login.png" ></img>
<div id="status"><%=status%></div>

<div id="inputForm">
	<form action="Authenticate" method="post">
		<table class="formTable">
			
			<tr>
				<td id="logtext">Email</td>
				<td><input type="text" name="username" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="rightIt"></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td id="logtext">Password</td>
				<td><input type="password" name="password" /></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
		</table>
		<div class="formControls">
			<input class="jBtn" type="submit" name="submit" value="Login" />
		</div>
	</form>
</div>
<div id="forPass" >
<a href="Login" >Forgot Password</a>
</div>
<div id="reg">
	<a  href="User_Add">Register</a>
</div>	
<div id="noreg">
New to WorldOfStagecraft?<br>
</div>
</div>
</div>
<div>
<br><br>
<iframe width="560" height="315" src="//www.youtube.com/embed/ytFSehs2Rxw" frameborder="0" allowfullscreen></iframe>
</div>