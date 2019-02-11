<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <title>Create New Room</title>
	<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">
        <form:form method="POST" modelAttribute="roomForm" class="form form-new-room">
            <h2 class="form-heading">Create New Room</h2>
            <div class="form-content">
	            <spring:bind path="title">
                    <form:input type="text" path="title" class="form-element form-input" placeholder="Title"
						autofocus="true"></form:input>
                    <form:errors path="title"></form:errors>
	            </spring:bind>
	
	            <spring:bind path="description">
					<form:textarea path="description" class="form-element form-textarea" placeholder="Description"
						rows="5"></form:textarea>
					<form:errors path="description"></form:errors>
	            </spring:bind>
	            <button class="form-button-primary" type="submit">Submit</button>
	        </div>
        </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>
