<%-- 
    Document   : userNewForm
    Created on : 17-mar-2018, 15:51:18
    Author     : sergiottellez
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="es">
<head>
        <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">

 <title>NUEVO SOCIO</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/bdcde6b0/src/main/webapp/WEB-INF/jsp/confirmar.js"> </script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/3e610780/src/main/webapp/WEB-INF/jsp/bloquearUniversidad.js">
 bloquearUniversidad();
 </script>
 <script>
          
</script>
 
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/0073d1d2/src/main/webapp/WEB-INF/jsp/validarFormulario.js"> </script>
     
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #166088;
}

li {
    float: left;

}

li a {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}

.active {
    background-color: #62A9EC;
}
</style>
</head>
<body>
    
            <!--  <a id="logout" href="logout"   class="btn btn-default btn-sm"> <i class="glyphicon glyphicon-log-out"></i>Logout </a> > -->

              
 <ul>
  <li><a href="home" class="active">Home</a></li>
  <c:if test="${user.role == 1}"> <li><a href="verLista">Ver lista</a></li></c:if>
   <li><a href="editUser?id=${user.email}">Mi Perfil</a></li>
   <c:if test="${user.role == 1}"><li><a href="summary">Summary</a></li></c:if>
  <c:if test="${user.role == 1}"> <li><a href="newUser">Nuevo Usuario</a></li></c:if>
      <c:if test="${user.role == 1}"><li><a href="verBajas">Bajas</a></li></c:if>
  <li>  <a id="logout" href="logout">Logout</a></li>


</ul>   

    
    
<div class="container-fluid">
  <div class="row-fluid">
   <div class="col-md-6">
    <h4 class="text-center">Agregar Nuevo Socio</h4>
    <hr>
    <form:form action="saveContactNew" method="POST" modelAttribute="contacto" onsubmit="validarNIF()">
        
       
       <div class="form-group">
      <label for = "user_id"> ID: </label>
  <form:input path="user_id" class="form-control" required="true" title="Introduzca un ID con el siguiente patrón: SNxxxx o  SFxxxx en caso de fundador o EXT si es externo.."  /> <div>
        <button type="button" onclick="ayudaNewUsuario()" class="btn btn-info"><span  class="glyphicon glyphicon-question-sign"></span></button>

         
     </div>
   

   

        
        
        <div class="form-group">
      <label for = "dni"> DNI: </label>
  <form:input id="dni" path="dni" class="form-control" title="Campo obligatorio" required="true" onchange="validarNIF(this.value)" /> 
   

        
      <div class="form-group">
      <label for = "nombre"> Nombre: </label>
      <form:input path="nombre" class="form-control"/>
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
      <label for = "sexo">Sexo: </label>
      <form:select path="sexo" class="form-control"> V o M
          <form:option value="V"> V </form:option>
                    <form:option value="M"> M </form:option>

          
      </form:select>
     </div>
     
     <div class="form-group">
      <label for ="email">Email</label>
      <form:input type="email" path="email" class="form-control" required="true" />
     </div>
     <div class="form-group">
      <label for = "password">Password: </label>
      <form:input type="password" id="password" onclick="seleccionar()"  path="password" showPassword="false"  required="true"  class="form-control"/>

     </div>
     <div class="form-group">
      <label for = "role">Rol (admin o usuario normal): </label> 
      <form:select path="role" class="form-control"> 
          <form:option value="1"> Admin </form:option>
                    <form:option value="0"> Socio </form:option>

          
      </form:select>
     </div>
     
      <div class="form-group">
      <label for = "universidad">Universidad: </label>
      <form:select path="universidad" disabled="false" id="universidad" onchange="bloqueaUniversidad()">
           <form:option value=" " label="Seleccione" />
          <form:options items="${universidades}"   />
        </form:select>
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
      <form:select path="pais">
         <form:options items="${paises}" />
        </form:select>
     </div>
     
     <div class="form-group">
      <label for = "empresa">Pertenece a empresa: </label>
      <form:select path="empresa" class="form-control" id="empresa" onchange="bloqueaUniversidad()"> 
           <form:option value="0"> No </form:option>
          <form:option value="1"> Sí </form:option>
                   

          
      </form:select>
     </div>
     
           
            <div class="form-group">
                <label onchange="bloqueauniversidad()" id="labelEmpresa" for = "nombreEmpresa">Nombre de empresa: </label>
 <form:input  path="nombreEmpresa" class="form-control" id="nombreEmpresa" onchange="bloqueaUniversidad()"  /> 
           

          
    
     </div>
     
     <div class="form-group">
      <label for = "antiguedad">Año antigüedad: </label>
      <form:input title="Año de inclusión" path="antiguedad" class="form-control" pattern="[0-9]{4}"/>
     </div>
     
     <div class="form-group">
      <label for = "reciente">Reciente: </label>
      <form:input title="Último año de renovación" path="reciente" class="form-control" pattern="[0-9]{4}"/> Año última aparición
     </div>
     
     <div class="form-group">
      <label for = "activo">Socio activo: </label>
      
       <form:select path="activo" class="form-control"> 
          <form:option value="1"> Sí </form:option>
                    <form:option value="0"> No </form:option>

          
      </form:select>
     </div>
     
   
      <div class="form-group">
      <label for = "fundador">Fundador: </label>
      <form:select path="fundador" class="form-control"> 
          <form:option value="1"> Sí </form:option>
                    <form:option value="0"> No </form:option>
                    
                    
 

          
      </form:select>
           <div class="form-group">
      <label for = "cuota">Tipo de renovación: </label>
      <form:select path="cuota" class="form-control"> 
          <form:option value="1"> Pagado </form:option>
          <form:option value="0"> Asistencia a encuentro </form:option>
          
      </form:select>
     </div>
     
     <div class="form-group">
      <input type="submit" value="Guardar" class="btn btn-success" />
      <a href="home" class="btn btn-success"><i class="glyphicon glyphicon-remove-circle"></i>Cancelar</a>

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