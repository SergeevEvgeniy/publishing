<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout>
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
</tag:layout>
