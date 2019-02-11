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
		<title>Moderator List</title>
		<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
		<link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
	 </head>
	<body>
		<jsp:include page="nav-bar.jsp" />
		<div class="container">
			<div class="table-div">
			    <table class="user-list-table">
			        <thead>
			            <tr>
			                <th>ID</th>
			                <th>Email</th>
			                <th>Username</th>
			                <th>First Name</th>
			                <th>Last Name</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${userList}" var="user">
			                <tr class="clickable-row" onclick="window.location='${contextPath}/admin/update/user/${user.username}';">
			                    <td>${user.id}</td>
			                    <td>${user.email}</td>
			                    <td>${user.username}</td>
			                    <td>${user.firstName}</td>
			                    <td>${user.lastName}</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	</body>
</html>