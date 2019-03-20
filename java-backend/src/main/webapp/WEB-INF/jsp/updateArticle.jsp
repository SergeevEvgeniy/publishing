<%--
  Created by IntelliJ IDEA.
  User: Denis Shubin
  Date: 27.02.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout>
    <form class="form-horizontal center-block">

        <div class="form-group">
            <label for="publishingSelector" class="col-sm-4 h4">Журнал</label>
            <div class="col-sm-8">
                <select id="publishingSelector" class="form-control">
                    <c:forEach items="${model.publishings}" var="publishing">
                        <option value="${publishing.id}">${publishing.title}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="topicSelector" class="label-control col-sm-4 h4">Рубрика</label>
            <div class="col-sm-8">
                <select id="topicSelector" class="form-control">
                    <c:forEach items="${model.topics}" var="topic">
                        <option data-topic-id="${topic.id}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="title" class="label-control col-sm-4 h4">Название</label>
            <div class="col-sm-8">
                <input type="text" id="title" value="${model.title}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label for="content" class="h4">Содержание</label>
            </div>
            <div class="col-sm-12">
                <textarea id="content" class="form-control no-resize">${model.content}</textarea>
            </div>
        </div>


        <div class="form-group">
            <label class="label-control col-sm-4 h4">Соавторы</label>
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-sm-8">
                                <select id="availableCoauthors" class="form-control ">
                                    <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                                        <option value="${availableCoauthor.id}">
                                                ${availableCoauthor.firstName} ${availableCoauthor.lastName} ${availableCoauthor.middleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <button id="addCoauthor" class="btn btn-block btn-success">
                                    Добавить
                                </button>
                            </div>
                        </div>
                    </div>

                    <ul id="currentCoauthors" class="list-group">
                        <c:forEach var="currentCoauthor" items="${model.currentCoauthors}">
                            <li class="list-group-item" data-coauthor-id="${currentCoauthor.id}">
                                <div class="row">
                                    <div class="col-sm-10">
                                            ${currentCoauthor.firstName}
                                            ${currentCoauthor.lastName}
                                    </div>
                                    <div class="col-sm-2 text-right">
                                        <span class="glyphicon glyphicon-trash" style="cursor: pointer"></span>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 h4">Рецензия</label>
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <Label class="h4 col-sm-4">Рецензент</Label>
                                <div class="col-sm-8">
                                    <select id="reviewerSelector" class="form-control">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <textarea id="reviewContent" class="form-control no-resize"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-right">
            <a href="/" class="btn btn-default">
                Отменить
            </a>
            <input type="submit" class="btn btn-success" value="Сохранить">
        </div>
    </form>
</tag:layout>
