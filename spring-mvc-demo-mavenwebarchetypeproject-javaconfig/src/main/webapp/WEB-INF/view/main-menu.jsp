<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Demo</title>
</head>
<body>
	<img
		src="${pageContext.request.contextPath}/resources/images/spring.png"> 
	<h2>${welcomeMessage}</h2>
	<!-- Display user name and role -->
	User: <security:authentication property="principal.username" /> | 
	Roles: <security:authentication property="principal.authorities" />
	<br>
	Language : <a href="?lang=en">English</a> | <a href="?lang=ph">Filipino</a>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>	
	
	<h3>${requestMapping}</h3>
	<a href="${pageContext.request.contextPath}/helloWorld/showForm">${helloWorldForm}</a>
	<hr>
	
	<h3>${springMvcFormTagsDataBinding}</h3>
	<h4>${textFieldsModelAttribute}</h4>
	<a href="${pageContext.request.contextPath}/student/showForm">${studentForm}</a>
	<h4>${formValidation}</h4>
	<a href="${pageContext.request.contextPath}/customer/showForm">${customerForm}</a>
	<hr>
	
	<h3>${crudSample}</h3>
	<a href="${pageContext.request.contextPath}/student/list">${studentCrud}</a>
	
	<security:authorize access="hasRole('ADMIN')">  
		<!-- ADMIN only content -->
		<h3>${utilities}</h3>
		<a href="${pageContext.request.contextPath}/endpoints">${showAllRequestMappings}</a>
		<br>
		<a href="${pageContext.request.contextPath}/beans">${showAllBeans}</a>
		<br>
		<a href="${pageContext.request.contextPath}/testDbConnection">${testDbConnection}</a>
	</security:authorize>
</body>
</html>