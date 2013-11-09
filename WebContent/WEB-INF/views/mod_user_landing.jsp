<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,beans.*,data.*"%>

</head>

<%
	/***************
 *  Author: JLH
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



<div id="moderator">
	<div id="mod-header">
		<h1>Moderator Console</h1>
	</div>
	<div id="mod-content">
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
			<th>User ID</th>
		    <th>First Name</th>
		    <th>Last Name</th>
		    <th>Date</th>
		    <th>Role</th>
		    <th>Credential ID</th>
		    <th>Email</th>
			<th>Enabled?</th>
			<th> -- </th>
			<th> -- </th>
		</tr>
		<%
			
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
				String date;
				String mddate;
				String ydate;
				String currentRole = (String)session.getAttribute("currentRole");
				
				for(index = 0; index < users.size(); index++){
			
					user = (User)users.get(index);


				date = user.getDate();
				int uID = user.getUser_ID();
				Credentials creds = CredentialsDB.getCedentialByUser_ID(uID);
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
				System.out.println("currentRole =  "+ currentRole+ "and role = "+ role);
				if((currentRole.equals("moderator")&&!role.equals("admin"))||currentRole.equals("admin")){
					
				if(valid==0){
					notValid="Enabled";
				}else{
					notValid = "Disabled";
				}
				fullPhone = user.getPhone();
				if (date==null||date.equals("")){
					mddate= "- / -";
					ydate = " - ";
				}
				else{
					mddate = date.substring(0,5);
					ydate= date.substring(6,10);
				}
				if(fullPhone==null||fullPhone.equals("")){
					areacode = " - ";
					phone = " - ";
				}else{
				areacode = fullPhone.substring(0,3);
				phone = fullPhone.substring(3,6)+"-"+fullPhone.substring(6,10);
				}
		%>
			<tr>
				<td class="centerIt"><span class="idText"><%=uID%></span></td>
				<td><%=user.getFirst_Name()%></td>
				<td><%=user.getLast_Name()%></td>
				<td class="centerIt"><%=mddate+ "\n\n"+ ydate%></td>
				<td class="centerIt"><%=role%></td>
				<td class="centerIt"><%=cred_ID%></td>
				<td class="centerIt"><%=email%></td>
				<td class="centerIt"><%=notValid%></td>
				<td><a class="jBtn" href="User_Modify?userID=<%=user.getUser_ID()%>">Modify</a></td>
				<td><a class="jBtn" href="User_Delete?userID=<%=user.getUser_ID()%>">Delete</a></td>
			</tr>
		<%}} %>
	</table>
	</div>
	<div id="mod-footer"></div>
</div>
</div>
</body>
</html>