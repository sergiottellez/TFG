<%-- 
    Document   : userNewForm
    Created on : 17-mar-2018, 15:51:18
    Author     : sergiottellez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
        <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">

 <title>EDITAR SOCIO</title>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/cca09cdb/src/main/webapp/WEB-INF/jsp/seleccionar.js"></script>
</head>
<body>
    
              <a id="logout" href="/TFGPruebaFinal/logout"   class="btn btn-default btn-sm"> <i class="glyphicon glyphicon-log-out"></i>Logout </a>

<div class="container-fluid">
  <div class="row-fluid">
   <div class="col-md-6">
    <h4 class="text-center">Editar Socio</h4>
    <hr>
    <form:form action="saveContactEdit" method="POST"  modelAttribute="contacto">
        
        <div class="form-group">
      <label for = "user_id"> ID: </label>
  <form:input path="user_id" class="form-control" readonly="true" /> 
   

        </div>
        
        <div class="form-group">
      <label for = "dni"> DNI: </label>
  <form:input path="dni" class="form-control" readonly="true" /> 
   

        </div>
        
        
      <div class="form-group">
      <label for = "nombre"> Nombre: </label>
      <form:input path="nombre" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "apellido1">1er Apellido: </label>
      <form:input path="apellido1" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "apellido2">2º Apellido: </label>
      <form:input path="apellido2" class="form-control"/>
     </div>
       
     <div class="form-group">
      <label for = "sexo">Sexo: </label>
      <form:select id="sexo" path="sexo" class="form-control" required="required" >
            <form:option value="V">V</form:option>
            <form:option value="M">M</form:option>
            
     </form:select>

      
     </div>
     
     <div class="form-group">
      <label for ="email">Email</label>
      <form:input path="email" class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "password">Password: </label>
      <form:input type="password" id="password" onclick="seleccionar()"  path="password" showPassword="false"   class="form-control"/>
     </div>
     <div class="form-group">
      <label for = "role">Rol (admin o usuario normal): </label> 
            <c:if test="${sessionScope.user.role == 1}" ><form:select path="role" class="form-control"> 
          <form:option value="1"> Admin </form:option>
                    <form:option value="0"> Socio </form:option>

          
      </form:select></c:if>

            <c:if test="${sessionScope.user.role==0}"><form:input path="role" class="form-control" readonly="true"/> ADMIN = 1</c:if>
     </div>
     
     <div class="form-group">
      <label for = "universidad">Universidad: </label>
      <form:input path="universidad" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "region">Región: </label>
        <form:select path="region">
         <form:options items="${region}" />
        </form:select>
     </div>
     
     <div class="form-group">
      <label for = "dblppersonname">Dblppersonname: </label>
      <form:input path="dblppersonname" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "authorkey">Author key: </label>
      <form:input path="authorkey" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "pais">País: </label>
      <form:input path="pais" class="form-control"/>
     </div>
     
     <div class="form-group">
      <label for = "grupoInvestigacion">Grupo: </label>
      <form:input path="grupoInvestigacion" class="form-control"/>
     </div>
     
     
     <div class="form-group">
      <label for = "empresa">Pertenece a empresa: </label>
     <form:select path="empresa" class="form-control"> 
          <form:option value="1"> Sí </form:option>
                    <form:option value="0"> No </form:option>

          
      </form:select>
     </div>
     
     <div class="form-group">
      <label for = "antiguedad">Año antigüedad: </label>
      <form:input path="antiguedad" class="form-control" readonly="true"/>
     </div>
     
     <div class="form-group">
      <label for = "reciente">Reciente: </label>
      <c:if test="${sessionScope.user.role == 1}" ><form:input path="reciente" class="form-control"/> </c:if>Año última aparición 
      <c:if test="${sessionScope.user.role == 0}" ><form:input path="reciente" class="form-control" readonly="true"/> </c:if>

     </div>
     
     <div class="form-group">
      <label for = "activo">Socio activo: </label>
 <c:if test="${sessionScope.user.role == 1}" >      <form:select path="activo" class="form-control"> 
          <form:option value="1"> Sí </form:option>
                    <form:option value="0"> No </form:option>

          
      </form:select></c:if>
      <c:if test="${sessionScope.user.role == 0}" ><form:input path="activo" class="form-control" readonly="true"/>SÍ=1</c:if>
     </div>
     
     <div class="form-group">
      <label for = "fundador">Fundador: </label>
      <c:if test="${sessionScope.user.role == 1}" ><form:select path="fundador" class="form-control"> 
          <form:option value="1"> Sí </form:option>
                    <form:option value="0"> No </form:option>

          
      </form:select></c:if>
      <c:if test="${sessionScope.user.role == 0}" ><form:input path="fundador" class="form-control" readonly="true"/>SÍ=1</c:if>
 
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