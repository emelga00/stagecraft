<%@ page import="java.util.Date, java.text.*,beans.*" %>
   <div id="header-bar">
<%
   User currentUser = (User) session.getAttribute("currentUser");
   String loggedIn;
   if(currentUser==null){
      loggedIn="<span class=\"topline\">User is not logged in</span>";
   }else{
      loggedIn="<span class=\"topline\">Username:<B> " + currentUser.getUserName() + 
    		  "</b>  <a href=\"logout\">Logout</a>";
   }
   out.println(loggedIn + "</span>"); 
%>
   </div>
   <div id="header-banner">
      <img src="<%=request.getContextPath()%>/images/uafs_logo_med.png" />
   </div>







