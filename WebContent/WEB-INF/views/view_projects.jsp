<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*"
%>

<%
  ArrayList<?> projects = (ArrayList<?>)request.getAttribute("projects");
  
  if(projects == null) 
  {
    projects = new ArrayList<Project>();
  }
%>

<h1>View All Projects Page</h1>
<br />
<hr />  
<br />
<%
  //if there are projects in the list, display them
  if (projects.size() > 0)
  {
    //loop through the projects ArrayList and put each project on the page
    for(int i = 0; i < projects.size(); i++)
    {
      Project project = (Project) projects.get(i);
%>  
      ProjectID: <%=project.getProjectID()%> <br />
      Project Name: <%=project.getName()%> <br />
      Project Description: <%=project.getDesc() %> <br />
      Submitted On: <%=project.getCreatedDate() %> <br />
      Submitted By: <a href="UserProfile?userID=<%=project.getUserID()%>"><%=project.getSubmittedBy() %></a> <br />
      Submitted User ID: <%=project.getUserID() %> <br />
      Last Updated On: <%=project.getLastUpdatedDate() %> <br />
      Last Updated By: <%=project.getLastUpdatedBy() %> <br />
      Last Updated ID: <%=project.getLastUpdatedID() %> <br />
      Banner Picture: <%=project.getBannerPicture() %> <br />
      Banner Extension: <%=project.getBannerPicExt() %> <br />
      <br />
      <a href="ViewProject?projectID=<%=project.getProjectID() %>">View Project Page</a>
      <br />
      <br />
      <hr />  
      <br />
<%
    }
  }
  else //no projects found, show a link to the create page
  {
%>
    <p>No projects were found.</p>
<%
  }
%>