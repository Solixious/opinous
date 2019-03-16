<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="left-nav">
    <div class="profile-picture">
        <c:choose>
            <c:when test="${userDetail.profilePicture != null}">
                <img src="${userDetail.profilePicture}" class="profile-picture-img"/>
            </c:when>
            <c:otherwise>
                <img src="${contextPath}/resources/img/defaultdp.jpg" class="profile-picture-img"/>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${!isUserProfile}">
        <button class="nav-button-primary follow-button">Follow</button>
    </c:if>
    <span class="left-nav-menu"><a href="${contextPath}/profile/basic">Basic</a></span>
    <c:if test="${isUserProfile}">
        <span class="left-nav-menu"><a href="${contextPath}/profile/my-posts">My Posts</a></span>
        <span class="left-nav-menu"><a href="${contextPath}/profile/my-rooms">My Rooms</a></span>
    </c:if>
    <span class="left-nav-menu"><a href="#">Followers</a></span>
    <span class="left-nav-menu"><a href="#">Following</a></span>
    <c:if test="${isUserProfile}">
        <span class="left-nav-menu"><a href="#">Advanced Settings</a></span>
    </c:if>
</div>
