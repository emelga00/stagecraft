<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="data.*,beans.*" %>
<%
	Student student = (Student) request.getAttribute("student");
	String action = "updateStudent";
	if(student==null){
		student = new Student();
		action = "addStudent";
	}
%>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Student Form</title>
</head>
<body>
	<form action="<%=action%>" method="post">
	
<%
	if(action.equalsIgnoreCase("addStudent")){
%>
		<p>StudentID<br/>
		<input type="text" name="studentID" value="<%=student.getStudentID()%>"  /></p>
<%
	}else{
%>
		<p>StudentID<br/>
		<%=student.getStudentID()%>
		<input type="hidden" name="studentID" value="<%=student.getStudentID()%>"  /></p>
<%
	}
%>
		<p>First Name<br/>
		<input type="text" name="firstName" value="<%=student.getFirstName()%>" /></p>
		
		<p>Last Name<br/>
		<input type="text" name="lastName" value="<%=student.getLastName()%>"/></p>

		<p>City<br/>
		<input type="text" name="city" value="<%=student.getCity()%>"/></p>
		
		<p>State<br/>
		<input type="text" name="state" value="<%=student.getState()%>"/></p>
		
		<p>Age<br/>
		<input type="text" name="age" value="<%=student.getAge()%>"/></p>
		
		<input type="submit" name="submit" value="Save Changes" />
	
	</form>
	
</body>
</html>