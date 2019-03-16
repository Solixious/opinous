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
    <title>User Profile</title>
	<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">

	    <jsp:include page="profile-nav-bar.jsp" />
        <div class="profile-details-title">
            Basic Info
        </div>
        <div class="profile-details-basic">
            <span class="profile-detail profile-detail-type">User Name</span>
            <span class="profile-detail profile-detail-value"><c:out value="${userDetail.username}"/></span>
            <span class="profile-detail profile-detail-type">First Name</span>
            <span class="profile-detail profile-detail-value"><c:out value="${userDetail.firstName}"/></span>
            <span class="profile-detail profile-detail-type">Last Name</span>
            <span class="profile-detail profile-detail-value"><c:out value="${userDetail.lastName}"/></span>
            <span class="profile-detail profile-detail-type">Email</span>
            <span class="profile-detail profile-detail-value"><c:out value="${userDetail.email}"/></span>
        </div>
        <c:if test="${isUserProfile}">
            <div class="edit-profile-button"><button class="form-button-primary">Edit</button></div>
            <form:form method="POST" modelAttribute="userDetail" class="form profile-details-basic-update hide" enctype="multipart/form-data">
                <h3 class="form-heading">Edit Basic Info</h3>
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
                    <input type="file" name="file" id="dpInput">
                    <button class="form-button-primary" type="submit">Update</button>
                    <img src="" class="anon-dp" id="preview"/>
                </div>
            </form:form>
        </c:if>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	$(document).on('click', '.edit-profile-button', function() {
		$('.edit-profile-button').addClass('hide');
		$('.profile-details-title').addClass('hide');
		$('.profile-details-basic').addClass('hide');
		$('.profile-details-basic-update').removeClass('hide');
	});
	$(document).on('click', '.profile-details-basic-update-cancel', function() {
		$('.profile-details-basic-update').addClass('hide');
		$('.edit-profile-button').removeClass('hide');
		$('.profile-details-title').removeClass('hide');
		$('.profile-details-basic').removeClass('hide');
	});
	function readURL(input) {
         if (input.files && input.files[0]) {
           var reader = new FileReader();
           reader.onload = function(e) {
             $('#preview').attr('src', e.target.result);
           }
           reader.readAsDataURL(input.files[0]);
         }
    }
    $("#dpInput").change(function() {
     readURL(this);
    });
</script>
</body>
</html>
