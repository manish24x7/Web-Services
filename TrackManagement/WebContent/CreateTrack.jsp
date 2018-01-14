<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	fieldset {
		margin-left: 30%;
    	margin-right: 30%;
	}
	th, tr {
		text-align: left;
    	padding: 15px;
	}
	.button {
		margin-left: 45%;
	    position: absolute;
	    top: 50%;
	}
	.button1 {
		margin-left: 50%;
	    position: absolute;
	    top: 50%;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Management</title>
</head>
<body>
	
	<div>
		<form method="post" action="TrackController?action=Save">
			<fieldset>
				<legend align="center"><h1>Track Details</h1></legend>
				<table align="center">
				<tr>
					<th></>Title  : </th>
					<th><input type="text" name="title" required="true"></th>
				</tr>
				<tr>
					<th>Singer : </th>
					<th><input type="text" name="singer" required="true"></th>
				</tr>
				<tr>
				<th>Database : </th>
				<th><select name="dbType">
				  <option value="table1">Table1</option>
				  <option value="table2">Table2</option>
				  <option value="table3">Table3</option>
				</select></th>
				<tr>
				</table>
				<br><br>
			</fieldset>
			<input class="button" type="submit" value="Submit">
			<input class="button1" type="reset" value="Reset">
	 	</form>
	</div>
</body>
</html>