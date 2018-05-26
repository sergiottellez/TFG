<%-- 
    Document   : cambiarPass
    Created on : 01-may-2018, 18:48:21
    Author     : sergiottellez
--%>

<%-- 
    Document   : recuperarPass
    Created on : 01-may-2018, 17:35:38
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
        <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">

	<title>SISTEDES</title>
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/login.css" />
	<link rel="stylesheet" href="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/bootstrap.min.css"/>
  	<script src="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/jquery.min.js"> </script>
        <script src="https://cdn.rawgit.com/sergiottellez/TFG/b4668f82/src/main/webapp/WEB-INF/jsp/bootstrap.min.js" > </script>

      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	
	
	<div class="container">
		<img src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/images/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
		 <form name="cambiarPassFinal" action='cambiarPassFinal' method="post" class="form-signin">
			<h3 class="form-signin-heading" th:text="Welcome"> Recuperar contraseña</h3>
			<br/>
			
                        <input type="text" id="dni" name="dni"  placeholder="DNI"
				class="form-control" required="true" /> <br/> 
                        
			<input type="password" id="password" name="password"  placeholder="Nueva pass"
				class="form-control" required="true"/> <br/> 
			 <br /> 
                      
			<button class="btn btn-lg btn-primary btn-block" name="Submit" value="Aceptar" type="Submit" >Aceptar</button>
		</form>
	</div>
</body>
</html>
