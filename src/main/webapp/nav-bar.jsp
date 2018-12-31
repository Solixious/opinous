<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Opinous</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Popular</a></li>
      <li><a href="#">Recent</a></li>
      <li><a href="#">Notification</a></li>
      <sec:authorize access="hasAuthority('ADMIN_ROLE')">
        <li><a href="#">Admin CP</a></li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('MODERATOR_ROLE')">
        <li><a href="#">Moderator CP</a></li>
      </sec:authorize>

    </ul>
    <button class="btn btn-primary navbar-btn">New</button>
    <ul class="nav navbar-nav navbar-right">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Welcome ${pageContext.request.userPrincipal.name}</a></li>
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