<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
        <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">

<meta http-equiv="Content-Type" content="text/html; utf-8">

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src=”https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js”></script>
 <script src="https://cdn.rawgit.com/sergiottellez/TFG/bdcde6b0/src/main/webapp/WEB-INF/jsp/confirmar.js"> </script>
<title>Home</title>


</head>
<body <c:if test="${popup == true}"> onunload="alert('${fichero}')" </c:if> >
    
  
    
        
        
              <a id="logout" href="logout"   class="btn btn-default btn-sm"> <i class="glyphicon glyphicon-log-out"></i>Logout </a>
          

    <h1 style="text-align:center;">Welcome 
 <c:if test="${user.nombre != null }">
 ${user.nombre }
 </c:if> </h1>
 <br/>
<div class="trans text-center"> 
    <c:if test="${user.role == 1 && user.primerLogin == 0}"><a href='<c:url value="/verLista" />' class="btn btn-info  btninter centrado">Listado de Socios</a></c:if>
 
    <a href="editUser?id=${user.email}"  class="btn btn-info  btninter centrado">Mi Perfil</a>
    
 <c:if test="${user.role == 1 && user.primerLogin == 0}"><a href='<c:url value="/summary" />' class="btn btn-info  btninter centrado">Resumen</a></c:if>
 
 <c:if test="${user.role == 1 && user.primerLogin == 0}"> <a href='<c:url value="/verBajas" />' class="btn btn-info  btninter centrado">Ver posibles bajas</a></c:if>

 
  <c:if test="${user.role == 1 && user.primerLogin == 0}"> <a href='<c:url value="newUser" />' class="btn btn-info  btninter centrado">Nuevo Usuario</a></c:if>

 </div>
 
 
 <br/>
   
 
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