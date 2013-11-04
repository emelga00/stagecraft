<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*,data.*"
%>
<%
/*****************************************************
 *  Author: BDS
 *  Description: View Submissions based on Project
 ****************************************************/
  String projectID  = (String) session.getAttribute("projectID");
  int projID = Integer.parseInt(projectID);
  Project project = ProjectsDB.getProjByPID(projectID);
  
  ArrayList<Submission> vidSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> imgSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> planSubmissions = new ArrayList<Submission>();
  
  vidSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("video", projID);
  imgSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("image", projID);
  planSubmissions = SubmissionsDB.getSubmissionsByTypeSortCategory("plan" , projID);

  request.removeAttribute("projectID");
  
  if(vidSubmissions.isEmpty() && imgSubmissions.isEmpty() && planSubmissions.isEmpty()) 
  {
    //show add new submission button/link instead
    %><h1><%=project.getName()%> - Submissions</h1>
      <h2>Currently there is no content for this project</h2>
      <h2>VidSubmissions.Size : <%=vidSubmissions.size ()%>
      <h2>ImgSubmissions.Size : <%=imgSubmissions.size ()%>
      <h2>PlanSubmissions.Size: <%=planSubmissions.size()%><%
  }
  else
  {
%>
	<h1><%=project.getName()%> - Submissions</h1>
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
	System.out.println("Vid  Size: "+vidSubmissions.size());
	System.out.println("Img  Size: "+imgSubmissions.size());
	System.out.println("Plan Size: "+planSubmissions.size());
  for(int i = 0; i < vidSubmissions.size(); i++)
  {
    Submission submission = (Submission)vidSubmissions.get(i);
%>  	<tr>
<%  System.out.println("URL: "+submission.getURL());
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