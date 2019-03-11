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
<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
    <title>Update article</title>
    <meta charset="UTF-8"/>
</head>
<body>
<div class="container">

    <form class="form-horizontal">
        <div class="form-group">
            <label for="publishingSelector" class="label-control">Журнал</label>
            <select id="publishingSelector" class="form-control">
                <c:forEach items="${model.publishings}" var="publishing">
                    <option data-publishingId="${publishing.id}">${publishing.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="topicSelector" class="label-control">Рубрика</label>
            <select id="topicSelector" class="form-control">
                <c:forEach items="${model.topics}" var="topic">
                    <option data-topicId="${topic.id}">${topic.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="title" class="label-control">Название</label>
            <input type="text" id="title" value="${model.title}" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="content" class="label-control">Содержание</label>
            <textarea id="content" class="form-control">${model.content}</textarea>
        </div>

        <label for="coauthorsTable" class="label-control">Соавторы</label>
        <table id="coauthorsTable">
            <tbody>
            <tr>
                <select class="form-control">
                    <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                        <option
                            data-coauthorId="${availableCoauthor.id}">${availableCoauthor.firstName} ${availableCoauthor.lastName} ${availableCoauthor.middleName}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-success">Добавить</button>
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

            <label for="reviewTable" class="label-control">Рецензия</label>
            <div class="form-group">
                <table id="reviewTable">
                    <tbody>
                    <tr>
                        <div class="form-group">
                            <label for="reviewerSelector" class="label-control">Рецензент</label>
                            <select id="reviewerSelector" class="form-control">
                                <c:forEach var="review" items="${model.reviews}">
                                    <option data-reviewerId="${review.reviewerId}">${review.reviewerId}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </tr>

                    <tr>
                        <td>
                            <div class="form-group">
                                <textarea id="reviewContent" class="form-control"></textarea>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </table>

    </form>
</div>


</body>
</html>
