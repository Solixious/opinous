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

    <title>Admin CP</title>

    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <jsp:include page="nav-bar.jsp" />
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <div class="container" style="margin-top:50px">
        <div class="row widgets">
          <div class="text-center col-md-3">
              <a href="${contextPath}/admin/new/user"><span class="glyphicon glyphicon-plus"></span> Add User</a>
          </div>
          <div class="text-center col-md-3">
              <a href="${contextPath}/admin/update/user"><span class="glyphicon glyphicon-pencil"></span> Update/Delete User</a>
          </div>
          <div class="text-center col-md-3">
              <a href="${contextPath}/admin/list/users"><span class="glyphicon glyphicon-list"></span> List Users</a>
          </div>
        </div>
        <div class="row widgets">
          <div class="text-center col-md-3">
              <a href="${contextPath}/admin/new/anon"><span class="glyphicon glyphicon-plus"></span> Add Anonymous User</a>
          </div>
          <div class="text-center col-md-3">
              <a href="${contextPath}/admin/update/anon"><span class="glyphicon glyphicon-pencil"></span> Update/Delete Anonymous User</a>
          </div>
          <div class="text-center col-md-3">
             <a href="${contextPath}/admin/list/anon"><span class="glyphicon glyphicon-list"></span> List Anonymous Users</a>
          </div>
        </div>
        <div class="row widgets">
          <div class="text-center col-md-4">
              <a href="${contextPath}/admin/new/moderator"><span class="glyphicon glyphicon-plus"></span> Add Moderator</a>
          </div>
          <div class="text-center col-md-4">
              <a href="${contextPath}/admin/list/moderators"><span class="glyphicon glyphicon-list"></span> List Moderators</a>
          </div>
        </div>
        <div class="row widgets">
          <div class="text-center col-md-4">
              <a href="${contextPath}/admin/new/admin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a>
          </div>
          <div class="text-center col-md-4">
              <a href="${contextPath}/admin/list/admins"><span class="glyphicon glyphicon-list"></span> List Admins</a>
          </div>
        </div>
        <div class="row widgets">
          <div class="text-center col-md-3">
              <a href="#"><span class="glyphicon glyphicon-stats"></span> Statistics</a>
          </div>
          <div class="text-center col-md-3">
              <a href="#"><span class="glyphicon glyphicon-envelope"></span> Mails</a>
          </div>
          <div class="text-center col-md-3">
              <a href="#"><span class="glyphicon glyphicon-cog"></span> Site Settings</a>
          </div>
        </div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>