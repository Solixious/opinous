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

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

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

        <jsp:include page="nav-bar.jsp" />

        <div class="container" style="margin-top:80px">
          <div class="media">
            <div class="media-left">
              <img src="${room.creator.anonymousUser.displayPicture}" class="media-object" style="width:100px">
              <span class="user-name">${room.creator.anonymousUser.name}</span>
            </div>
            <div class="media-body">
              <h4 class="media-heading">${room.title}</h4>
              <p>${room.description}</p>
            </div>
          </div>
          
          <div >
          	<c:forEach items="${posts}" var="post">
          		<div class="media">
		            <div class="media-left">
		              <img src="${post.anonMap.anonymousUser.displayPicture}" class="media-object" style="width:100px">
		              <span class="user-name">${post.anonMap.anonymousUser.name}</span>
		            </div>
		            <div class="media-body">
		              <p>${post.text}</p>
		            </div>
		        </div>
          	</c:forEach>
          </div>
          
          <c:if test='${isUser == true }'>
          	<div class="post-reply">
          		<form:form method="POST" modelAttribute="postForm" class="form-signin">
          			<spring:bind path="text">
			            <div class="form-group ${status.error ? 'has-error' : ''}">
			                <form:textarea path="text" class="form-control" placeholder="Post your reply here"
			                            autofocus="true"></form:textarea>
			                <form:errors path="text"></form:errors>
			                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			            </div>
			        </spring:bind>
          		</form:form>
          	</div>
          </c:if>
        </div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
