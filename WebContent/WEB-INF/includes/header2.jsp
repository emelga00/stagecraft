<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
String homepage = (String)session.getAttribute("homepage");
String currentRole = (String)session.getAttribute("currentRole");
String currentUsername = (String)session.getAttribute("currentUsername");
if(homepage==""){
	homepage="Home";
}
%>
<div id="center">
    <div id="logo"><a href="<%=homepage%>">Global Performance Space</a></div>
    	<div id="head-nav-container">
        	<ul id="header-nav" class="clearfix">
				<li id="explore" class="header-nav"><a href="Explore">Explore</a></li>
				<li id="create" class="header-nav"><a href="Create">Create</a></li>
				<li id="videos" class="header-nav"><a href="#">Videos</a></li>
            	<li id="wiki" class="header-nav"><a href="#">WIKI</a></li>
            	
            	<% if(currentRole.equals("admin")||currentRole.equals("moderator")){
            	out.println("<li id='admin' class='header-nav'><a href='ViewUsers'>Admin</a></li>");
            	}
            		%>
            	
            	<% if(currentRole.equals("admin")||currentRole.equals("moderator")||currentRole.equals("user")){
            	out.println("<li id='admin' class='header-nav'><a href='UserProfile'>"+currentUsername+"</a></li>");
            	}            		%>	
            	<li id="login" class="header-nav"><a href="Logout">Logout</a></li>
			</ul>
		</div>
    <div class="search">
        <form class="search-form">
            <input type="search" name="search" placeholder="Search" autocomplete="off" tabindex="1">
        </form>
    </div>
</div>

