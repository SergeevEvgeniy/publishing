<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issues</title>
</head>
<body>
<c:url value="#" var="issuesURL"/>
<sf:form method="post" action="${issuesURL}" modelAttribute="issueFormDto">
    <sf:select path="publishingId" id="publishingSelect">
        <option></option>
        <c:forEach var="p" items="${publishing}">
            <sf:option value="${p.id}">${p.title}</sf:option>
        </c:forEach>
    </sf:select>
    <select id="topicsSelect"></select>
    <select id="authorSelect"></select>
    <select id="articlesSelect"></select>
    <input type="submit" value="Проверка"/>
</sf:form>
<script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/issues-page.js"/>"></script>
</body>
</html>
