<%@ 
page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.ArrayList, beans.*, data.*"
%>
<%
	String projectID = (String) session.getAttribute("projectID");
	int projID = Integer.parseInt(projectID);
	Project project = ProjectsDB.getProjByPID(projectID);
	request.removeAttribute("projectID");
	
	ArrayList<?> vidSubmissions  = (ArrayList<?>) request.getAttribute("videos");
	ArrayList<?> imgSubmissions  = (ArrayList<?>) request.getAttribute("images");
	ArrayList<?> planSubmissions = (ArrayList<?>) request.getAttribute("plans");
	
	/*cannot reach submissionDB
	ArrayList<?> vidSubmissions = SubmissionDB.getSubmissionsByTypeSortCategory("video", projID);
	ArrayList<?> imgSubmissions = SubmissionDB.getSubmissionsByTypeSortCategory("image", projID);
	ArrayList<?> planSubmissions = SubmissionDB.getSubmissionsByTypeSortCategory("plan", projID);*/
%>
	<h1><%=project.getName()%> - Submissions </h1>
	<table>
	<tr>
		<td>
		<table><tr><td>Videos</td></tr></table>
		</td>
	</tr>
	<tr>
		<td>
		<table>
			<tr>
			<td>Submission</td>
			<td>User</td>
			<td>Category</td>
			<td>Date Submitted</td>
			</tr>
		</table>
	</td>
	</tr>
	<tr>
	<td>
		<div style="overflow:auto">
		<table>
		<%for(int i = 0; i< vidSubmissions.size(); i++)
		  {
			Submission submission = (Submission) vidSubmissions.get(i);%>	
			<tr><%	
			if(submission.getBlob() != null)
		  	  {%>	  
		  	  	<td><%=submission.getBlob()%></td><%
		  	  }
			else
			  {%>
			  	<td><a href=<%=submission.getURL()%>>Click to View</a></td><%				    
			  }%>
				
			<td><%=submission.getUserName()%></td><%
			
			if(submission.getCategory()!=null)
			  {%>
				<td><%=submission.getCategory() %></td><%
			  }
			else
			  {%>
				<td>None</td><%
			  }%>
			
			<td><%=submission.getDate() %></td><%
			  }%>
		</tr>
		</table>
		</div>
		</td>
		</tr>
	</table>
	
	<table>
	<tr>
		<td>
		<table><tr><td>Images</td></tr></table>
		</td>
	</tr>
	<tr>
		<td><table><tr>
			<td>Submission</td>
			<td>User</td>
			<td>Category</td>
			<td>Date Submitted</td>
			</tr></table>
		</td>
	</tr>
	<tr>
		<td><div style="overlfow:auto;"><table>
	<%for(int i = 0; i < imgSubmissions.size(); i++)
	  {
	    Submission submission = (Submission)imgSubmissions.get(i);%>		
			<tr>
		<%if(submission.getBlob()!=null)
		  {
		    %><td><%=submission.getBlob()%></td><% 
		  }
		else
		  {
		    %><td><a href=<%=submission.getURL()%>></a></td><%
		  }%>
			<td><%=submission.getUserName()%></td>
		<%
		if(submission.getCategory()!=null)
		  {
		    %><td><%=submission.getCategory()%></td><%
		  }
		else
		  {
		    %><td>None</td><%
		  }
		%><td><%=submission.getDate()%></td><%
	  } %>			
			</tr></table></div>
		</td>
	</tr>
	</table>
	
		<table>
	<tr>
		<td>
		<table><tr><td>Plans</td></tr></table>
		</td>
	</tr>
	<tr>
		<td><table><tr>
			<td>Submission</td>
			<td>User</td>
			<td>Category</td>
			<td>Date Submitted</td>
			</tr></table>
		</td>
	</tr>
	<tr>
		<td><div style="overlfow:auto;"><table>
	<%for(int i = 0; i < planSubmissions.size(); i++)
	  {
	    Submission submission = (Submission)planSubmissions.get(i);%>		
			<tr>
		<%if(submission.getBlob()!=null)
		  {
		    %><td><%=submission.getBlob()%></td><% 
		  }
		else
		  {
		    %><td><a href=<%=submission.getURL()%>></a></td><%
		  }%>
		  	<td><%=submission.getUserName()%></td>
		  <%
		if(submission.getCategory()!=null)
		  {
		    %><td><%=submission.getCategory()%></td><%
		  }
		else
		  {
		    %><td>None</td><%
		  }
		%><td><%=submission.getDate()%></td><%
	  }%>			
			</tr></table></div>
		</td>
	</tr>
	</table>