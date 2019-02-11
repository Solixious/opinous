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
    <title>Anonymous User List</title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
</head>

<body>
<jsp:include page="nav-bar.jsp" />
<div class="container">
    <c:if test="${userList == null}">
        Getting null
    </c:if>
    <div class="anon-table-div">
    <table class="table anon-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Anonymous Name</th>
                <th>Display Picture</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user">
                <tr class="clickable-row" onclick="window.location='${contextPath}/admin/update/anon/${user.name}';">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td><img src="${user.displayPicture}" class="anon-list-dp"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>