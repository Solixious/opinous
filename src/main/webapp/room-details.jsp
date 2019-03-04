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
    <title>${room.title}</title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">
			<div class="media">
				<div class="media-left">
					<img src="${room.creator.anonymousUser.displayPicture}" class="media-object">
					<span class="user-name">${room.creator.anonymousUser.name}</span>
				</div>
				<div class="media-body">
					<h4 class="media-heading">${room.title}</h4>
					<p>${room.description}</p>
				</div>
			</div>
			<div class="room-replies">
	          	<c:forEach items="${posts}" var="post">
					<div class="media">
						<div class="media-left">
							<img src="${post.anonMap.anonymousUser.displayPicture}" class="media-object">
							<span class="user-name">${post.anonMap.anonymousUser.name}</span>
						</div>
						<div class="media-body">
							<p>${post.text}</p>
						</div>
					</div>
				</c:forEach>
			</div>
          
          <c:if test='${isUser == true }'>
          	<div class="media reply-media">
				<div class="media-left">
					<c:if test='${postingAs != null}'>
						<img src="${postingAs.anonymousUser.displayPicture}" class="media-object">
						<span class="user-name">${postingAs.anonymousUser.name}</span>
					</c:if>
				</div>
				<div class="media-body">
		          		<form:form method="POST" modelAttribute="postForm" class="form form-room-reply">
		       				<spring:bind path="text">
				                <form:textarea path="text" class="form-control" placeholder="Post your reply here"
				                            autofocus="true"></form:textarea>
				                <form:errors path="text"></form:errors>
				                <button class="form-button-primary" type="submit">Submit</button>
					        </spring:bind>
		          		</form:form>
				</div>
			</div>
          	
          </c:if>
        </div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>
