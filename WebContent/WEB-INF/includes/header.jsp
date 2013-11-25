<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*,data.*"%>
    <% String gotoPage = "Create";
         if(session.getAttribute("currentUserID") == null)
         {gotoPage = "Login";} %>
<div id="nav">
	<a href="Home"><img class="logo" src="<%=request.getContextPath()%>/images/logo.png" /></a>
	<ul>
		<li><a class="active" href="Explore">Explore</a></li>
		<li><a href="<%=gotoPage %>">Create</a></li>
        <li><a href="http://70.178.114.2:5555/mediawiki" target="_blank">WIKI</a></li>
        <li><a href="Login">Login</a></li>
	</ul>
</div>     