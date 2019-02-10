<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Register Account</title>
		<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
		<link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="nav-bar.jsp" />
		<div class="container">
			<form:form method="POST" modelAttribute="userForm" class="form form-register form-register-admin">
				<h2 class="form-heading">Register User Account</h2>
				<div class="form-content">
					<spring:bind path="username">
							<form:input type="text" path="username" class="form-element form-input" placeholder="Username"
								autofocus="true"></form:input>
							<form:errors path="username"></form:errors>
					</spring:bind>
					<spring:bind path="email">
							<form:input type="text" path="email" class="form-element form-input" placeholder="Email Address"></form:input>
							<form:errors path="email"></form:errors>
					</spring:bind>
					<spring:bind path="password">
							<form:input type="password" path="password" class="form-element form-input" placeholder="Password"></form:input>
							<form:errors path="password"></form:errors>
					</spring:bind>
					<spring:bind path="confirmPassword">
							<form:input type="password" path="confirmPassword" class="form-element form-input"
								placeholder="Confirm your password"></form:input>
							<form:errors path="confirmPassword"></form:errors>
					</spring:bind>
					<button class="form-button-primary" type="submit">Submit</button>
				</div>
			</form:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	</body>
</html>