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
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="nav-bar.jsp" />
<div class="container">
    <div class="form update-query-form">
      <h3 class="form-heading">Anonymous Name</h3>
      <div class="form-content">
      	<input type="text" class="form-input form-element" id="usr">
      	<button type="button" class="form-button-primary" id="load">Load</button>
      </div>
    </div>
    <c:if test="${not empty userForm.name}">
        <form:form method="POST" modelAttribute="userForm" class="form form-update-anonymous" enctype="multipart/form-data">
            <h3 class="form-heading">User Details</h3>
            <div class="form-content">
	            <spring:bind path="id">
	                    <form:hidden path="id"></form:hidden>
	                    <form:errors path="id"></form:errors>
	            </spring:bind>
	            <spring:bind path="name">
	                    <form:input type="text" path="name" class="form-input form-element" placeholder="Anonymous Name"
	                               autofocus="true"></form:input>
	                    <form:errors path="name"></form:errors>
	            </spring:bind>
	            <img src="${userForm.displayPicture}" class="anon-dp" id="preview1"/>
	            <input type="file" name="file" id="dpInput">
	            <button class="form-button-primary" type="submit">Update</button>
	        </div>
        </form:form>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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

   function readURL(input) {

         if (input.files && input.files[0]) {
           var reader = new FileReader();

           reader.onload = function(e) {
             $('#preview1').attr('src', e.target.result);
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