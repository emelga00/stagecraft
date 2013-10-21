<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*,data.*"
%>
<%
/*****************************************************
 *  Author: BDS
 *  Description: View Submissions based on Project
 ****************************************************/
  Project project = (Project)request.getAttribute("project");
  
  ArrayList<Submission> vidSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> imgSubmissions  = new ArrayList<Submission>();
  ArrayList<Submission> planSubmissions = new ArrayList<Submission>();
  
  vidSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("video");
  imgSubmissions  = SubmissionsDB.getSubmissionsByTypeSortCategory("video");
  planSubmissions = SubmissionsDB.getSubmissionsByTypeSortCategory("video");
  
  request.removeAttribute("project");
  
  if(vidSubmissions == null && imgSubmissions == null && planSubmissions == null) 
  {
    //show add new submission page instead
  }
%>

<h1><%=project.getName()%> - Submissions</h1>

<%//need to add three different loops to sort each one by type
  //loop through the submission ArrayList and put each submission on the page
  for(int i = 0; i < vidSubmissions.size(); i++)
  {
    Submission submission = (Submission)vidSubmissions.get(i);
%>  
    <h1 id="<%=submission.getProjID()%>"><%=submission.getSubID()%></h1>
    <br />
    <h2>Created By: <%=submission.getUserID()%></h2>
    <br />
<% 
	if(submission.getURL()==null)
	  {
%>
	     <h2><%=submission.getBlob()%></h2>
    	 <br />
<%	  }
	else
	  {
%>     <h2><%=submission.getURL()%></h2>
	    <br />
<%	  }
    }
%>