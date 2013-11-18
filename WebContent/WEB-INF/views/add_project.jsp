<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>   

<div id="project-form">
  <form name="AddProject" class="project" action="Create_Post" method="POST">
    <div id="project-header" class="header">
      <h1>Project Information</h1><br>
    </div>
    <p>
      Project Name:*
      <br />
      <input type="text" name="name" size="50" Title="Please provide a name for this project. The name should be short but descriptive."/>
    </p>
    <br><br><br>
    <p>Description:*
      <br />
      <textarea rows="15" cols="78" name="description" Title="This area is for the description of your project. Please describe your project, what it does, how it was used, etc."></textarea>
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