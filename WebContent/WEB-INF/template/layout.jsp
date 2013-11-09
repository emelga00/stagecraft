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

String currentUsername = (String)session.getAttribute("currentUsername");
%>

<!DOCTYPE HTML>
<html>
   <head>
     	<title>Global Performance Space</title>
     	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/nav.css" type="text/css"></link>
      	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css"></link>
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/reset.css" type="text/css"></link>
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/forms.css" type="text/css"></link>
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/tables.css" type="text/css"></link>
	  	<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
			<script>
        		tinymce.init({selector:'textarea'});
			</script>
		<script type="text/javascript">
		$(document).on("scroll",function(){
			if($(document).scrollTop()>100){ 
				$("header").removeClass("large").addClass("small");
				}
			else{
				$("header").removeClass("small").addClass("large");
				}
			});
		</script>
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
        <header>
			<%
				if(currentUsername!=null){
			%>
			<jsp:include page="/WEB-INF/includes/header2.jsp" />
				<%
				}else {
				%>
			<jsp:include page="/WEB-INF/includes/header.jsp" />
				<%
				}
				%>
		</header>
<div class="clear"></div>
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

