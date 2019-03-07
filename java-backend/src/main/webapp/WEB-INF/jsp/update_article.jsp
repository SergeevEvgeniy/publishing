<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rezerv
  Date: 27.02.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update article</title>
</head>
<body>
<form>
    <div>
        <label for="publishingSelector">Журнал</label>
        <select id="publishingSelector">
            <c:forEach items="${model.publishings}" var="publishing">
                <option data-publishingId="${publishing.id}">${publishing.title}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="topicSelector">Рубрика</label>
        <select id="topicSelector">
            <c:forEach items="${model.topics}" var="topic">
                <option data-topicId="${topic.id}">${topic.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="title">Название</label>
        <input type="text" id="title" value="${model.title}"/>
    </div>
    <div>
        <label for="content">Содержание</label>
        <textarea id="content">${model.content}</textarea>
    </div>

    <label for="coauthorsTable">Соавторы</label>
    <table id="coauthorsTable">
        <tbody>
        <tr>
            <select>
                <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                    <option
                        data-coauthorId="${availableCoauthor.id}">${availableCoauthor.firstName} ${availableCoauthor.lastName} ${availableCoauthor.middleName}</option>
                </c:forEach>
            </select>
            <button>Добавить</button>
        </tr>
        <c:forEach items="${model.currentCoauthors}" var="currentCoauthor">
            <tr>
                <td>
                        ${currentCoauthor.lastName} ${currentCoauthor.firstName} ${currentCoauthor.middleName}
                    <button data-coauthorId="${currentCoauthor.id}">Удалить</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

        <label for="reviewTable">Рецензия</label>
        <table id="reviewTable">
            <tbody>
            <tr>
                <label for="reviewerSelector">Рецензент</label>
                <select id="reviewerSelector">
                    <c:forEach var="review" items="${model.reviews}">
                        <option data-reviewerId="${review.reviewerId}">${review.reviewerId}</option>
                    </c:forEach>
                </select>
            </tr>
            <tr>
                <td>
                    <textarea id="reviewContent"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </table>

</form>
</body>
</html>
