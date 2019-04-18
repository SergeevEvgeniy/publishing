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
    <script type="text/javascript" src="<c:url value="/resources/js/updateArticle.js"/>"></script>
    <form:form class="form-horizontal center-block" method="${formMethod}" modelAttribute="articleForm">

        <div class="form-group">
            <label for="publishingSelector" class="col-sm-4 h4">Журнал</label>
            <div class="col-sm-8">
                <input type="hidden" id="hiddenPublishingId" name="publishingId" value="${articleForm.publishingId}">
                <select id="publishingSelector" class="form-control">
                    <option value="">---Выберите журнал---</option>
                    <c:forEach items="${publishingDtoList}" var="publishing">
                        <option value="${publishing.id}">${publishing.title}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="topicSelector" class="label-control col-sm-4 h4">Рубрика</label>
            <div class="col-sm-8">
                <input type="hidden" id="hiddenTopicId" name="topicId" value="${articleForm.topicId}">
                <select id="topicSelector" class="form-control" disabled="disabled">
                    <option value="">---Выберите рубрику---</option>
                    <c:forEach items="${topicShortInfos}" var="topic">
                        <option value="${topic.id}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="title" class="label-control col-sm-4 h4">Название</label>
            <div class="col-sm-8">
                <input type="text" id="title" value="${articleForm.title}" class="form-control" name="title"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label for="content" class="h4">Содержание</label>
            </div>
            <div class="col-sm-12">
                <textarea id="content" class="form-control no-resize" name="content">${articleForm.content}</textarea>
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
                                    <c:forEach var="availableCoauthor" items="${availableCoauthors}">
                                        <option value="${availableCoauthor.id}">
                                                ${availableCoauthor.shortFullName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <span id="addCoauthor" class="btn btn-block btn-success">
                                    Добавить
                                </span>
                            </div>
                        </div>
                    </div>

                    <ul id="currentCoauthors" class="list-group list-unstyled">
                        <c:forEach var="currentCoauthor" items="${currentCoauthors}">
                            <li class="list-group-item"
                                data-employee-id="${currentCoauthor.id}"
                                data-employee-name="${currentCoauthor.shortFullName}">
                                <input type="hidden" name="coauthors" value="${currentCoauthor.id}">
                                <div class="row">
                                    <div class="col-sm-10">
                                            ${currentCoauthor.shortFullName}
                                    </div>
                                    <div class="col-sm-2 text-right">
                                        <span class="glyphicon glyphicon-trash delete-coauthor" style="cursor: pointer"
                                              data-id="${currentCoauthor.id}"></span>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <c:if test="${isEditMode and reviewShortInfos.size() != 0}">
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
                                            <option value="">---Выберите рецензента---</option>
                                            <c:forEach var="element" items="${reviewShortInfos}">
                                                <option value="${element.reviewerId}">
                                                        ${element.reviewerShortName}
                                                </option>
                                            </c:forEach>
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
        </c:if>
        <div class="text-right">
            <a
                <c:if test="${isEditMode}">href="../../article"</c:if>
                <c:if test="${not isEditMode}">href="../article"</c:if> class="btn btn-default">
                Отменить
            </a>
            <input type="submit" class="btn btn-success" value="Сохранить">
        </div>
    </form:form>

    <div id="currentCoauthorTemplate" class="hidden">
        <li class="list-group-item">
            <input type="hidden" name="coauthors">
            <div class="row">
                <div class="col-xs-10 new-full-name"></div>
                <div class="col-xs-2 text-right">
                    <span class="glyphicon glyphicon-trash delete-coauthor" style="cursor: pointer"></span>
                </div>
            </div>
        </li>
    </div>

    <c:if test="${isEditMode}">
        <div id="articleId" class="hidden">${articleId}</div>
    </c:if>

</tag:layout>
