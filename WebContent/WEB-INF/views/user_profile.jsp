<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("userProfile");
	int currentUserID = (Integer) session.getAttribute("currentUserID");
	String disabled = "disabled='disabled'";
	Boolean cUser = false;
	if (user.getUser_ID()==currentUserID){
		cUser=true;
		disabled = "";
	}
	System.out.println("currentUserID is "+currentUserID);
	System.out.println("user.getFirst_Name is "+user.getFirst_Name());
	
%>
<div id="register">

	<form name="registration" class="register-form"
		action="User_PostModify" method="post">
		<div class="register-header">
			<h1>User Info</h1>
		</div>
		<div class="register-content">
			First Name 
			<input type="text" name="fName" class="uprofile-fName" value="<%=user.getFirst_Name()%>" size="30" <%=disabled%>/>
			<br>
			Last Name 
			<input type="text" name="lName" class="uprofile-lName" value="<%=user.getLast_Name()%>" size="30" <%=disabled%>/>
			<br>
			Email 
			<input type="text" name="email" class="uprofile-email" value="<%=user.getCreds_Email()%>" size="30" <%=disabled%>/> 
			<br>
			Phone 
			<input type="text" name="fName" class="uprofile-phone" value="<%=user.getPhone()%>" size="30" <%=disabled%>/>
			<br>
			Address 
			<input type="text" name="lName" class="uprofile-address" value="<%=user.getAddress()%>" size="30" <%=disabled%>/>
			<br>
			City 
			<input type="text" name="email" class="uprofile-city" value="<%=user.getCity()%>" size="30" <%=disabled%>/> 
			<br>
			State 
			<input type="text" name="fName" class="uprofile-state" value="<%=user.getState()%>" size="30" <%=disabled%>/>
			<br>
			Zip Code 
			<input type="text" name="lName" class="uprofile-zip" value="<%=user.getZIP()%>" size="30" <%=disabled%>/>
			<br>
			A Member Since 
			<input type="text" name="email" class="uprofile-date" value="<%=user.getDate()%>" size="30" <%=disabled%>/> 
			<br>
			Role
			<input type="text" name="email" class="uprofile-role" value="<%=user.getRole()%>" size="30" <%=disabled%>/> 
			<br>
			

		
		</div>
<% if(cUser){
	out.println("<div class='register-footer'>");
		out.println("<input type='submit' name='submit' value='Cancel' class='submit' />");
		out.println("<input type='reset' name='reset' value='Reset' class='reset' /> ");
			out.println("<input type='submit' name='submit' value='Submit' class='submit' />");

	out.println("</div>");
	}%>
	</form>
</div>