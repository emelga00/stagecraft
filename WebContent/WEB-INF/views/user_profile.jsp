<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("userProfile");
	session.removeAttribute("userProfile");
	String currentUsername = (String) session.getAttribute("currentUsername");
	int currentUserID = 0;
	if(currentUsername !=null){
	currentUserID = (Integer) session.getAttribute("currentUserID");
	}
	Boolean cUser = false;
	if (user.getUser_ID()==currentUserID){
		cUser=true;
	}
	
	
%>
<profile>
	<div class="profile-header">
		<h1>About</h1>
	</div>
	<div class="profile-content">
		<div id="profile-pic">
			<div id="uPic">USER Picture here!</div>
			<br>
			USER STATS GO HERE<br>
			Example:<br>
			A Member Since: <%=user.getDate()%>
			<br>
			Role: <%=user.getRole()%>
			<br>
			
		</div>
		<div id="profile-info">
			<b>Name:</b>    <%=user.getFirst_Name()%> <%=user.getLast_Name()%>
			<br><br>
			<b>Email:</b>   <%=user.getCreds_Email()%>
			<br><br>
			<b>Phone:</b>   <%=user.getPhone()%>
			<br><br>
			<b>Address:</b> <%=user.getAddress()%>
			<br><br>
			<b>City:</b>    <%=user.getCity()%>
			<br><br>
			<b>State:</b>   <%=user.getState()%>
			<br><br>
			<b>Zipcode:</b> <%=user.getZIP()%>
			<br>
		
		</div>
	</div>
	<div class="clear"></div>
	<div class="profile-footer">
		<% if(cUser){
	out.println("<a href='UpdateUserProfile'>Update Profile</a>");
		session.setAttribute("updatingUser",user);
	}%>
	</div>
	<br><br>
</profile>