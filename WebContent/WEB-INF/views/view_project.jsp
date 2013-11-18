<%@ 
  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
  import="java.util.ArrayList, beans.*"
%>
<%
  String pageUrl = (String) request.getAttribute("pageUrl");
  
  Project project = (Project) request.getAttribute("project");
  
  if(project == null) 
  {
    project = new Project();
  }
%>

<h1>Single Project Page</h1>
<br />
<hr />  
<br />  
    ProjectID: <%=project.getProjectID()%> <br />
    Project Name: <%=project.getName()%> <br />
    Project Description: <%=project.getDesc() %> <br />
    Submitted On: <%=project.getCreatedDate() %> <br />
    Submitted By: <%=project.getSubmittedBy() %> <br />
    Submitted User ID: <%=project.getUserID() %> <br />
    Last Updated On: <%=project.getLastUpdatedDate() %> <br />
    Last Updated By: <%=project.getLastUpdatedBy() %> <br />
    Last Updated ID: <%=project.getLastUpdatedID() %> <br />
    Banner Picture: <%=project.getBannerPicture() %> <br />
    Banner Picture Ext: <%=project.getBannerPicExt() %> <br />
    <%System.out.println("project id is "+project.getProjectID()); %>
    <a href="ViewSubmissions?projectID=<%=project.getProjectID()%>">View Submissions</a>