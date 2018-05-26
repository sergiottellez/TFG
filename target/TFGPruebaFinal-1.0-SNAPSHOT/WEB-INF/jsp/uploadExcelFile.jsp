<%-- 
    Document   : uploadExcelFile
    Created on : 13-may-2018, 10:55:47
    Author     : sergiottellez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:url value="/uploadExcelFile" var="uploadFileUrl" />
            <form method="post" enctype="multipart/form-data"
  action="${uploadFileUrl}">
    <input type="file" name="file" accept=".xls,.xlsx" /> <input
      type="submit" value="Upload file" />
            </form>
    </body>
</html>
