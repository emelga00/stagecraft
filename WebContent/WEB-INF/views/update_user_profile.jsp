<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<%
	User user = (User) session.getAttribute("updatingUser");
	
	
%>
<update-profile>

	<form name="registration" class="modify-form"
		action="UserProfile_PostModify" method="post">
		
	<div class="modify-header"><h1>User Info</h1></div>
	<div class="modify-content">
			<label>First Name:</label>
			<input type="text" name="fName" value="<%=user.getFirst_Name()%>" size="30"  maxlength='30'/>  
			<label>Last Name:</label>
			<input type="text" name="lName" value="<%=user.getLast_Name()%>" size="30"  maxlength='30'/>
			<label>Email:</label>
			<input type="text" name="email" required class="modify-email" value="<%=user.getCreds_Email()%>" size="30" disabled='disabled'/> 
			<label>Phone:</label>
			<input type="tel" name="phone" required class="modify-phone" value="<%=user.getPhone()%>" size="30" maxlength='10'/>
			<label>Address:</label>
			<input type="text" name="address" class="modify-address" value="<%=user.getAddress()%>" size="30" maxlength='40'/>
			<label>City:</label> 
			<input type="text" name="city" class="modify-city" value="<%=user.getCity()%>" size="30" maxlength='20'/> 
			<label>State:</label>
			<input type="text" name="state" class="modify-state" value="<%=user.getState()%>" size="30" maxlength='2'/>
			<label>Zipcode:</label>
			<input type="text" name="zip" class="modify-zip" value="<%=user.getZIP()%>" size="30" maxlength='5'/>
			<label>Member Since:</label>
			<input type="text" name="joindate" class="modify-date" value="<%=user.getDate()%>" size="30" disabled='disabled'/> 
			<label>Role:</label>
			<input type="text" name="role" class="modify-role" value="<%=user.getRole()%>" size="30" disabled='disabled'/> 
			<input type="hidden" name="userid" value="<%=user.getUser_ID()%>" />
	</div>
	<div class="modify-footer">
		<input type='submit' name='submit' value='Update' class='submit' />
	</div>
	</form>
</modify-profile>