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
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Update User Account</title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
</head>

<body>
<jsp:include page="nav-bar.jsp" />
<div class="container">
	<div class="form update-query-form">
		<h2 class="form-heading">Enter Username</h2>
		<div class="form-content">
			<input type="text" class="form-element form-input" id="usr">
			<button type="button" class="form-button-primary" id="load">Load</button>
		</div>
	</div>
    <c:if test="${not empty userForm.username}">
		<form:form method="POST" modelAttribute="userForm" class="form form-update-user">
			<h3 class="form-heading">User Details</h3>
			<div class="form-content">
				<spring:bind path="id">
					<form:hidden path="id"></form:hidden>
					<form:errors path="id"></form:errors>
				</spring:bind>
				<spring:bind path="username">
					<div class="form-element">Username</div>
					<form:input type="text" path="username" class="form-element form-input" placeholder="Username"
						autofocus="true"></form:input>
					<form:errors path="username"></form:errors>
	            </spring:bind>
				<spring:bind path="email">
					<div class="form-element">Email</div>
					<form:input type="text" path="email" class="form-element form-input" placeholder="Email Address"></form:input>
					<form:errors path="email"></form:errors>
				</spring:bind>
	            <spring:bind path="password">
					<div class="form-element">Password</div>
					<form:input type="password" path="password" class="form-element form-input" placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
	            </spring:bind>
	            <spring:bind path="confirmPassword">
					<div class="form-element">Confirm Password</div>
					<form:input type="password" path="confirmPassword" class="form-element form-input"
	                                placeholder="Confirm your password"></form:input>
					<form:errors path="confirmPassword"></form:errors>
	            </spring:bind>
	            <spring:bind path="firstName">
					<div class="form-element">First Name</div>
					<form:input type="text" path="firstName" class="form-element form-input"
	                                placeholder="First Name"></form:input>
					<form:errors path="confirmPassword"></form:errors>
	            </spring:bind>
	            <spring:bind path="lastName">
					<div class="form-element">Last Name</div>
					<form:input type="text" path="lastName" class="form-element form-input"
	                                placeholder="Last Name"></form:input>
					<form:errors path="lastName"></form:errors>
	            </spring:bind>
				<button class="form-button-primary" type="submit">Update</button>
				<button class="form-button-secondary" type="submit" onclick="document.forms['deleteForm'].submit()">Delete</button>
			</div>
		</form:form>
        <form:form method="DELETE" modelAttribute="userForm" class="update-form" id="deleteForm">
            <spring:bind path="id">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:hidden path="id"></form:hidden>
                    <form:errors path="id"></form:errors>
                </div>
            </spring:bind>
        </form:form>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
    jQuery("#load").click( function() {
        var usr = jQuery("#usr").val();
        window.location.replace("${contextPath}/admin/update/user/" + usr);
    });
    jQuery("#usr").keypress(function (e) {
      var key = e.which;
      if(key == 13)  // the enter key code
       {
            var usr = jQuery("#usr").val();
            window.location.replace("${contextPath}/admin/update/user/" + usr);
       }
   });
</script>
</body>
</html>
