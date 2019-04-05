<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${rooms}" var="room">
	<div class="room-card">
		<a href="${contextPath}/room/${room.id}">
			<span class="room-title"><c:out value="${room.title}"/></span>
			<span class="room-description secondary-text"><span class="description-content">${room.description}</span></span>
			<span class="secondary-text time-ago">${room.updatedTimeAgo}</span>
			<span class="room-stat">
			<span class="secondary-text">
			<img src="/resources/img/comment.png" class="comment-img"/>
			<span class="comment-val">${room.postCount}</span>
			</span>
			<span class="secondary-text">
			<img src="/resources/img/participant.png" class="participant-img"/>
			<span class="participant-val">${room.participantCount}</span>
			</span>
			</span>
		</a>
	</div>
</c:forEach>