<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,beans.*,data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table {
	font-size:0.9em;
}
</style>
</head>

<%
	/***************
 *  Author: BAC
 ***************/
 
	int index;
	ArrayList<?> users;
	User user;
	
	//handle status form submissions
	String status = (String)session.getAttribute("status");
	session.removeAttribute("status");
	if(status == null)
		status = "";
	
	//**** Get the data list placed in Request or Session Scope *******
	users = (ArrayList<?>) request.getAttribute("userList");
	request.removeAttribute("userList");
	if(users == null)
		users = new ArrayList<User>();
	
	String result = (String)request.getParameter("result");
	if(result == null){
		result = "";
	}
	if(result.equals("x")){
		result="Attempted Action Cancelled!";
	}
%>


<body>

<!-- Start page content -->
<h2 class="centerIt" >User Administration Console</h2>
<%
	if(result.equals("")){
out.println("<div class='centerIt' id='status'>"+status+"</div>");
}
else{
out.println("<div class='centerIt' id='status'>"+result+"</div>");
}
%>
<div class="centerIt" id="navLinks"><a class="jBtn" href="ClientAdminMenu">Back to the client admin menu</a><br /><br /></div>

<div id="listHolder">
	<table class="listTable">
		<tr>
			<th>User<br />ID</th>
		    <th>First<br />Name</th>
		    <th>Last<br />Name</th>
		    <th>Phone</th>
		    <th>Address</th>
		    <th>Date</th>
		    <th>Role</th>
		    <th>Credential<br />ID</th>
		    <th> -- </th>
		    <th> -- </th>
			<th>Verification</th>
			<th> -- </th>
			<th> -- </th>
		</tr>
		<%
			String contCharges = "";
				String fullPhone;
				String areacode;
				String phone;
				int cred_ID;
				String credID;
				String email;
				String pass;
				String role;
				int valid;
				String notValid;
				int rowToggle = 0;
				for(index = 0; index < users.size(); index++){
			
					user = (User)users.get(index);

			
				int uID = user.getUser_ID();
				Credentials creds = CredentialsDB.getCredentialByUserID(uID);
				if(creds==null){
					cred_ID = 0;
					email = " - ";
					pass = " - ";
					role = " - ";
					valid = 1;
				}else{
					cred_ID = creds.getCredID();
					email = creds.getEmail();
					pass = creds.getPass();
					role = creds.getRole();
					valid = creds.getValid();
				}
				if(cred_ID==0){
					credID="TBD";
				}else{
					credID=Integer.toString(cred_ID);
				}
				if(valid==0){
					notValid="Verified";
				}else{
					notValid = "Not Verified";
				}
				fullPhone = user.getPhone();
				areacode = fullPhone.substring(0,3);
				phone = fullPhone.substring(3,6)+"-"+fullPhone.substring(6,10);
			
		%>
			<%
				if(rowToggle%2 != 0) {
			%>
				<tr class="oddRow">
			<%
				} else {
			%>
				<tr class="evenRow">
			<%
				}
			
			%>
				<td class="centerIt"><span class="idText"><%=user.getUser_ID()%></span></td>
				<td><%=user.getFirst_Name()%></td>
				<td><%=user.getLast_Name()%></td>
				<td class="centerIt">(<%=areacode%>)<br > <span  style="white-space: nowrap;"><%=phone%></span></td>
				<td><%=user.getAddress()%> <br><span  style="white-space: nowrap;"><%=user.getCity()%>, <%=user.getState()%>. <%=user.getZIP()%></span></td>
				<td class="centerIt"><%=user.getDate()%></td>
				<td class="centerIt"><%=role%></td>
				<td class="centerIt"><%=credID%></td>
				<td class="centerIt">E-Mail:<br /><%=email%></td>
				<td class="centerIt">Password:<br /><%=pass%></td>
				<td class="centerIt"><%=notValid%></td>
				<td><a class="jBtn" href="Client_Modify?clientID=<%=user.getUser_ID()%>">Modify</a></td>
				<td><a class="jBtn" href="Client_Delete?clientID=<%=user.getUser_ID()%>">Delete</a></td>
			</tr>
		<%
		rowToggle++;
		}
		%>
	</table>
</div>

</body>
</html>