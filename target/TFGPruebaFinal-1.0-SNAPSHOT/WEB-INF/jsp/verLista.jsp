<%-- 
    Document   : verLista
    Created on : 15-mar-2018, 12:16:13
    Author     : sergiottellez
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">




<html lang="es">
<head>
    <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">
 <title>LISTADO SOCIOS</title>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script async="async" src="https://cdn.rawgit.com/sergiottellez/TFG/165c8450/src/main/webapp/WEB-INF/jsp/ControlListado.js"/>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/bdcde6b0/src/main/webapp/WEB-INF/jsp/confirmar.js"></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/407a3959/src/main/webapp/WEB-INF/jsp/crearCSV.js"> </script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/583a1506/src/main/webapp/WEB-INF/jsp/generarLog.js"> </script>
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
   <div class="table table-striped">
       <table id="tabletocsv" style="margin: auto;"  class="table table-bordered table-striped">
    <thead>
    <tr>
     <th scope="col">Email</th>
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
    <c:forEach var="users" items="${datos}">
     <tr>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td ><c:out value="${users.email}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.nombre}"/> </td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido1}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.apellido2}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.role == 1}"><c:out value="Admin"/></c:if><c:if test="${users.role == 0}"><c:out value="Socio"/></c:if></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.universidad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.region}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td> <a href='grupoUserP?id=${users.grupoInvestigacion}'> <c:out value="${users.grupoInvestigacion}"/></td></a></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.empresa == 1}"><c:out value="Si"/></c:if><c:if test="${users.empresa == 0}"><c:out value="No"/></c:if></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.antiguedad}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:out value="${users.reciente}"/></td></c:if>
      <c:if test="${sessionScope.user.role == 1 ||sessionScope.user.email.equals(users.email)}" ><td><c:if test="${users.activo == 1}"><c:out value="Si"/></c:if><c:if test="${users.activo == 0}"><c:out value="No"/></c:if></td></c:if>

      
            <c:if test="${user.role == 1}"><td><a href="verHistorial?id=${users.user_id}" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-book"></i>Historial</a></td></c:if>

      <c:if test="${user.role == 1}" > <td><a href="editUser?id=${users.email}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-check"></i>Editar</a></td></c:if>
      <c:if test="${user.role == 1}" ><td><a href="deleteUser?id=${users.user_id}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash" onclick="confirmar()"></i>Eliminar</a></td> </c:if>
      </tr>
    </c:forEach>    
    </tbody>
   </table>
   </div>
   <br />
   
   <a href="home" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Home</a>
     <c:if test="${user.role == 1}" > <a href="newUser" class="btn btn-success" >
 <i class="glyphicon glyphicon-edit"></i> Nuevo Socio</a> </c:if> 
   <a href="summary" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>Summary </a>
   <button onClick="crearCSV()" class="btn btn-success"> <c:if test="${user.role == 1}" > Exportar a CSV</button></c:if> 
<br/>
<br/>
 
 <a  href="https://cdn.rawgit.com/sergiottellez/TFG/a71e0a15/src/main/webapp/WEB-INF/jsp/SociosSISTEDESEjemplo.csv" download="Prueba.csv"  class="btn btn-success"><i class="glyphicon glyphicon-download"></i>Descargar Plantilla CSV </a>

 <br/>
 <br/>
  <c:if test="${user.role == 1}" >  <c:url value="/uploadExcelFile" var="uploadFileUrl" />
<form method="post" enctype="multipart/form-data"
  action="${uploadFileUrl}">
    <input type="file" name="file" accept=".xls,.xlsx, .csv" /> <input
        type="submit" value="Importar archivo" class="btn btn-success " />
</form>  </a> </c:if> 
<br/>

<br/>

<textarea type="text" hidden="true" id="textbox" value="${fichero}"> "${fichero}" </textarea> <button id="create" onclick="generarLog()" >Generar log importación</button> <a download="log.txt" id="downloadlink" style="display: none">Download</a>


  </div>
 </div>
</div>

<footer>
        <div class='define'>
		<img src="https://www.sistedes.es/wp-content/themes/sistedes/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
        </div>
    
    <div>
                           <button type="button" onclick="ayuda()" class="btn btn-info"><span  class="glyphicon glyphicon-question-sign"></span></button>

         
     </div>
    </footer>
</body>
</html>