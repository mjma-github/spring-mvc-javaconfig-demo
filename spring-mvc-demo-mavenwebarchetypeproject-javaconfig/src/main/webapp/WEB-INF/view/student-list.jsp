<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student List</h2>
		</div>
	</div>
		
	<div id="container">
		<div id="content">
			<!-- Add Customer -->
			<input type="button" value="Add Customer"
				   onclick="window.location.href='add'; return false;"
				   class="add-button"
			/>
			
			<!--  search box -->
			<form:form action="searchByFirstOrLastName" method="POST">
				Search customer: <input type="text" name="searchName" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Birth Date</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print students -->
				<c:forEach var="student" items="${students}">
				
					<!-- edit link with student id -->
					<c:url var="editLink" value="/student/edit">
						<c:param name="studentId" value="${student.id}" />
					</c:url>
					
					<!-- delete link with student id -->
					<c:url var="deleteLink" value="/student/delete">
						<c:param name="studentId" value="${student.id}" />
					</c:url>						
											
									
					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<!-- <td>${student.dateofBirth}</td> -->
						<td><fmt:formatDate value="${student.dateofBirth}" type="date" pattern="MM/dd/yyyy HH:mm:ss"/></td>
						<td>${student.email}</td>
						<!-- display the edit link -->
						<td>
							<a href="${editLink}">Edit</a> |
							<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div>
		<p>${message}</p>
	</div>
	<br>
	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>