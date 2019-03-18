<%--
    Document   : layout
    Created on : Mar 14, 2019, 1:03:38 PM
    Author     : Sergeev Evgeniy
--%>

<%@tag description="Include style css and nav-bar" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mailing.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/update-article.css">

        <script src="${pageContext.request.contextPath}/resources/js/libs/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mailing.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/libs/bootstrap.js"/>"></script>
    <script>
        var APP_CONTEXT_PATH = '${pageContext.request.contextPath}';
    </script>
</head>
<body>

    <header>
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    </header>
    <main>
        <jsp:doBody  />
    </main>

</body>
</html>
