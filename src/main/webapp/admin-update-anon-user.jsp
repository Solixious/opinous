<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Anonymous User Update</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<jsp:include page="nav-bar.jsp" />
<div class="container" style="margin-top:50px">
    <div class="form-group update-query-form">
      <label for="usr">Anonymous Name</label>
      <input type="text" class="" id="usr">
      <button type="button" class="btn btn-primary" id="load">Load</button>
    </div>
    <c:if test="${not empty userForm.name}">
        <form:form method="POST" modelAttribute="userForm" class="form-signin" enctype="multipart/form-data">
            <spring:bind path="id">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:hidden path="id"></form:hidden>
                    <form:errors path="id"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" placeholder="Anonymous Name"
                                autofocus="true"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>
            Current <img src="${userForm.displayPicture}" class="anon-list-dp"/>
            <input type="file" name="file">

            <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
        </form:form>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    jQuery("#load").click( function() {
        var usr = jQuery("#usr").val();
        window.location.replace("${contextPath}/admin/update/user/" + usr);
    });
    jQuery("#usr").keypress(function (e) {
      var key = e.which;
      if(key == 13)  // the enter key code
       {
            var usr = jQuery("#usr").val();
            window.location.replace("${contextPath}/admin/update/anon/" + usr);
       }
   });
</script>
</body>
</html>