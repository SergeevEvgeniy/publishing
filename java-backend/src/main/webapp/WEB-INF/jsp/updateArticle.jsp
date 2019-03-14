<%--
  Created by IntelliJ IDEA.
  User: Denis Shubin
  Date: 27.02.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<tag:layout>
    <div class="container">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="publishingSelector" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Журнал</label>
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <select id="publishingSelector" class="form-control">
                        <c:forEach items="${model.publishings}" var="publishing">
                            <option data-publishingId="${publishing.id}">${publishing.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="topicSelector" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Рубрика</label>
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <select id="topicSelector" class="form-control">
                        <c:forEach items="${model.topics}" var="topic">
                            <option data-topicId="${topic.id}">${topic.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="title" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Название</label>
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <input type="text" id="title" value="${model.title}" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="content" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Содержание</label>
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <textarea id="content" class="form-control">${model.content}</textarea>
                </div>
            </div>

            <label for="coauthorsTable" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Соавторы</label>
            <div class="col-lg-8 col-md-8 col-sm-8">
                <table id="coauthorsTable">
                    <tbody>
                        <tr>
                    <select class="form-control">
                        <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                            <option data-coauthorId="${availableCoauthor.id}">
                                ${availableCoauthor.firstName} ${availableCoauthor.lastName} ${availableCoauthor.middleName}
                            </option>
                        </c:forEach>
                    </select>
                    <a href="#">
                        <span class="btn btn-success">Добавить</span>
                    </a>
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
            </div>
            <div>
                <a>
                    <span class="btn btn-danger">Отменить</span>
                    <span class="btn btn-primary">Сохранить</span>
                </a>
            </div>
        </form>
    </div>
</tag:layout>