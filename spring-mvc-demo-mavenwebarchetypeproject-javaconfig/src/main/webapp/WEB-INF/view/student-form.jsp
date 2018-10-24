<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration Form</title>
</head>
<body>
	<!-- Note: path uses model attribute class' field getter and setter methods -->
	<form:form action="processForm" modelAttribute="student1" method="POST">
		First name: <form:input path="firstName" />
		<br><br>
		Last name: <form:input path="lastName" />
		<br><br>
		Country: <form:select path="country">
			<!-- Dynamic options coming from model attribute w/ default value -->
			<form:options items="${countryOptions}" />
			
			<!--  Hard coded in jsp -->
			<!--
			<form:option value="PHI" label="Philippines" />
			<form:option value="JPN" label="Japan" />
			<form:option value="USA" label="United States" />
			-->			
		</form:select>
		<br><br>
		Favorite language:
		<br>
		<form:radiobuttons path="favoriteLanguage" items="${favoriteLanguageOptions}" />
		<br><br>
		<!--  Hard coded in jsp -->
		<!--
		<form:radiobutton path="favoriteLanguage" value="Java" /> Java
		<form:radiobutton path="favoriteLanguage" value="C#" /> C#
		<form:radiobutton path="favoriteLanguage" value="PHP" /> PHP
		<form:radiobutton path="favoriteLanguage" value="Ruby" /> Ruby
		-->
		<br>
		Operating Systems:
		<br>
		<form:checkboxes items="${osOptions}" path="operatingSystems"/>
		<br>
		<!--  Hard coded in jsp -->
		<!-- 
		Linux <form:checkbox path="operatingSystems" value="OS1" />
		Windows <form:checkbox path="operatingSystems" value="OS2" />
		Mac <form:checkbox path="operatingSystems" value="OS3" />
		-->
		<br>
		<input type="submit" value="Submit" />
	</form:form>
	<br>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>

<!-- Note: if there are form fields with same path, the resulting model value for the field will be comma-separated list -->