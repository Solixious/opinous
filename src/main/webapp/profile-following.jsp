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
    <title>Following</title>
	<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">
	    <jsp:include page="profile-nav-bar.jsp" />
        <div class="profile-details-title">
            Following
        </div>
        <div class="profile-details-basic follow-content">
        	<c:forEach items="${following}" var="user">
        		<a href="${contextPath}/profile/${user.username}" class="follow-link">
	        		<c:choose>
		        		<c:when test="${user.profilePicture != null}">
		        			<img src="${user.profilePicture}" class="follow-dp"/>
		        		</c:when>
		        		<c:otherwise>
		        			<img src="${contextPath}/resources/img/defaultdp.jpg" class="follow-dp"/>
		        		</c:otherwise>
	        		</c:choose>
	        		<span class="follow-username">${user.username}</span>
        		</a>
        	</c:forEach>
        </div>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>
