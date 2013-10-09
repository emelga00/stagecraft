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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>Global Performance Space</title>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css"></link>
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/reset.css" type="text/css"></link>
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/reg-log.css" type="text/css"></link>
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/tables.css" type="text/css"></link>
	  <script src="http://code.jquery.com/jquery-latest.js"></script>
	  <style type="text/css">
	  body { 
  				background: url(images/red.jpg) no-repeat center center fixed; 
  				-webkit-background-size: cover;
  				-moz-background-size: cover;
  				-o-background-size: cover;
  				background-size: cover;
}
	  </style>
	  <%
	    //add the javascript references for the multifile upload on the add project page
      if (urlMapping.equals("/Project_Add"))
      {
    %>
        <script src="<%=request.getContextPath()%>/javascript/jquery.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/javascript/jquery.form.js" type="text/javascript" language="javascript"></script>
        <script src="<%=request.getContextPath()%>/javascript/jquery.MetaData.js" type="text/javascript" language="javascript"></script>
        <script src="<%=request.getContextPath()%>/javascript/jquery.MultiFile.js" type="text/javascript" language="javascript"></script>
        <script src="<%=request.getContextPath()%>/javascript/jquery.blockUI.js" type="text/javascript" language="javascript"></script>
    <%
      }
	  %>
   </head>
   
   <body>
   <div id="wrapper">
        <div id="header">
			<%
				if(urlMapping.equals("/AdminView")||urlMapping.equals("/CAInfoListView")||
				urlMapping.equals("/StandardUserView")||urlMapping.equals("/ClientViewerList")||
				urlMapping.equals("/Home")||urlMapping.equals("/HrSystemUserListView")){
			%>
			<jsp:include page="/WEB-INF/includes/header2.jsp" />
				<%
				}else {
				%>
			<jsp:include page="/WEB-INF/includes/header.jsp" />
				<%
				}
				%>
		</div><!-- END header-->
<!--         <div class="clear"></div> -->
        <div id="section">
            <div id="page">
                <!-- Page Content -->
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

