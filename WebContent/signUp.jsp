<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/00030c648e.js" crossorigin="anonymous"></script>
</head>
<body  style="text-align:center" class="bg-light">
<!-- page where a user can register. -->
	<c:choose>
	    <c:when test="${not empty userEmail}">
	    	<br>
	    	<h4 style="color:red;">User logged In already.</h4>
	    	<h4 style="color:red;">
	    		go to : 
	    		<a href="homePage.jsp">home page</a>
	    	</h4>
	    </c:when>    
	    <c:otherwise>
	        <h1 class="mt-3">Sign Up <i class="fas fa-user-plus"></i></h1>
			<br>
			<br>
			<form action="signUp" method="post">
				<strong>Enter Email : </strong>
				<input type="text" class="form-control mt-3" name="email" placeholder="user@example.com" size=25 />
				<br>
				<br>
				<strong>Enter password : </strong>
				<br>
				<input type="password" class="form-control mt-3" name="password" placeholder="Password"/>
				<br>
				<br>
				<strong>Confirm password : </strong>
				<br>
				<input type="password" class="form-control mt-3" name="confirmPassword" placeholder="Password"/>
				<br>
				<p style="color:red;">${emailError}</p>
				<p style="color:red;">${passwordError}</p>
				<c:if test="${not empty alreadyRegistered}">
					<p style="color:red;">
						User registered already,please 
						<a href="logIn.jsp">log in</a>
					</p>
				</c:if>
				<input type="submit" name="signedUp" class="btn btn-outline-dark" value="Sign up"/>
			</form>
	    </c:otherwise>
	</c:choose>
</body>
</html>