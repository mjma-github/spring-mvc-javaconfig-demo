<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Student CRUD</h2>
		</div>
	</div>

	<div id="container">
		<h3>Add Student</h3>
	
		<form:form action="addOrUpdate" modelAttribute="student" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td>
							<form:input path="firstName"/>
							<form:errors path="firstName" cssClass="error" />
						</td>
					</tr>
				
					<tr>
						<td><label>Last name:</label></td>
						<td>
							<form:input path="lastName"/>
							<form:errors path="lastName" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td><label>Date of Birth:</label></td>
						<td>
							<form:input type="date" path="dateofBirth"/>
							<form:errors path="dateofBirth" cssClass="error" />
						</td>
					</tr>
					

					<tr>
						<td><label>Email:</label></td>
						<td>
							<form:input path="email"/>
							<form:errors path="email" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










