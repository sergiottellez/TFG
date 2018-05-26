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
 <title>SUMARIO</title>



 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script async="async"  src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/confirmar.js"></script>
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
   <h4 class="text-center">SUMMARY</h4>
   <hr>
   
    <h3 class="text-center">Universidades/Centros</h3>
   <table class="table table-bordered table-striped">
    <thead>
        
    <tr>
     <th>Univ/Centro</th>
     <th>Total</th>
     <th>Activos</th>
    
    </tr>
    </thead>
    <tbody>
    <c:forEach var="univ" items="${datosFinales}">
     <tr>
      <td><c:out value="${univ.key}"/></td>
      <td><c:out value="${univ.value.x}"/> </td>
      <td><c:out value="${univ.value.y}"/> </td>
      <td><c:out value="${(univ.value.y * 100) / univ.value.x} %"/> </td>
    
     
        </tr>
    </c:forEach>    
    </tbody>
   </table>
   <br />
   
    </tbody>
   </table>
   
    <h3 class="text-center">Region</h3>
   <table class="table table-bordered table-striped">
    <thead>
    <tr>
     <th>Región</th>
     <th>Total</th>
     <th>Activos</th>
    
    </tr>
    </thead>
    <tbody>
    <c:forEach var="datos" items="${datosRegion}">
     <tr>
     <td><c:out value="${datos.key}"/></td>
      <td><c:out value="${datos.value.x}"/> </td>
      <td><c:out value="${datos.value.y}"/> </td>
      <td><c:out value="${(datos.value.y * 100) / datos.value.x} %"/> </td>
    
     
        </tr>
    </c:forEach>    
    </tbody>
   </table>
   <br />
   
    </tbody>
   </table>
   <br />
   
   
   <h3 class="text-center">Mujeres</h3>
    <table class="table table-bordered table-striped">
    <thead>
    <tr>
     <th>Total</th>
     <th>Mujeres activas</th>
    
    </tr>
    </thead>
    <tbody>
   
     <tr>
     
      <td><c:out value="${datosMujeres.x} %"/> </td>
      <td><c:out value="${datosMujeres.y} %"/> </td>
    
     
        </tr>
     
    </tbody>
   </table>
   <br />
   
    </tbody>
   </table>
   <br />
   <a href="home" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Home</a>
   <a href="verLista" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Socios SISTEDES</a>

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