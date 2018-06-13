<%-- 
    Document   : grupoUser
    Created on : 14-abr-2018, 11:25:15
    Author     : sergiottellez
--%>

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
    <h4 class="text-center">Grupo Investigación</h4>
    <hr>
        
        
        
        <div class="form-group">
          <h2>Nombre de grupo:</h2>
          </br>
          <label> ${grupoN.nombre} </label>
          
     </div>
          </br>

     <div class="form-group">
          <h2>URL</h2>
          <a href="${grupoN.url}" target="_blank"> ${grupoN.url}</a>  
     </div>
     
     </br>
     
      <div class="form-group">
          <h2>Lider:</h2>
          <label>${lider}</label>
         
     </div>
          </br>

     
     <div class="form-group">
          <h2>Corresponsal:</h2>
          <label>${corresponsal}</label>
     </div>


     
    
  
   </div>
  </div>
 </div>
    
     
 <a href="home" class="btn btn-success" >
 <i class="glyphicon glyphicon-edit"></i> Home</a> 
    <footer>
        <div class='define'>
		<img src="https://cdn.rawgit.com/sergiottellez/TFG/0a02bc85/src/main/webapp/WEB-INF/jsp/images/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
        </div>
    </footer>
</body>
</html>