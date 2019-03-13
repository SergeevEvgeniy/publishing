<%@ tag %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mailing.css">

    <script src="${pageContext.request.contextPath}/resources/js/libs/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mailing.js"></script>
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
