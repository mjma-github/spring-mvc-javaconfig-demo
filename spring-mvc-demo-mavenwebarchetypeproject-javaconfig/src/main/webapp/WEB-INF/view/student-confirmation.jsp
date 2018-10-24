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
	<h2>Student Confirmation</h2>
	Student Name: ${student1.firstName} ${student1.lastName}
	<br> Country: ${student1.countryDescription}
	<br> Favorite language: ${student1.favoriteLanguage}
	<br> Operating systems:
	<br>
	<ul>
		<c:forEach var="temp" items="${student1.operatingSystems}">
			<li>${temp}</li>
		</c:forEach>
	</ul>
	<br>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>