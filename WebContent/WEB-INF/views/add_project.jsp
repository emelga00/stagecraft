<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>   

<div id="project-form">
<form name="AddProject" class="project" action="Create" method="POST">
  <div id="project-header" class="header">
    <h1>Project Information</h1><br>
    <p>
    </div>
      Project Title:*
      <br />
      <input type="text" name="title" size="50" Title="Please provide a title for this project. The title should be short but descriptive."/>
    </p>
    <br><br><br>
    <p>Description:*
      <br />
      <textarea rows="15" cols="78" name="description" Title="This area is for the description of your project. Please describe your project, what it does, how it was used, etc."></textarea>
    </p>
  </fieldset>
    
  <br />
    
  <fieldset>
    <p id="plansSection">
    <label for="plans" class="button" title="This area allows you to upload any plans you have for this project such as AutoCAD, PDFs, etc.">
    Project Plans</label><br>
      <input type="file" id="plans" class="file" name="planUploads" Title="This area allows you to upload any plans you have for this project such as AutoCAD, PDFs, etc.">
    </p>        
  </fieldset> 
    
  <br /> 
    
  <fieldset>
    <p>
      <label for="videos" class="button" Title="This area allows you to upload or link any videos.">
      Tutorial Videos </label>
      <br />
      <input type="file" id="videos" class="file" name="planUploads" >
    </p>          
  </fieldset> 
          
  <br /> 
    
  <fieldset>
    <p><label for="pictures" class="button" Title="This area allows you to upload or link any pictures.">
    Pictures </label>
      <br />
      <input type="file" id="pictures" class="file" name="planUploads" >
    </p>              
  </fieldset> 
 <br><br>
 <div id="project-footer" class="footer">
  <input class="button" type="submit" name="submit" value="Submit Project" />
  <input class="button" type="submit" name="submit" value="Cancel" />
  </div>
  
</form>
</div>