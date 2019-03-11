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
    <title>${room.title}</title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<div class="container">
			<div class="media">
				<div class="media-heading">
					<span class="title">${room.title}</span>
				</div>
				<div class="media-left">
					<img src="${room.creator.anonymousUser.displayPicture}" class="media-object">
					<span class="user-name">${room.creator.anonymousUser.name}</span>
				</div>
				<div class="media-body">	
					<div class="media-date">
						<fmt:formatDate value="${room.createDate}" pattern="dd MMM yyyy, hh:mm aa" />
					</div>
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
							<div class="media-date">
								<fmt:formatDate value="${post.createDate}" pattern="dd MMM yyyy, hh:mm aa" />
							</div>
							<div class="media-react">
								<span class="react-count">${post.reactionCounts['LIKE']}</span>
								<c:choose>
									<c:when test="${post.reactions['LIKE'] == 1}">
										<span class="react-icon liked" data-id="${post.id}"></span>
									</c:when>
									<c:otherwise>
										<span class="react-icon like" data-id="${post.id}"></span>
									</c:otherwise>
								</c:choose>
								<c:choose>
                                    <c:when test="${post.reactions['DISLIKE'] == 1}">
                                        <span class="react-icon disliked" data-id="${post.id}"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="react-icon dislike" data-id="${post.id}"></span>
                                    </c:otherwise>
                                </c:choose>
								<span class="react-count">${post.reactionCounts['DISLIKE']}</span>
							</div>
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
<script>
    $('span.like').click(function() {
        var id = $(this).data('id');
        var likes = parseInt($(this).prev('.react-count').text());
        likes = likes + 1;
        $(this).prev('.react-count').html(likes);
    	$(this).removeClass('like').addClass('liked');
        $.ajax({
            type: 'POST',
            url: '/post/like/' + id
        });
    });
    $('span.dislike').click(function() {
        var id = $(this).data('id');
        var dislikes = parseInt($(this).next('.react-count').text());
        dislikes = dislikes + 1;
        $(this).next('.react-count').html(dislikes);
        $(this).attr({style: "content:url('/resources/img/td_w.png')"});
        $.ajax({
            type: 'POST',
            url: '/post/dislike/' + id
        });
    });
</script>
</body>
</html>
