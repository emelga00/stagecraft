<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>

<form name="AddProject" class="project" action="AddProject" method="POST">
  <fieldset>
    <legend>Project Information</legend>
    <p>
      Project Title:*
      <img border="0" src="<%=request.getContextPath()%>/images/help.png" Title="Please provide a title for this project. The title should be short but descriptive.">
      <br />
      <input type="text" name="title" size="50" />
    </p>
    <p>
      Description:*
      <img border="0" src="<%=request.getContextPath()%>/images/help.png" Title="This area is for the description of your project. Please describe your project, what it does, how it was used, etc.">
      <br />
      <textarea rows="15" cols="78" name="description"></textarea>
    </p>
  </fieldset>
    
  <br />
    
  <fieldset>
    <legend>Project Plans</legend>
    <p id="plansSection">
      Project Plans:
      <img border="0" src="<%=request.getContextPath()%>/images/help.png" Title="This area allows you to upload any plans you have for this project such as AutoCAD, PDFs, etc.">
      <br />
      <input type="file" class="multi" name="planUploads">
    </p>        
  </fieldset> 
    
  <br /> 
    
  <fieldset>
    <legend>Tutorial Videos</legend>
    <p>
      Tutorial Videos
      <img border="0" src="<%=request.getContextPath()%>/images/help.png" Title="This area allows you to upload or link any videos.">
      <br />
      <input type="file" class="multi" name="planUploads">
    </p>          
  </fieldset> 
          
  <br /> 
    
  <fieldset>
    <legend>Pictures</legend>
    <p>
      Pictures
      <img border="0" src="<%=request.getContextPath()%>/images/help.png" Title="This area allows you to upload or link any pictures.">
      <br />
      <input type="file" class="multi" name="planUploads">
    </p>              
  </fieldset> 
 <br><br>
  <input class="button" type="submit" name="submit" value="Submit Project" />
  <input class="button" type="submit" name="submit" value="Cancel" />
</form>