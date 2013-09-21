<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
/***************
 *  Author: JLH
 ***************/
%>

<div id="headcontent">

	<div id="topcontent">
		<%
			String loggedIn;
			String currentUsername = (String)session.getAttribute("currentUsername");
			if(currentUsername==null){
			   loggedIn="<span class=\"topline\"></span>";
			}else{
			   loggedIn="<span class=\"topline\" id=\"forPass\">  Logged in as: <B> " + currentUsername + "</b> -- <a href=\"Logout\" >logout</a></span>";
			
			}
		%>
	</div><!-- end topcontent -->
	
	<div class="header">
	
		<%
			if(session.getAttribute("homepage")==null){
				session.setAttribute("homepage", "Login");
			}
		%>
		<div id="logoHolder">
			STAGECRAFT
		</div>
		<nav>
		<menu>
		<li><a href="#">Home</a></li>
		<li><a href="#">Projects</a></li>
		<li><a href="#">Blog</a></li>
		<li><a href="#">WIKI</a></li>
		<li><a href="#">About</a></li>
		<li><a href="#">Contact</a></li>
		</menu>
		</nav>
		
	</div ><!-- end header -->
	
</div><!-- END headcontent -->



