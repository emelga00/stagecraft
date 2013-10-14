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
  
  String startProject = (String)request.getAttribute("startProject");
  int projectNumber;
  
  if (startProject == null)
  {
    projectNumber = 0;
  }
  else
  {
    projectNumber = Integer.parseInt(startProject);
  }
%>

<h1>View Submissions Page</h1>

<%
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
<%
	  }

	else
	  {
%>	    
	    <h2><%=submission.getURL()%></h2>
	    <br />
<%	     
	  }
  }
%>
<p>
  <span>   
<%
  if (projectNumber > 0)
  {
%>  
    <a href="<%=request.getContextPath()%><%=pageUrl%>?startPost=<%=projectNumber - displayNumber%>">Previous 5 Entries</a> &nbsp;&nbsp;
<%
  }

  if (submissions.size() == 5)
  {
%>            
    <a href="<%=request.getContextPath()%><%=pageUrl%>?startPost=<%=projectNumber + displayNumber%>">Next 5 Entries</a>
<%        
  }
%>  
  </span>
</p>