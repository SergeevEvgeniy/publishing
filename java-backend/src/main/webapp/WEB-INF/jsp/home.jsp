<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
        <meta charset="UTF-8"/>    
    </head>
    <body>
        <jsp:include page="logoutHeader.jsp"/>
        <h1>Привет, Мир</h1>
        <h2>Это домашняя страница</h2>
    </body>
</html>
