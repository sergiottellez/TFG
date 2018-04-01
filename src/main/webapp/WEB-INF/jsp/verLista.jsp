<%-- 
    Document   : verLista
    Created on : 15-mar-2018, 12:16:13
    Author     : sergiottellez
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
    <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">
 <title>LISTADO SOCIOS</title>



 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script async="async"  src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/confirmar.js"></script>

</head>
<body>
<div class="container-fluid">
 <div class="row-fluid">
  <div class="col-md-6">
   <h4 class="text-center">Lista de SOCIOS. USUARIO: ${pageContext.request.userPrincipal.name }</h4>
   <hr>
   <table class="table table-bordered table-striped">
    <thead>
    <tr>
     <th>Email</th>
     <th>Nombre</th>
     <th>Apellido1</th>
    <th>Apellido2</th>
    <th>Rol</th>
    <th>Universidad</th>
     <th>Region</th>
     <th>Empresa</th>
     <th>Antiguedad</th>
     <th>Reciente</th>
     <th>Activo</th>
     <th>Editar</th>
     <th>Eliminar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${datos}">
     <tr>
      <td><c:out value="${user.email}"/></td>
      <td><c:out value="${user.name}"/> </td>
      <td><c:out value="${user.apellido1}"/></td>
      <td><c:out value="${user.apellido2}"/></td>
      <td><c:out value="${user.role}"/></td>
      <td><c:out value="${user.universidad}"/></td>
      <td><c:out value="${user.region}"/></td>
      <td><c:out value="${user.empresa}"/></td>
      <td><c:out value="${user.antiguedad}"/></td>
      <td><c:out value="${user.reciente}"/></td>
      <td><c:out value="${user.activo}"/></td>

      <td><a href="editUser?id=${user.email}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-check"></i> Editar</a></td>
      <td><a href="deleteUser?id=${user.email}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash" onClick="return confirmar()"></i> Eliminar</a></td>
     </tr>
    </c:forEach>    
    </tbody>
   </table>
   <br />
   <a href="newUser" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> Nuevo Socio</a>
   <a href="importExcel" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Importar desde Excel   </a>
   <a href="summary" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Summary </a>

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