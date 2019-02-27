<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rezerv
  Date: 27.02.2019
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
<form:form modelAttribute="list">
    <button>Добавить статью</button>
    <table>
        <thead>
        <tr>
            <td>Название</td>
            <td>Журнал/газета</td>
            <td>Рубрика</td>
            <td>Автор</td>
            <td>Соавторы</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${list}">
            <tr>
                <td>${element.title}</td>
                <td>${element.publishing}</td>
                <td>${element.topic}</td>
                <td>${element.author}</td>
                <td>
                    <ul>
                        <c:forEach items="${element.coauthors}" var="coauthor">
                            <li> ${coauthor}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <button>Редактировать</button>
                    <button>Удалить</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form:form>
</body>
</html>
