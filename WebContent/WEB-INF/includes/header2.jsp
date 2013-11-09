<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
String homepage = (String)session.getAttribute("homepage");
String currentRole = (String)session.getAttribute("currentRole");
String currentUsername = (String)session.getAttribute("currentUsername");
if(homepage==""){
	homepage="Home";
}
%>
<div id="nav">
	<a href="Home"><img class="logo" src="<%=request.getContextPath()%>/images/logo.png" /></a>
	<ul>
		<li><a class="active" href="Explore">Explore</a></li>
		<li><a href="Create">Create</a></li>
		<li><a href="#">Videos</a></li>
        <li><a href="#">WIKI</a></li>
        	<ul>
        		<% if(currentRole.equals("admin")||currentRole.equals("moderator")||currentRole.equals("user")){
            	out.println("<li><a href='UserProfile'>"+currentUsername+"</a></li>");
            	}%>		
                <% if(currentRole.equals("admin")||currentRole.equals("moderator")){
            	out.println("<li class='last'><a href='ViewUsers'>Tools</a></li>");
            	}%>
            	<li><a href="Logout">Logout</a></li>
            </ul>
	</ul>
</div>    
   
