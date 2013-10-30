<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*,data.*"
%>
<%
/*****************************************************
 *  Author: BDS
 *  Description: View Submissions based on Project
 ****************************************************/
  String projectID  = (String) request.getAttribute("projectID");
  Project project = ProjectsDB.getProjByPID(projectID);
  
  ArrayList<Submission> vidSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> imgSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> planSubmissions = new ArrayList<Submission>();
  
  vidSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("video");
  imgSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("image");
  planSubmissions = SubmissionsDB.getSubmissionsByTypeSortCategory("plan");
  
  request.removeAttribute("projectID");
  
  if(vidSubmissions == null && imgSubmissions == null && planSubmissions == null) 
  {
    //show add new submission page instead
    %><h1><%=project.getName()%> - Submissions</h1>
      <h2>Currently there is no content for this project</h2><%
  }
  else
  {
%>
	<h1><%=project.getName()%> - Submissions</h1>
<%//need to add three different loops to sort each one by type%>
	<table>
	<tr>
	<td>
	<table>
		<tr>
		<td>Videos</td>
		</tr>
		</table>
		</td>
		</tr>
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
	<div style="overflow:auto;">
	<table>
<%
  for(int i = 0; i < vidSubmissions.size(); i++)
  {
    Submission submission = (Submission)vidSubmissions.get(i);
%>  	<tr>
<% 
	if(submission.getURL()==null)
	  {
%>
	    <td><%=submission.getBlob()%></td>
<%	  }
	else
	  {
%>      <td><a href=<%=submission.getURL()%>>Click To View</a></td>
<%	  }%>
	    <td>Created by: <%=submission.getUserID()%></td>
<%
	if(submission.getCategory()!=null)
	  {
%>
	    <td><%=submission.getCategory()%>
<%	    
	  }
	else
	  {
	    %><td>None</td><%
	  }
%>		<td><%=submission.getDate()%></td><%
    }
%>
</tr>
</table>
</div>
</td>
</tr>
</table>
	<table>
	<tr>
	<td>
	<table>
		<tr>
		<td>Images</td>
		</tr>
		</table>
		</td>
		</tr>
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
	<div style="overflow:auto;">
	<table>
<%
  for(int i = 0; i < imgSubmissions.size(); i++)
  {
    Submission submission = (Submission)imgSubmissions.get(i);
%>  	<tr>
<% 
	if(submission.getURL()==null)
	  {
%>
	    <td><%=submission.getBlob()%></td>
<%	  }
	else
	  {
%>      <td><a href=<%=submission.getURL()%>>Click To View</a></td>
<%	  }%>
	    <td><%=submission.getUserID()%></td>
<%
	if(submission.getCategory()!=null)
	  {
%>
	    <td><%=submission.getCategory()%>
<%	    
	  }
	else
	  {
	    %><td>None</td><%
	  }
%>		<td><%=submission.getDate()%></td><%
    }
%>
</tr>
</table>
</div>
</td>
</tr>
</table>
	<table>
	<tr>
	<td>
	<table>
		<tr>
		<td>Plans</td>
		</tr>
		</table>
		</td>
		</tr>
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
	<div style="overflow:auto;">
	<table>
<%
  for(int i = 0; i < planSubmissions.size(); i++)
  {
    Submission submission = (Submission)planSubmissions.get(i);
%>  	<tr>
<% 
	if(submission.getURL()==null)
	  {
%>
	    <td><%=submission.getBlob()%></td>
<%	  }
	else
	  {
%>      <td><a href=<%=submission.getURL()%>>Click To View</a></td>
<%	  }%>
	    <td><%=submission.getUserID()%></td>
<%
	if(submission.getCategory()!=null)
	  {
%>
	    <td><%=submission.getCategory()%>
<%	    
	  }
	else
	  {
	    %><td>None</td><%
	  }
%>		<td><%=submission.getDate()%></td><%
    }
%>
</tr>
</table>
</div>
</td>
</tr>
</table>
<%}%>