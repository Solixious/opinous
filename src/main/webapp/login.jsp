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
		<title>Log in</title>
		<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<form method="POST" action="${contextPath}/login" class="form form-login">
				<h2 class="form-heading">Log in</h2>
				<div class="form-content ${error != null ? 'has-error' : ''}">
					<span>${message}</span>
					<input name="username" type="text" class="form-element form-input" placeholder="Username"
						autofocus="true"/>
					<input name="password" type="password" class="form-element form-input" placeholder="Password"/>
					<span>${error}</span>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<button class="form-button-primary" type="submit">Log In</button>
					<div class="form-extra-links">
						<a href="#">Forgot Password</a>
						<a href="${contextPath}/registration" class="float-right">Create an account</a>
					</div>
				</div>
			</form>
		</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	</body>
</html>