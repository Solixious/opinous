<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Home</title>
		<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="nav-bar.jsp" />
		<div class="container">
			<div class="rooms">
				<c:forEach items="${rooms}" var="room">
					<div class="room-card">
						<a href="${contextPath}/room/${room.id}">
							<span class="room-title"><c:out value="${room.title}"/></span>
							<span class="secondary-text"><img src="/resources/img/comment.png" class="comment-img"/><span class="comment-val">${room.postCount}</span></span>
							<span class="secondary-text"><img src="/resources/img/participant.png" class="participant-img"/><span class="participant-val">${room.participantCount}</span></span>
						</a>
					</div>
				</c:forEach>
				<div class="page-nav-div">
					<c:choose>
						<c:when test="${pageNumber > 0}">
							<a href="/home/page/${pageNumber}" class="page-nav">Previous</a>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${pageNumber < maxPageNumber - 1}">
							<a href="/home/page/${pageNumber + 2}" class="page-nav">Next</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	</body>
</html>
