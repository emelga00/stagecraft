<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="data.*,beans.*,java.util.*" %>
  
<%
	ArrayList<?> students = (ArrayList<?>) request.getAttribute("students");
	Student student;
	int index;
	String message = (String) session.getAttribute("message");
	if(message==null){
		message = "";
	}else{
		session.removeAttribute("message");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Administration Page</title>
</head>
<body>
	<p><a href="addStudent">Add Student</a></p>
	<form action="admin" method="post">
	<table class="gridtable">
		<tr><th></th><th>Student ID</th><th>First Name</th><th>Last Name</th><th>Age</th></tr>
<%
		for(index=0;index<students.size();index++){
			student = (Student) students.get(index);
%>
			<tr><td><input type="radio" name="studentID" value="<%=student.getStudentID()%>" /></td>
			<td><%=student.getStudentID()%></td>
			<td><%=student.getFirstName()%></td>
			<td><%=student.getLastName()%></td>
			<td><%=student.getAge()%></td></tr>
<%
		}
%>
	</table>
	<input type="submit" name="submit" value="Remove Student" />
	<input type="submit" name="submit" value="Edit Student" />
	<span class="message"><%=message%></span>
	</form>
</body>
</html>