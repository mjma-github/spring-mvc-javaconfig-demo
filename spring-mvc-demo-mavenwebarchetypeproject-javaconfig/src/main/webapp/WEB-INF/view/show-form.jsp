<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Demo</title>
</head>
<body>
	<h2>Spring MVC Demo - Hello World Form</h2>
	<h3>Using Spring EL to retrieve parameter</h3>
	<form action="processForm" method="POST">
		<input type="text" name="studentName" />
		<input type="submit" value="Submit Query" />
	</form>
	<br>
	<br>
	<h3>Using HttpServletRequest and Model to retrieve parameter</h3>
	<form action="processForm2" method="POST">
		<input type="text" name="studentName" />
		<input type="submit" value="Submit Query" />
	</form>
	<br>
	<br>
	<h3>Using @RequestParam and Model to retrieve parameter</h3>
	<form action="processForm3" method="POST">
		<input type="text" name="studentName"/>
		<input type="submit" value="Submit Query" />
	</form>
	
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>