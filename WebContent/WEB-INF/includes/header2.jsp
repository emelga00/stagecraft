<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
String homepage = (String)session.getAttribute("homepage");
String currentRole = (String)session.getAttribute("currentRole");
String currentUsername = (String)session.getAttribute("currentUsername");
if(homepage==""){
	homepage="Home";
}
String gotoPage = "Create";
if(session.getAttribute("currentUserID") == null)
{gotoPage = "Login";} %>

<div id="nav">
	<a href="Home"><img class="logo" src="<%=request.getContextPath()%>/images/logo.png" /></a>
	<ul>
		<li><a class="active" href="Explore">Explore</a></li>
		<li><a href="<%=gotoPage %>">Create</a></li>
        <li><a href="http://70.178.114.2:5555/mediawiki" target="_blank">WIKI</a></li>
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
   
