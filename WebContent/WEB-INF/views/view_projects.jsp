<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*"
%>
<%
  final int displayNumber = 10;
  int index, index1;

  String pageUrl = (String) request.getAttribute("pageUrl");
  
  ArrayList<?> projects = (ArrayList<?>) request.getAttribute("projects");
  
  if(projects == null) 
  {
    projects = new ArrayList<Project>();
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

<h1>View All Projects Page</h1>
<br />
<hr />  
<br />
<%
  //loop through the projects ArrayList and put each project on the page
  for(int i = 0; i < projects.size(); i++)
  {
    Project project = (Project) projects.get(i);
%>  
    ProjectID: <%=project.getProjectID()%> <br />
    Project Name: <%=project.getName()%> <br />
    Project Description: <%=project.getDesc() %> <br />
    Submitted On: <%=project.getCreatedDate() %> <br />
    Submitted By: <%=project.getSubmittedBy() %> <br />
    Submitted User ID: <%=project.getUserID() %> <br />
    Last Updated On: <%=project.getLastUpdatedDate() %> <br />
    Last Updated By: <%=project.getLastUpdatedBy() %> <br />
    Last Updated ID: <%=project.getLastUpdatedID() %> <br />
    Organization ID: <%=project.getOrganizationID() %> <br />
    Organization Name: <%=project.getOrganization() %> <br />
    Banner Picture ID: <%=project.getBannerPicID() %> <br />
    Banner Picture: <%=project.getBannerPicture() %> <br />
    <br />
    <a href="ViewProject?projectID=<%=project.getProjectID()%>">View Project Page</a>
    <br />
    <br />
    <hr />  
    <br />
<%
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

  if (projects.size() == 5)
  {
%>            
    <a href="<%=request.getContextPath()%><%=pageUrl%>?startPost=<%=projectNumber + displayNumber%>">Next 5 Entries</a>
<%        
  }
%>  
  </span>
</p>