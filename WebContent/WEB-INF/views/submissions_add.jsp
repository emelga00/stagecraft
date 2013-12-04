<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, data.*, beans.*" %>
<%
	//Get project id from request scope
	int projID = (int) request.getAttribute("projID");

	//use the projID variable to find the project from the database
	Project project = ProjectsDB.getProjByPID(""+projID);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Categories</title>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
	function alertFileName(fileNumber)
	{
		var thefile = document.getElementById('file'+fileNumber);
		alert(thefile.value);
	}
</script>
<script type="text/javascript">
$(function() {
	var addDiv = $('#addInput');
	var i = $('#addinput p').size() + 1;

	$('#addNew').live('click', function() {
	$('<p><input type="text" id="URL'+i+'" size="40" name="URL'+i+'" value="" placeholder="Insert URL" /><select name="urlType'+i+'"><option value="plan" >Plan </option><option value="video">Video</option><option value="image">Image</option></select><input type="text" id="urlTag'+i+'" size="20" name="urlTag'+i+'" value="" placeholder="tag: (\'set\',\'trailer\',\'costume\',...)" /><a href="#" id="remNew">Remove</a><input type="hidden" name="URL_Length" value="'+i+1+'"></p>').appendTo(addDiv);
	i++;

	return false;
	});

	$('#remNew').live('click', function() {
	if( i > 2 ) {
	$(this).parents('p').remove();
	i--;
	}
	return false;
	});
	});

</script>
<script type="text/javascript">
$(function() {
	var addDiv = $('#addFileInput');
	var i = $('#addFileInput p').size() + 1;

	$('#addNewFile').live('click', function() {
		$('<p><input type="file" id="file'+i+'" name="file'+i+' onchange="alertFileName('+i+')"" /><select name="fileType'+i+'"><option value="plan" >Plan </option><option value="video">Video</option><option value="image">Image</option></select><input type="text" id="fileTag'+i+'" size="20" name="fileTag'+i+'" value="" placeholder="tag: (\'set\',\'trailer\',\'costume\',...)" /><a href="#" id="remNewFile">Remove</a><input type="hidden" name="File_Length" value="'+i+1+'"><input type="hidden" name="fileName0" value="alertFileName('+i+')"></p>').appendTo(addDiv);
		i++;
		
		return false;
	});

	$('#remNewFile').live('click', function() {
		if( i > 2 ) {
		$(this).parents('p').remove();
		i--;
		}
		return false;
		});
		});

	
	</script>
</head>
<body>
<h2><%=project.getName()%></h2>
<form name="Submission_Add" class="submission" action="Create_Submission" method="POST">
<div id="addInput">
<h3>Add URL(s)</h3>
<p>
<input type="text" id="URL0" size="30" name="URL0" value="" placeholder="Insert URL" />
<select name="urlTypes0" id="url0">
	<option value="plan" >Plan </option>
	<option value="video">Video</option>
	<option value="image">Image</option>
</select>
<input type="text" id="urlTag0" size="15" name="urlTag0" value="" placeholder="Tag: ('set','trailer','costume','cast',...)"/>
<input type="hidden" name="URL_Length" value="1">
<a href="#" id="addNew">Add another URL</a>
</p>
</div>
<div id="addFileInput">
<h3>Add File(s)</h3>
<p>
<input type="file" name="file0" id="file0" onchange="alertFileName(0)">
<select name="fileTypes0">
	<option value="plan" >Plan </option>
	<option value="video">Video</option>
	<option value="image">Image</option>
</select>
<input type="text" id="fileTag0" size="15" name="fileTag0" value="" placeholder="Tag: ('set','trailer','costume','cast',...)"/>
<a href="#" id="addNewFile">Add another file</a>
<input type="hidden" name="fileName0" value="alertFileName(0)">
<input type="hidden" name="File_Length" value="1">
</p>
</div>
</form>
</body>
</html>