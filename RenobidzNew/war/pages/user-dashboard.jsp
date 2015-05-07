<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Renobidz Home</title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Renobidz</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>

				<!--logo-->
				<a class="navbar-brand" href="/">Renobidz</a>
			</div>

			<!-- Navigation bar -->
			<div class="navbar-collapse collapse in"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav lft_nav">
					<li class="active"><a href="#">Find Professional</a></li>
					<li><a href="#">Find Product</a></li>
					<li><a href="#">Advice</a></li>
				</ul>

				<!-- Right navigation links for support and log in -->
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${user == null}">
							<li id="signUp"><a href="#" class="signUpModalNavLink">Sign
									Up</a></li>
							<li id="logIn"><a href="#" class="loginModalNavLink">Log
									In</a></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${user != null}">
							<li class="dropdown">
							<li><img src="${user.profilePicture}" class="rounded" /></a></li>
							<li class="dropdown-toggle" data-toggle="dropdown"><a
								role="button" aria-expanded="false">Hello <span
									class="text-danger">${user.firstName}</span>&nbsp;<span
									class="caret"></span></a></li>
							<ul class="dropdown-menu" role="menu">
								<li class="divider"></li>
								<li><a href="/logout">Logout</a></li>
								<li class="divider"></li>
							</ul>
							</li>
						</c:when>
					</c:choose>
					<li><a href="#">Support</a></li>
				</ul>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
</body>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
<script type="text/javascript"
	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/additional-methods.min.js"></script>
<script src="../rb/scripts/signin.js"></script>
<script src="//apis.google.com/js/client.js?onload=init"></script>
</html>