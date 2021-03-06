<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><c:out value="${room.title}"/></title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">
			<div class="media">
				<div class="media-heading">
					<span class="title"><c:out value="${room.title}"/></span>
				</div>
				<div class="media-left">
					<img src="${room.creator.anonymousUser.displayPicture}" class="media-object">
					<span class="user-name"><c:out value="${room.creator.anonymousUser.name}"/></span>
				</div>
				<div class="media-body">	
					<div class="media-date">
						<span class="tooltip date-text">
							${room.createdTimeAgo}
							<span class="tooltiptext">
								<fmt:formatDate value="${room.createDate}" pattern="dd MMM yyyy, hh:mm aa" />
							</span>
						</span>
					</div>
					<p><c:out value="${room.description}"/></p>
				</div>
			</div>
			<div class="room-replies">
	          	<c:forEach items="${posts}" var="post">
				<div class="media">
						<div class="media-left">
							<img src="${post.alias.anonymousUser.displayPicture}" class="media-object">
							<span class="user-name"><c:out value="${post.alias.anonymousUser.name}"/></span>
						</div>
						<div class="media-body">
							<div class="media-date">
								<span class="tooltip date-text">
									${post.updatedTimeAgo}
									<span class="tooltiptext">
										<fmt:formatDate value="${post.createDate}" pattern="dd MMM yyyy, hh:mm aa" />
									</span>
								</span>
							</div>
							<div><c:out value="${post.text}"/></div>
							<div class="media-react">
								<span class="react-count">${post.reactionCounts['LIKE']}</span>
								<c:choose>
									<c:when test="${post.reactions['LIKE'] == 1}">
										<span class="react-icon liked unlike" data-id="${post.id}"></span>
									</c:when>
									<c:otherwise>
										<span class="react-icon react-icon-login-${isUser} like" data-id="${post.id}"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
                                    <c:when test="${post.reactions['DISLIKE'] == 1}">
                                        <span class="react-icon disliked undislike" data-id="${post.id}"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="react-icon react-icon-login-${isUser} dislike" data-id="${post.id}"></span>
                                    </c:otherwise>
                                </c:choose>
								<span class="react-count">${post.reactionCounts['DISLIKE']}</span>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
          
          <c:if test='${isUser}'>
          	<div class="media reply-media">
				<div class="media-left">
					<c:if test='${postingAs != null}'>
						<img src="${postingAs.anonymousUser.displayPicture}" class="media-object">
						<span class="user-name"><c:out value="${postingAs.anonymousUser.name}"/></span>
					</c:if>
				</div>
				<div class="media-body">
		          		<form:form method="POST" modelAttribute="postForm" class="form form-room-reply">
		       				<spring:bind path="text">
				                <form:textarea path="text" class="form-control"
				                placeholder="Post your reply here"></form:textarea>
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
<c:if test='${isUser}'>
	<script src="/resources/js/reactions.js"></script>
</c:if>
</body>
</html>
