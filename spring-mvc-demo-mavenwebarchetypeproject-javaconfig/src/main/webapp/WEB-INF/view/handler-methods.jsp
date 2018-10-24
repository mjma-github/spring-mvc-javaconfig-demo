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
		<h1>Spring MVC 3.1 Demo Endpoints</h1>
		<c:forEach items="${handlerMethods}" var="entry">
			<div>
				<hr>
				<p>
					<strong>${entry.value}</strong>
				</p>
			</div>
			<div class="span-3 colborder">
				<p>
					<span class="alt">Patterns:</span><br>
					<c:if test="${not empty entry.key.patternsCondition.patterns}">
            ${entry.key.patternsCondition.patterns}
          </c:if>
				</p>
			</div>
		</c:forEach>
	</div>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>