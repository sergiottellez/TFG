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
  <script async="async" src="https://cdn.rawgit.com/sergiottellez/TFG/165c8450/src/main/webapp/WEB-INF/jsp/ControlListado.js"/>

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
   <h4 class="text-center">Lista de SOCIOS. USUARIO:</h4>
   
   
     
                    <div class="panel-body">
                        <form id="listar" action="verLista" method="POST">              
                            <label> Tipo de busqueda: </label>
                            <div id="txt">
                                <input type="text" id="textoTipoBusqueda"  name="textoTipoBusqueda" />
                            </div>
                             <br/> 
                            <select id="tipoListadoNumero" value="6" name="tipoListadoNumero" onchange="campoBusqueda(this.value)">
                                <option value= "0" itemLabel="Email" label="Email"/>
                                <option value = "1" itemLabel = "Universidad" label="Universidad"/>
                                <option value = "4" itemLabel = "Región" label="Region"/> 
                                <option value = "3" itemLabel = "Activos" label="Usuarios Activos" />
                                <option value = "5" itemLabel = "Nombre" label="Nombre" />
                                <option value = "6" itemLabel = "Todos" label="Todos" />
       
                            </select>
                            
                            <br/>
                            <br/>
                              <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>Buscar</button>         
                            <br/>
                            
                            
                        </form>
                    </div>
               
   <hr>
   <table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>Fecha</th>
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

    </tr>
    </thead>
    <tbody>
    <c:forEach var="users" items="${historial}">
     <tr>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" > <td><c:out value="${users.fecha}"/></td></c:if>   
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" > <td><c:out value="${users.email}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.nombre}"/> </td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido1}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido2}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.role == 1}"><c:out value="Admin"/></c:if><c:if test="${users.role == 0}"><c:out value="Socio"/></c:if></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.universidad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.region}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td> <a href="grupoUserP?id=${users.grupoInvestigacion}"> <c:out value="${users.grupoInvestigacion}"/></td></a></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.empresa == 0}"><c:out value="No"/></c:if><c:if test="${users.empresa == 1}"><c:out value="Si"/></c:if></td></c:if></td>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.antiguedad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.reciente}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.activo == 1}"><c:out value="Si"/></c:if><c:if test="${users.activo == 0}"><c:out value="No"/></c:if></td></c:if>

      

     </tr>
    </c:forEach>    
    </tbody>
   </table>
   <br />
  
   <a href="summary" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Summary </a>
   <a href="home" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Home </a>

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