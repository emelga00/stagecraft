<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
/***************
 *  Author: JLH
 ***************/
%>

<div id="headcontent" style="height:110px">

	<div id="topcontent">
		<%
			String loggedIn;
			String currentUsername = (String)session.getAttribute("currentUsername");
			if(currentUsername==null){
			   loggedIn="<span class=\"topline\">Welcome!</span>";
			}else{
			   loggedIn="<span class=\"topline\" id=\"forPass\">  Logged in as: <B> " + currentUsername + "</b> -- <a href=\"Logout\" >logout</a></span>";
			
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
		   out.println(loggedIn + "<span class=\"datetime\">" + date + "</span>");
		%>
		<br />
		<a href="https://www.twitter.com/" target="Twitter"><img id="linkicons" src="<%=request.getContextPath()%>/images/twittericon.png" ></a>
		<a href="https://www.facebook.com/pages/TheatreUA-Fort-Smith/130272423677588" target="UAFS Theatre Facebook"><img id="linkicons" src="<%=request.getContextPath()%>/images/facebookicon.png" ></a>
		
	</div><!-- end topcontent -->
	
	<div class="header">
	
		<%
			if(session.getAttribute("homepage")==null){
				session.setAttribute("homepage", "Login");
			}
		%>
		<div id="logoHolder">
			<a href="<%=session.getAttribute("homepage")%>"><img id="logo" src="<%=request.getContextPath()%>/images/header.png" ></a><img id="logo" src="<%=request.getContextPath()%>/images/subheader.png" >
			
		</div>
		
	</div ><!-- end header -->
	
</div><!-- END headcontent -->



