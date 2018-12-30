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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home Page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="#">Opinous</a>
            </div>
            <ul class="nav navbar-nav">
              <li class="active"><a href="#">Popular</a></li>
              <li><a href="#">Recent</a></li>
              <li><a href="#">Notification</a></li>
              
              <sec:authorize access="hasRole('ADMIN_ROLE')">
			  	<li><a href="#">Admin CP</a></li>
			  </sec:authorize>
			  <sec:authorize access="hasRole('MODERATOR_ROLE')">
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

        <div class="container">
          <h3>Test title</h3>
          <p>This is a test paragraph. Proper content will be generated here.</p>
        </div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>