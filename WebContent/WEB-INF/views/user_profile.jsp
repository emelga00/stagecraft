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
			Name:    <%=user.getFirst_Name()%> <%=user.getLast_Name()%>
			<br>
			Email:   <%=user.getCreds_Email()%>
			<br>
			Phone:   <%=user.getPhone()%>
			<br>
			Address: <%=user.getAddress()%>
			<br>
			City:    <%=user.getCity()%>
			<br>
			State:   <%=user.getState()%>
			<br>
			Zipcode: <%=user.getZIP()%>
			<br>
		
		</div>
		<div class="clear"></div>
		<div class='button'>Update Profile</class></div>
	</div>
	<div class="clear"></div>
	<div class="profile-footer">
	 
	</div>
	<br><br>
</profile>