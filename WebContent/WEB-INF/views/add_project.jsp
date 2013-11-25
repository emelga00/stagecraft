<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>   

<%
  //if the user failed to enter required information on the add form,
  //they will be redirected back here. The invalid project object will
  //be in session scope. Pull it and remove it.
  Project invalidProject = (Project)session.getAttribute("invalidProject");
  session.removeAttribute("invalidProject");
  
  String name = "";
  String description = "";
  
  //if invalidProject isn't null, store the name and description
  if (invalidProject !=null)
  {
	if (invalidProject.getName() != null)
	{
      name = invalidProject.getName();
	}
	
	if (invalidProject.getDesc() != null)
	{
		description = invalidProject.getDesc();
	}   
  }
%>
<div id="project-form">
  <form name="AddProject" class="project" action="Create_Post" method="POST">
    <div id="project-header" class="header">
      <h1>Project Information</h1><br>
    </div>
    <p>
      Project Name:*
      <br />
      <input type="text" name="name" size="50" value="<%=name%>" Title="Please provide a name for this project. The name should be short but descriptive."/>
    </p>
    <br><br><br>
    <p>Description:*
      <br />
      <textarea rows="15" cols="78" name="description" Title="This area is for the description of your project. Please describe your project, what it does, how it was used, etc."><%=description%></textarea>
    </p>
    <br><br><br>
    <p>Banner Picture:
      <br />
      <input type="file" name="bannerPicture">
    </p>
    <div id="project-footer" class="footer">
      <input class="button" type="submit" name="submit" value="Submit Project" />
      <input class="button" type="reset" value = "Reset Form" />
      <input class="button" type="submit" name="submit" value="Cancel" />
    </div> 
  </form>
</div>