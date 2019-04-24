<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Opinous</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="${isPopularNav}"><a href="/"><img class="nav-img popular-img"/>Popular</a></li>
      <li class="${isRecentNav}"><a href="/recent"><img class="nav-img recent-img"/>Recent</a></li>
      <li><a href="#"><img class="nav-img notification-img"/>Notification</a></li>
      <sec:authorize access="hasAuthority('ADMIN_ROLE')">
        <li class="${isAdminNav}"><a href="${contextPath}/admin/">Admin CP</a></li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('MODERATOR_ROLE')">
        <li><a href="#">Moderator CP</a></li>
      </sec:authorize>
    </ul>
    <button class="nav-button-primary" onclick="window.location='${contextPath}/room/new'">New</button>
    <ul class="nav navbar-nav float-right">
    <li>
        <form id="searchForm" method="GET" action="${contextPath}/search">
            <input type="text" id="search" name="q" placeholder="Search"/>
            <img class="search-img"/>
        </form>
    </li>
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <li><a href="${contextPath}/profile/${pageContext.request.userPrincipal.name}"><span class="glyphicon glyphicon-user"></span>Welcome <c:out value="${pageContext.request.userPrincipal.name}"/></a></li>
            <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${contextPath}/registration"><span class="glyphicon glyphicon-register"></span> Register</a></li>
            <li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </c:otherwise>
    </c:choose>
    </ul>
  </div>
</nav>
<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<c:if test="${not empty notif}">
    <div class="alert alert-${notifType} fade in" id="notification">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${notif}
    </div>
</c:if>
