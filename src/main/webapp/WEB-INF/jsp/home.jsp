<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
        <link rel="shortcut icon" href="https://cdn.rawgit.com/sergiottellez/TFG/289ded45/src/main/webapp/WEB-INF/jsp/images/logo069_400x400.ico">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
 <a href='<c:url value="/logout" />'>Logout</a>
 <h1>Welcome 
 <c:if test="${pageContext.request.userPrincipal.name != null }">
 ${pageContext.request.userPrincipal.name }
 </c:if> </h1>
 <br/>
 <a href='<c:url value="/verLista" />'>Listado de socio</a>
 
 
 <footer>
        <div class='define'>
		<img src="https://www.sistedes.es/wp-content/themes/sistedes/logo.png" class="img-responsive center-block" width="300" height="300" alt="Logo" />
        </div>
    </footer>
</body>
</html>