<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>
  	<h1>Error Page</h1>
  	<p>Application has encountered an error. Please contact support on ...
  	<br>
  	Support may ask you to right click this page to view page source.
  	</p>
  	<br>
  	<a href="${pageContext.request.contextPath}">Home</a>
  	
	<!--
	  Failed URL: ${url}
	  Exception:  ${exception.message}
	  <c:forEach items="${exception.stackTrace}" var="ste"> 
	  	${ste} 
	  </c:forEach>
	-->
</body>
</html>