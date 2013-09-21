<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,java.sql.*,data.*,beans.*" %>

<%
/****************
 *  Author: JLH
 ****************/
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");

String nextView =  (String) request.getAttribute("nextView");
String urlMapping = (String)session.getAttribute("urlMapping");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

   <head>
      <title>Enterprise Java - Group1 Project</title>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css"></link>
   </head>
   
   <body>
   
       <div id="container">
       
       
           <div id="clearfooter">
		       
		       
				<div id="header">
			    	<!-- Header  -->
					<%
						if(urlMapping.equals("/CAClientListView")||urlMapping.equals("/CAInfoListView")||
						urlMapping.equals("/CACredentialsListView")||urlMapping.equals("/ClientViewerList")||
						urlMapping.equals("/HrEmployeeListView")||urlMapping.equals("/HrSystemUserListView")){
					%>
						<jsp:include page="/WEB-INF/includes/header2.jsp" />
					<%
					} else {
					%>
						<jsp:include page="/WEB-INF/includes/header.jsp" />
					<%
					}
					%>
				</div><!-- END header-->
		       
		       
				<div id="content">
		            <!-- Page Content -->
		        	<jsp:include page="<%=nextView%>"  />
				</div><!-- END content-->
			       
			       
			</div><!-- END clearfooter-->
		       
		       
   			<div id="clearfooter2">
   			</div><!-- END clearfooter2-->
      
       
			<div id="footer">
		        	<!-- Footer -->
		        	<jsp:include page="/WEB-INF/includes/footer.jsp" />
			</div><!-- END footer-->


       </div><!-- END container-->
       
   </body>
   
</html>

