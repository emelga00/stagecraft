<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*"
%>
<%
/*****************************************************
 *  Author: BDS
 *  Description: View Submissions based on Project_ID
 ****************************************************/

  final int displayNumber = 10;
  int index, index1;

  String pageUrl = (String) request.getAttribute("pageUrl");
  
  ArrayList<?> submissions = (ArrayList<?>) request.getAttribute("submissions");
  
  if(submissions == null) 
  {
    submissions = new ArrayList<Submission>();
  }
  
  String projectID = (String)request.getAttribute("projectID");
  int projectNumber;
  
  if (projectID == null)
  {
    //send a redirect to the viewProjects page
  }
  else
  {
    projectNumber = Integer.parseInt(projectID);
  }
%>

<h1>View Submissions Page</h1>

<%//need to add three different loops to sort each one by type
  //loop through the submission ArrayList and put each submission on the page
  for(int i = 0; i < submissions.size(); i++)
  {
    Submission submission = (Submission)submissions.get(i);
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