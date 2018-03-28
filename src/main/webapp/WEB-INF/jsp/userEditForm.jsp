<%-- 
    Document   : userNewForm
    Created on : 17-mar-2018, 15:51:18
    Author     : sergiottellez
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
 <title>EDITAR SOCIO</title>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
  <div class="row-fluid">
   <div class="col-md-6">
    <h4 class="text-center">Editar Socio</h4>
    <hr>
    <form:form action="saveContact" method="POST" modelAttribute="contact">
        
        <form:hidden path="user_id"/>
      <div class="form-group">
      <label for = "name"> Nombre: </label>
      <form:input path="name" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "apellido1">Apellido 1: </label>
      <form:input path="apellido1" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "apellido2">Apellido 2: </label>
      <form:input path="apellido2" class="form-control"/>
     </div>
     <div class="form-group">
      <label for ="email">Email</label>
      <form:input path="email" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "password">Password: </label>
      <form:input  path="password" showPassword="false"  class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "role">Rol (admin o usuario normal): </label> 
      <form:input path="role" class="form-control"/> ADMIN = 1
     </div>
     
     <div class="form-group">
      <label for = "universidad">Universidad: </label>
      <form:input path="universidad" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "region">Región: </label>
      <form:input path="region" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "empresa">Pertenece a empresa: </label>
      <form:input path="empresa" class="form-control"/> SÍ = 1
     </div>
     
     <div class="form-group">
      <label for = "antiguedad">Año antigüedad: </label>
      <form:input path="antiguedad" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "reciente">Reciente: </label>
      <form:input path="reciente" class="form-control"/> Año última aparición
     </div>
     
     <div class="form-group">
      <label for = "activo">Socio activo: </label>
      <form:input path="activo" class="form-control"/> SÍ = 1
     </div>
     
     <div class="form-group">
      <input type="submit" value="Guardar" class="btn btn-success"/>
     </div>
    </form:form>
   </div>
  </div>
 </div>
    
    <footer>
        <div class='define'>
		<img src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/images/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
        </div>
    </footer>
</body>
</html>