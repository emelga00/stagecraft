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

<%
  //loop through the projects ArrayList and put each project on the page
  for(int i = 0; i < projects.size(); i++)
  {
    Project project = (Project) projects.get(i);
%>  
    <h1 id="<%=project.getProjectID()%>"><%=project.getName()%></h1>
    <br />
    <h2>Created By: <%=project.getUserID()%></h2>
    <br />
    <h2>Organization: <%=project.getOrgID()%></h2>
    <br />
    <h2>Description</h2>
    <br />
    <p><%=project.getDesc()%></p>   
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