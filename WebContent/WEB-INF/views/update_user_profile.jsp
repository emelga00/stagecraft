<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("userProfile");
	String currentUsername = (String) session.getAttribute("currentUsername");
	int currentUserID = 0;
	if(currentUsername !=null){
	currentUserID = (Integer) session.getAttribute("currentUserID");
	}
	String disabled = "disabled='disabled'";
	Boolean cUser = false;
	if (user.getUser_ID()==currentUserID){
		cUser=true;
		disabled = "";
	}
	
	
%>
<profile>

	<form name="profile" class="profile-form"
		action="User_PostModify" method="post">
	<div class="profile-header">
			<h1>User Info</h1>
	</div>
	<div class="profile-content">
			First Name
			<input type="text" name="fname" value="<%=user.getFirst_Name()%>" size="30" <%=disabled%> />  
			<br>
			<input type="text" name="fname" value="<%=user.getLast_Name()%>" size="30" <%=disabled%> />
			<br>
			Email 
			<input type="email" name="email" required class="uprofile-email" value="<%=user.getCreds_Email()%>" size="30" <%=disabled%>/> 
			<br>
			Phone 
			<input type="tel" name="tel" required class="uprofile-phone" value="<%=user.getPhone()%>" size="30" <%=disabled%>/>
			<br>
			Address 
			<input type="text" name="address" class="uprofile-address" value="<%=user.getAddress()%>" size="30" <%=disabled%>/>
			<br>
			City 
			<input type="text" name="city" class="uprofile-city" value="<%=user.getCity()%>" size="30" <%=disabled%>/> 
			<br>
			State 
			<input type="text" name="state" class="uprofile-state" value="<%=user.getState()%>" size="30" <%=disabled%>/>
			<br>
			Zip Code 
			<input type="text" name="zip" class="uprofile-zip" value="<%=user.getZIP()%>" size="30" <%=disabled%>/>
			<br>
			A Member Since 
			<input type="text" name="joindate" class="uprofile-date" value="<%=user.getDate()%>" size="30" <%=disabled%>/> 
			<br>
			Role
			<input type="text" name="role" class="uprofile-role" value="<%=user.getRole()%>" size="30" <%=disabled%>/> 
			<br>
			

		
		</div>
<% if(cUser){
	out.println("<div class='profile-footer'>");
	out.println("<input type='submit' name='submit' value='Update Profile' class='submit' />");

	out.println("</div>");
	}%>
	</form>
</profile>