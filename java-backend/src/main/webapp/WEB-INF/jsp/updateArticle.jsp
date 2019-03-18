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
            <label for="publishingSelector" class="col-lg-4 col-md-4 col-sm-4 h4">Журнал</label>
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
            <div class="col-lg-4 col-md-4 col-sm-4">
                <label for="content" class="h4">Содержание</label>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <textarea id="content" class="form-control">${model.content}</textarea>
            </div>
        </div>


        <div class="form-group">
            <label class="label-control col-lg-4 col-md-4 col-sm-4 h4">Соавторы</label>

            <div class="col-md-12 col-lg-12 col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-8">
                                <select id="availableCoauthors" class="form-control ">
                                    <option>---------------</option>
                                    <option>coauthor 1</option>
                                    <option>coauthor 4</option>
                                    <option>coauthor 2</option>
                                    <option>coauthor 3</option>
                                    <c:forEach var="availableCoauthor" items="${model.availableCoauthors}">
                                        <option data-coauthorId="${availableCoauthor.id}">
                                                ${availableCoauthor.firstName} ${availableCoauthor.lastName} ${availableCoauthor.middleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4">
                                <div class="row">
                                    <button id="addCoauthor" class="btn btn-success col-md-11 col-sm-11 col-lg-11">
                                        Добавить
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <ul id="currentCoauthors" class="list-group">
                        <li class="list-group-item">
                            <input type="hidden" value="1" name="currentCoauthorId"/>
                            <div class="row">
                                <div class="col-xs-10">
                                    Дед Игнат
                                </div>
                                <div class="col-xs-2 text-right">
                                    <span class="glyphicon glyphicon-trash" style="cursor: pointer"></span>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <input type="hidden" value="2" name="currentCoauthorId"/>
                            <div class="row">
                                <div
                                    class="col-xs-10">Тётка Валя
                                </div>
                                <div class="col-xs-2 text-right">
                            <span class="glyphicon glyphicon-trash"
                                  style="cursor: pointer"></span>
                                </div>
                            </div>
                        </li>
                        <c:forEach var="currentCoauthor" items="${model.currentCoauthors}">
                            <li class="list-group-item">
                                <input type="hidden" value="${currentCoauthor.id}"
                                       name="currentCoauthorId"/>

                                <div class="row">
                                    <div class="col-xs-10">
                                            ${currentCoauthor.firstName}
                                            ${currentCoauthor.lastName}
                                    </div>
                                    <div class="col-xs-2 text-right">

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
            <label class="col-lg-4 col-md-4 col-sm-4 h4">Рецензенты</label>
            <div class="panel panel-default">
                <div class="row">
                    <Label class="col-md-4 col-sm-4 col-lg-4"></Label>
                    <div class="col-md-12 col-sm-12 col-lg-12">
                        <Label class="h5 col-lg-4 col-md-4 col-sm-4">Рецензент</Label>
                        <div class="col-lg-8 col-md-8 col-sm-8">
                            <select id="reviewerSelector" class="form-control">
                                <option>213</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</tag:layout>
