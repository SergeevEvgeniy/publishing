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
                <option>${publishing}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="topicSelector">Рубрика</label>
        <select id="topicSelector">
            <c:forEach items="${model.topics}" var="topic">
                <option>${topic}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="title">Название</label>
        <input type="text" id="title"/>
    </div>
    <div>
        <label for="content">Содержание</label>
        <textarea id="content"></textarea>
    </div>

    <label for="coauthorsTable">Соавторы</label>
    <table id="coauthorsTable">
        <tbody>
        <tr>
            <select>
                <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                    <option>${availableCoauthor}</option>
                </c:forEach>
            </select>
            <button>Добавить</button>
        </tr>
        <c:forEach items="${model.currentCoauthors}" var="currentCoauthor">
            <tr>
                <td>
                    ${currentCoauthor}
                    <button>Удалить</button>
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
                        <option>${review.reviewerId}</option>
                    </c:forEach>
                </select>
            </tr>
            <tr>
                <textarea></textarea>
            </tr>
            </tbody>
        </table>
    </table>

</form>
</body>
</html>
