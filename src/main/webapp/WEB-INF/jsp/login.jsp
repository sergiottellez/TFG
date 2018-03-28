<%-- 
    Document   : login
    Created on : 20-mar-2018, 17:06:00
    Author     : sergiottellez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    
    >

<head>
	<title>SISTEDES Login</title>
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/login.css" />
	<link rel="stylesheet" href="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/bootstrap.min.css"/>
  	<script src="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/jquery.min.js"> </script>
        <script src="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/bootstrap.min.js" > </script>

      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	
	
	<div class="container">
		<img src="https://www.sistedes.es/wp-content/themes/sistedes/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
		 <form name="loginForm" action='login' method="post" class="form-signin">
			<h3 class="form-signin-heading" th:text="Welcome"> Bienvenido</h3>
			<br/>
			 
			<input type="text" id="email" name="email"  placeholder="Email"
				class="form-control" /> <br/> 
			<input type="password"  placeholder="Password"
				id="password" name="password" class="form-control" /> <br /> 
				
                      
			<button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit" >Login</button>
		</form>
	</div>
</body>
</html>