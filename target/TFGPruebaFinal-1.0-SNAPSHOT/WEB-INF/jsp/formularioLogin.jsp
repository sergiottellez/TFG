<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    
    >

<head>
	<title>SISTEDES Login</title>
	<link rel="stylesheet" type="text/css" href="login.css" />
	<link rel="stylesheet" href="bootstrap.min.css"/>
  	<script src="jquery.min.js"> </script>
        <script src="boostrap.min.js" > </script>

      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	
	
	<div class="container">
		<img src="logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
                <form:form action="/login" method="post" class="form-signin">
			<h3 class="form-signin-heading" th:text="Welcome"> Bienvenido</h3>
			<br/>
			 
			<form:input type="text" id="email" name="email" path="email" placeholder="Email"
				class="form-control" /> <br/> 
			<form:input type="password" path="password"  placeholder="Password"
				id="password" name="password" class="form-control" /> <br /> 
				
                        <div align="center" id="error" ng-if="{param.error}">
				<p style="font-size: 20; color: #FF1C19;">Email o password incorrectas. Vuelvalo a intentar</p>
			</div>
			<form:button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit" >Login</button>
		</form>
	</div>
</body>
</html>