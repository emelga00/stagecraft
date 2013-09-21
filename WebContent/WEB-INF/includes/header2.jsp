<%@ page import="java.util.Date, java.text.*,beans.*" %>

<%
/***************
 *  Author: JLH
 ***************/
%>

<div id="headcontent" >

	<div id="topcontent">
		<%
			String urlMapping = (String)session.getAttribute("urlMapping");
			urlMapping = urlMapping.substring(1);
			
			String loggedIn;
			String currentUsername = (String) session.getAttribute("currentUsername");
			if(currentUsername==null){
			   loggedIn="<span class=\"topline\">  You are not logged in</span>";
			}else{
			   loggedIn="<span class=\"topline\">  Logged in as: <B> " + currentUsername + "</b> -- <a href=\"Logout\">logout</a></span>";
			
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			out.println(loggedIn + "<span class=\"datetime\">" + date + "</span>");
		%>
	</div>
	
	<div class="header">
		<%
		if(session.getAttribute("homepage")==null){
			session.setAttribute("homepage", "Login");
		}
		%>
		<div id="logoHolder">
			<a href="<%=session.getAttribute("homepage")%>"><img id="logo" src="<%=request.getContextPath()%>/styles/header.png" ></a>
		</div>

		<div id="alphalist">

				<table id=letterNav>
					<tr>
					<td class="tdclass"><a href="<%=urlMapping%>?" class="alpha2">ListAll</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=A" class="alpha2">A</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=B" class="alpha2">B</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=C" class="alpha2">C</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=D" class="alpha2">D</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=E" class="alpha2">E</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=F" class="alpha2">F</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=G" class="alpha2">G</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=H" class="alpha2">H</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=I" class="alpha2">I</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=J" class="alpha2">J</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=K" class="alpha2">K</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=L" class="alpha2">L</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=M" class="alpha2">M</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=N" class="alpha">N</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=O" class="alpha">O</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=P" class="alpha">P</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=Q" class="alpha">Q</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=R" class="alpha">R</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=S" class="alpha">S</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=T" class="alpha">T</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=U" class="alpha">U</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=V" class="alpha">V</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=W" class="alpha">W</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=X" class="alpha">X</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=Y" class="alpha">Y</a></td>
					<td class="tdclass"><a href="<%=urlMapping%>?letter=Z" class="alpha">Z</a></td>
					</tr>
				</table>

		</div><!-- END alphalist -->
	</div ><!-- END header -->
</div><!-- END headcontent -->
