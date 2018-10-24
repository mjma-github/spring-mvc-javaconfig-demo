<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Confirmation</title>
</head>
<body>
	<h2>Customer Confirmation</h2>
	First Name: ${customer1.firstName}
	<br><br>
	Last Name: ${customer1.lastName}
	<br><br>
	Free passes: ${customer1.freePasses}
	<br><br>
	Email address: ${customer1.emailAddress}
	<br><br>
	Birth date: ${customer1.birthDate}
	<br><br>
	Postal code: ${customer1.postalCode}
	<br><br>
	Course code: ${customer1.courseCode}
	
	<br><br>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>