<%-- 
    Document   : layout
    Created on : Mar 14, 2019, 1:03:38 PM
    Author     : Sergeev Evgeniy
--%>

<%@tag description="Include style css and nav-bar" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mailing.css">
    </head>
    <body>
        <header>
            <jsp:include page="../jsp/header.jsp"/>
        </header>
        <main>
            <jsp:doBody/>
        </main>
    </body>
</html>