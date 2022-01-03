  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome <%= request.getAttribute("userName") %> </title>
</head>
<body>
<h1> Welcome <%= request.getAttribute("userName") %>
</h1> 

</body>
</html>