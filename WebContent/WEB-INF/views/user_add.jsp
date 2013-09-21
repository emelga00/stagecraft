<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new Client</title>
</head>

<%
/***************
 *  Author: JLH
 ***************/

	
	
	String status = (String)session.getAttribute("status");
	session.removeAttribute("status");
	
	//**** Get the data list placed in Request or Session Scope *******
	
	//reset status incase user cancels out of form
    session.setAttribute("status", "Add client cancelled...");
%>



<body>

<h2 class="centerIt">Register</h2>

<div id="inputForm">
	<form action="User_PostAdd" method="post">
		<table class="formTable">
			<colgroup>
				<col class="formLabelBox">
				<col>
				<col class="formLabelBox">
			</colgroup>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr style="border:none:">
				<td class="rightIt" style="border:none:">First Name</td><td><input type="text" name="firstName" maxlength="30" /></td><td></td>
			</tr>
			<tr style="border:none:">
				<td class="rightIt" style="border:none:">Last Name</td><td><input type="text" name="lastName" maxlength="30" /></td><td></td>
			</tr>
			<tr>
				<td class="rightIt">Phone Number</td><td><input type="text" name="phone" maxlength="10" /></td><td class="formInst">ex. 5558675309</td>
			</tr>
			<tr>
				<td class="rightIt">Street Address</td><td><input type="text" name="address1" maxlength="40" /></td><td class="formInst">Physical Address Only</td>
			</tr>
			<tr>
				<td class="rightIt">Street Address 2</td><td><input type="text" name="address2" maxlength="10" /></td><td class="formInst">ex. Apt 19, #19</td>
			</tr>
			<tr>
				<td class="rightIt">City</td><td><input type="text" name="city" maxlength="20" /></td><td class="formInst">Serving Fort Smith &amp; Van Buren</td>
			</tr>
			<tr>
				<td class="rightIt">State</td><td><input type="text" name="state" maxlength="2" value="AR"/></td><td class="formInst"></td>
			</tr>
			<tr>
				<td class="rightIt">Zip Code</td><td><input type="text" name="zip" maxlength="5" /></td><td class="formInst">ex. 90210</td>
			</tr>
		</table>
		<div class="formControls">
			<input class="jBtn" type="reset" name="rReset" value="Reset" /> &nbsp; 
			<input class="jBtn" type="submit" name="submit" value="Add client" />		
		</div>
	</form>
</div>

</body>
</html>