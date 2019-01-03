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

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<jsp:include page="nav-bar.jsp" />
<div class="container" style="margin-top:50px">
    <div class="form-group update-query-form">
      <label for="usr">Username</label>
      <input type="text" class="" id="usr">
      <button type="button" class="btn btn-primary" id="load">Load</button>
    </div>
    <c:if test="${not empty userForm.username}">
        <form:form method="POST" modelAttribute="userForm" class="update-form">
            <spring:bind path="id">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:hidden path="id"></form:hidden>
                    <form:errors path="id"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Username<form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Email<form:input type="text" path="email" class="form-control" placeholder="Email Address"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Password<form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="confirmPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Confirm Password<form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="firstName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    First Name<form:input type="text" path="firstName" class="form-control"
                                placeholder="First Name"></form:input>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Last Name<form:input type="text" path="lastName" class="form-control"
                                placeholder="Last Name"></form:input>
                    <form:errors path="lastName"></form:errors>
                </div>
            </spring:bind>
            <button class="btn btn-primary" type="submit">Update</button>
            <button class="btn btn-secondary">Delete</button>
        </form:form>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    jQuery("#load").click( function() {
        var usr = jQuery("#usr").val();
        window.location.replace("${contextPath}/admin/update-delete-user/" + usr);
    });
</script>
</body>
</html>