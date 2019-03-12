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

        <div class="profile-picture">
            <c:choose>
                <c:when test="${userDetail.profilePicture}">
                    <img src="${userDetail.profilePicture}" class="profile-picture-img"/>
                </c:when>
                <c:otherwise>
                    <img src="${contextPath}/resources/img/defaultdp.jpg" class="profile-picture-img"/>
                </c:otherwise>
            </c:choose>
        </div>
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
        <div class="edit-profile-button"></div>
        <div class="left-nav">
            <span class="left-nav-menu"><a href="#">Basic</a></span>
            <span class="left-nav-menu"><a href="#">My Posts</a></span>
            <span class="left-nav-menu"><a href="#">My Threads</a></span>
            <span class="left-nav-menu"><a href="#">Followers</a></span>
            <span class="left-nav-menu"><a href="#">Following</a></span>
            <span class="left-nav-menu"><a href="#">Advanced Settings</a></span>
        </div>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>
