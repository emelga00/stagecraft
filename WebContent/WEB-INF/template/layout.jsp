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

<!DOCTYPE HTML>
<html>
   <head>
      <title>World of Stagecraft</title>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css"></link>
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/reset.css" type="text/css"></link>
	  <script src="http://code.jquery.com/jquery-latest.js"></script>
   </head>
   
   <body>
   <div id="wrapper">
        <div id="header">
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
        <div class="clear"></div>
        <div id="section">
            <div id="page">
                <!-- Page Content -->
                <jsp:include page="/WEB-INF/includes/loginbox.jsp" />
                <jsp:include page="<%=nextView%>"  />
            </div><!-- END content-->
        </div>
		<div class="clear"></div>	       
        <div id="footer">
			<jsp:include page="/WEB-INF/includes/footer.jsp" />
		</div><!-- END footer--> 
   </div><!-- END wrapper--> 
   </body>
</html>

