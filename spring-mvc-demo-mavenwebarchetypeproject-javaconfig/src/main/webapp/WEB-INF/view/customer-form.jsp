<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration Form</title>
<style>
.error {
	color: red
}
</style>
</head>
<body>
	<i>Fill out the form. Asterisk (*) means required.</i>
	<br>
	<form:form action="processForm" modelAttribute="customer1" method="POST">
		First Name: <form:input path="firstName" />
		<br><br>
		
		Last Name*: <form:input path="lastName" />
		<form:errors path="lastName" cssClass="error" />
		<br><br>
		
		Email address:
		<form:input path="emailAddress" />
		<!-- <form:input type="email" path="emailAddress" /> -->
		<form:errors path="emailAddress" cssClass="error" />
		<br><br>
		
		Birth date*:
		<form:input type="date" path="birthDate" />
		<form:errors path="birthDate" cssClass="error" />
		<br><br>
		
		Postal code:
		<form:input path="postalCode" />
		<form:errors path="postalCode" cssClass="error" />
		<br><br>
		
		<h4>Custom annotation validation</h4>
		Free passes*:
		<!-- let spring handle type mismatch error, just for testing -->
		<!-- <form:input type="number" path="freePasses" /> -->
		<form:input path="freePasses" />
		<form:errors path="freePasses" cssClass="error" />
		<br><br>
		
		Course code:
		<form:input path="courseCode" />
		<form:errors path="courseCode" cssClass="error" />
		<br><br>
		
		<code>*Note: Customer level validation checks allowable free passes based on age.</code>
		<br><br>
		
		<!-- display all errors -->
		<!-- <form:errors path="" cssClass="error" /> -->
		
		<!-- display object errors only  -->
		<form:errors cssClass="error" />
		
		<br><br>
		<input type="submit" value="Submit" />
	</form:form>
	<br>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>