<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issues</title>
</head>
<body>
<c:url value="#" var="issuesURL"/>
<sf:form method="post" action="#{issuesURL}" modelAttribute="issueFormDto">
    <sf:select path="publishingId">
        <c:forEach var="p" items="${publishing}">
            <sf:option value="${p.id}">${p.title}</sf:option>
        </c:forEach>
    </sf:select>
</sf:form>
</body>
</html>
