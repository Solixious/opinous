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
	    <title>Create An Admin Account</title>
	    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	    <link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="nav-bar.jsp" />
		<div class="container">
		    <form:form method="POST" modelAttribute="userForm" class="form form-new-anon" enctype="multipart/form-data">
		        <h2 class="form-heading">Create anonymous account</h2>
		        <div class="form-content">
			        <spring:bind path="name">
			        	<form:input type="text" path="name" class="form-input form-element" placeholder="Anonymous Name"
			            	autofocus="true"></form:input>
			            <form:errors path="name"></form:errors>
			        </spring:bind>
			        <input type="file" name="file" id="dpInput" class="form-element">
			        <button class="form-button-primary" type="submit">Submit</button>
		        </div>
		    </form:form>
			<img id="preview" src="#" alt="No Image Preview" class="anon-dp-preview">
			        
		</div>
		<!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		
		<script>
		    function readURL(input) {
		
		          if (input.files && input.files[0]) {
		            var reader = new FileReader();
		
		            reader.onload = function(e) {
		              $('#preview').css('display','block');
		              $('#preview').attr('src', e.target.result);
		            }
		
		            reader.readAsDataURL(input.files[0]);
		          }
		    }
		
		    $("#dpInput").change(function() {
		      readURL(this);
		    });
		
		</script>
	
	</body>
</html>