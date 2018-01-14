<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,com.myproject.bean.Track" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Management</title>
</head>
<body>
	<center><p>View Track Details</p><br><br>
	
	<table border="1">
	<thead>
		<tr><th>Title</th><th>Singer</th></tr>
	</thead>
	<tbody>
		<% List<Track> trackList = (List<Track>) request.getAttribute("trackList"); 
		for(Track track : trackList){%>
			<tr><td><%=track.getTitle() %></td><td><%=track.getSinger() %></td></tr>
		<%} %>
	</tbody>		
	</table>
	
	
	<a href="index.jsp">Home</a></center>
</body>
</html>