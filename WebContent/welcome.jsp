<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/00030c648e.js" crossorigin="anonymous"></script>
</head>
<body style="text-align:center" class="bg-light">
<!-- this is first page. -->
	<br>
	<h1> Welcome</h1>
	<h1> To </h1>
	<h1> To-Do-List <i class="fas fa-list"></i></h1>
	<h6 class="card-subtitle mb-2 text-muted"> Please Login or Register :</h6>
	<div>
		<!-- a login button to log in into the system -->
		<form action="logIn.jsp" method="post">
			<input style="border:2px solid black;" type="submit" class="btn btn-outline-dark px-3 mt-4" value="Log in" />
		</form>
		
		<br>
		<!-- a sign up button to register into the system -->
		<form action="signUp.jsp" method="post">
			<input style="border:2px solid black;" class="btn btn-outline-dark px-2" type="submit" value="Register"/>
		</form>
	</div>
	<br>
	<br>
	<!-- Information about our project -->
	<h3> About us <i class="fas fa-address-card"></i></h3>
	<hr style="width:100%" >
	<p> Team Collaboration (Shivam, Harman, Sukhraj and Gursharan) </p>
	<p> Front End : HTML and CSS </p>
	<p> Back End : JSP and Servlet </p>
	<p> Database : MySQL </p>
	<p> Server : Apache Tomcat </p>
	<form action="Contactus.html" method="post">
			<input style="border:2px solid black;" class="btn btn-outline-dark px-2" type="submit" value="Contact Us"/>
		</form>
</body>
</html>