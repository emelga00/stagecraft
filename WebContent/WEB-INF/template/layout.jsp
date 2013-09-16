<%@ page contentType="text/html; charset=iso-8859-1" language="java"
import="java.util.*,java.sql.*,data.*,beans.*" %>
<%
   //*** Prevent Browser Caching of Pages *****
   response.setHeader("Pragma", "No-cache");
   response.setDateHeader("Expires", 0);
   response.setHeader("Cache-Control", "no-cache");

   String content =  (String) request.getAttribute("content");
  
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
   
      <title>MVC Students</title>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css"></link>
   </head>
   <body>
       <header>
          <jsp:include page="/WEB-INF/template/header.jsp" />
       </header>
       <section>
	      <jsp:include page="<%=content%>"  />
	   </section>
	   <footer>
	      <jsp:include page="/WEB-INF/template/footer.jsp" />
	   </footer>	       	       
  </body>
</html>

