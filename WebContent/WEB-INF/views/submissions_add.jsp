<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, data.*, beans.*" %>
<%
int projID = (int) request.getAttribute("projID");
Project project = ProjectsDB.getProjByPID(""+projID);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Categories</title>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var addDiv = $('#addInput');
	var i = $('#addinput p').size() + 1;

	$('#addNew').live('click', function() {
	$('<p><input type="text" id="p_new" size="40" name="newURL_'+i+'" value="" placeholder="Insert URL" /><select name="newURLType_' +i+'"><option value="plan" >Plan </option><option value="video">Video</option><option value="image">Image</option></select><input type="text" id="newUrlTag_'+i+'" size="20" name="p_new" value="" placeholder="tag: (\'set\',\'trailer\',\'costume\',...)" /><a href="#" id="remNew">Remove</a> </p>').appendTo(addDiv);
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
		$('<p><input type="file" id="p_newFile" name="file_'+i+'" /><select name="newFileTypes_'+i+'"><option value="plan" >Plan </option><option value="video">Video</option><option value="image">Image</option></select><input type="text" id="p_new" size="20" name="newFileTag_'+i+'" value="" placeholder="tag: (\'set\',\'trailer\',\'costume\',...)" /><a href="#" id="remNewFile">Remove</a></p>').appendTo(addDiv);
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
<div id="addInput">
<h3>Add URL(s)</h3>
<p>
<input type="text" id="p_new" size="20" name="p_new" value="" placeholder="URL" />
<select name="urlTypes">
	<option value="plan" >Plan </option>
	<option value="video">Video</option>
	<option value="image">Image</option>
</select>
<input type="text" id="urlTag" size="20" name="urlTag" value="" placeholder="Tag: ('set','trailer','costume','cast',...)"/>
<a href="#" id="addNew">Add another URL</a>
</p>
</div>
<div id="addFileInput">
<h3>Add File(s)</h3>
<p>
<input type="file" name="img">
<select name="fileTypes">
	<option value="plan" >Plan </option>
	<option value="video">Video</option>
	<option value="image">Image</option>
</select>
<input type="text" id="p_newFile" size="20" name="fileTag" value="" placeholder="Tag: ('set','trailer','costume','cast',)"/>
<a href="#" id="addNewFile">Add another file</a>
</p>
</div>
</body>
</html>