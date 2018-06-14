<%-- 
    Document   : verBajas
    Created on : 25-abr-2018, 17:27:56
    Author     : sergiottellez
--%>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%@page contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">




<html lang="es">
<head>
    <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">
 <title>LISTADO SOCIOS</title>



 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script async="async" src="https://cdn.rawgit.com/sergiottellez/TFG/165c8450/src/main/webapp/WEB-INF/jsp/ControlListado.js"/>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script async="async"  src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/confirmar.js"></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/407a3959/src/main/webapp/WEB-INF/jsp/crearCSV.js"> </script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/4e29c6e4/src/main/webapp/WEB-INF/jsp/generarTexto.js"> </script>
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
  <c:if test="${user.role == 1}"> <li><a href="verLista">Listado de Socios</a></li></c:if>
   <li><a href="editUser?id=${user.email}">Mi Perfil</a></li>
   <c:if test="${user.role == 1}"><li><a href="summary">Summary</a></li></c:if>
  <c:if test="${user.role == 1}"> <li><a href="newUser">Nuevo Usuario</a></li></c:if>
      <c:if test="${user.role == 1}"><li><a href="verBajas">Bajas</a></li></c:if>
  <li>  <a id="logout" href="logout">Logout</a></li>


</ul>  

<div class="container-fluid">
 <div class="row-fluid">
  <div class="col-md-6">
   <h4 class="text-center">Lista de posibles bajas</h4>
   
   
     
                   
               
   <hr>
   <table id="tabletocsv" class="table table-bordered table-striped">
    <thead>
    <tr>
     <th>Email</th>
     <th>Nombre</th>
     <th>Apellido1</th>
    <th>Apellido2</th>
    <th>Rol</th>
    <th>Universidad</th>
     <th>Region</th>
     <th>Grupo</th>
     <th>Empresa</th>
     <th>Antiguedad</th>
     <th>Reciente</th>
     <th>Activo</th>
          <c:if test="${sessionScope.user.role == 1}" > <th>Historico</th> </c:if>

     <c:if test="${sessionScope.user.role == 1}" > <th>Editar</th> </c:if>
      <c:if test="${sessionScope.user.role == 1}" > <th>Eliminar</th></c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="users" items="${bajas}">
     <tr>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.email}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.nombre}"/> </td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido1}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido2}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.activo == 1}"><c:out value="Admin"/></c:if><c:if test="${users.activo == 0}"><c:out value="Socio"/></c:if></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.universidad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.region}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td> <a href='grupoUserP?id=${users.grupoInvestigacion}'> <c:out value="${users.grupoInvestigacion}"/></td></a></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.empresa == 1}"><c:out value="Si"/></c:if><c:if test="${users.empresa == 0}"><c:out value="No"/></c:if></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.antiguedad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.reciente}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.activo == 1}"><c:out value="Si"/></c:if><c:if test="${users.activo == 0}"><c:out value="No"/></c:if></td></c:if>

      
            <c:if test="${user.role == 1}"><td><a href="verHistorial?id=${users.user_id}" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-book"></i>Historial</a></td></c:if>

      <c:if test="${user.role == 1}" > <td><a href="editUser?id=${users.email}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-check"></i>Editar</a></td></c:if>
      <c:if test="${user.role == 1}" ><td><a href="deleteUser?id=${users.user_id}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash" onClick="return confirm('¿Desea dar de baja a este usuario del sistema?');"></i>Eliminar</a></td> </c:if>
     </tr>
    </c:forEach>    
    </tbody>
   </table>
   <br />
   
   
   <a href="home" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Home</a>
   <button onClick="crearCSV()" class="btn btn-success"> <c:if test="${user.role == 1}" > Exportar lista a CSV</button></c:if> 

 
 
   <button onClick="descargartxt('${emails}')" class="btn btn-success"> <c:if test="${user.role == 1}" > Obtener emails</button></c:if> 
  
  <a href="summary" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Summary </a>
<br/>
<br/>

  </div>
 </div>
</div>

<footer>
        <div class='define'>
		<img src="https://www.sistedes.es/wp-content/themes/sistedes/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
        </div>
    </footer>
</body>
</html>