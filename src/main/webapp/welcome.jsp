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
							<span class="room-description secondary-text"><span class="description-content">${room.description}</span></span>
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
		<script>
			var animeFlag = false;
			var e1, e2;
			var up = "up", down = "down";
			$(".room-title").bind('mouseover', function() {
				var currHeight = $(this).height();
				$(this).css("max-height", "80%");
				$(this).css('height','auto');
				var autoHeight = $(this).height();
				$(this).css('height', currHeight);
				$(this).animate({
				    height: autoHeight,
				    duration: 500,
				    queue: false
				});
				
				var descHeight = $(this).next(".room-description").height() - (autoHeight - currHeight);
				$(this).next(".room-description").animate({
					height: descHeight,
					duration: 500,
					queue: false
				});
			});
			$(".room-title").bind('mouseleave', function() {
				var currHeight = $(this).height();
				$(this).css("max-height", "30%");
				$(this).css('height','auto');
				var autoHeight = $(this).height();
				$(this).css("max-height", "80%");
				$(this).css('height', currHeight);
				
				$(this).next(".room-description").animate({
					height: "+55%",
					duration: 500,
					queue: false
				});
				if(autoHeight < currHeight) {
					$(this).animate({
					    height: "+30%",
						duration: 500,
						queue: false
					});
				}
			});
			$(".room-description").bind('mouseover', function() {
				var titleHeight = ($(this).prev(".room-title").height()/$(this).prev(".room-title").parent().height())*100;
				if($(this).height() < $(this).find('.description-content').height() && titleHeight <= 30) {
					animateContent($(this), $(this).find('.description-content'), down);
				} else if($(this).height() < $(this).find('.description-content').height() && titleHeight >= 30) {
					animeFlag = true;
					e1 = $(this);
					e2 = $(this).find('.description-content');
				}
			});
			$(".room-description").bind('mouseleave', function() {
				animeFlag = false;
				if($(this).height() < $(this).find('.description-content').height()) {
					animateContent($(this), $(this).find('.description-content'), up);
				}
				
				$(this).prev("room-title").animate({
				    height: "+30%",
					duration: 500,
					queue: false
				});
			});
			function animateContent(ele, ele2, direction) {
			    var animationOffset = ele.height() - ele2.height();
			    var speed = "slow";
			    if (direction == up) {
			        animationOffset = 0;
			        speed = "fast";
			    }
			    ele2.animate({
			        "marginTop": animationOffset + "px"
			    }, speed);
			}
		</script>
	</body>
</html>
