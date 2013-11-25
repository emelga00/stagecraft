<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("updatingUser");
	session.removeAttribute("updatingUser");
	
%>
<profile>

	<form name="profile" class="profile-form"
		action="User_PostModify" method="post">
	<div class="profile-header">
			<h1>User Info</h1>
	</div>
	<div class="profile-content">
			First Name
			<input type="text" name="fname" value="<%=user.getFirst_Name()%>" size="30"  />  
			<br>
			<input type="text" name="fname" value="<%=user.getLast_Name()%>" size="30"  />
			<br>
			Email 
			<input type="email" name="email" required class="uprofile-email" value="<%=user.getCreds_Email()%>" size="30" disabled='disabled'/> 
			<br>
			Phone 
			<input type="tel" name="tel" required class="uprofile-phone" value="<%=user.getPhone()%>" size="30" />
			<br>
			Address 
			<input type="text" name="address" class="uprofile-address" value="<%=user.getAddress()%>" size="30" />
			<br>
			City 
			<input type="text" name="city" class="uprofile-city" value="<%=user.getCity()%>" size="30" /> 
			<br>
			State 
			<input type="text" name="state" class="uprofile-state" value="<%=user.getState()%>" size="30" />
			<br>
			Zip Code 
			<input type="text" name="zip" class="uprofile-zip" value="<%=user.getZIP()%>" size="30" />
			<br>
			A Member Since 
			<input type="text" name="joindate" class="uprofile-date" value="<%=user.getDate()%>" size="30" disabled='disabled'/> 
			<br>
			Role
			<input type="text" name="role" class="uprofile-role" value="<%=user.getRole()%>" size="30" disabled='disabled'/> 
			<br>
			

		
		</div>
		<div class="profile-footer">
			<input type='submit' name='submit' value='Update' class='submit' />
		</div>
	</form>
</profile>