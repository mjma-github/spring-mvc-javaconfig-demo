<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Demo</title>
</head>
<body>
	<div class="container">
		<h1>Spring Beans</h1>
		<c:forEach items="${beansMap}" var="entry">
			<div>
				<hr>
				<p>
					<strong>${entry.key}</strong> : ${entry.value} 
				</p>
			</div>
		</c:forEach>
	</div>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>