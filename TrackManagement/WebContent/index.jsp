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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Management</title>
</head>
<body>
	<fieldset>
		<legend  align="center"><h1>Select an operation</h1></legend>
		<center><a href="CreateTrack.jsp">Insert track</a><br><br>
		<a href="FindTrack.jsp">Search Track</a><br><br>
		<a href="TrackController?action=View">View all Tracks</a></center><br><br>
	</fieldset>
</body>
</html>