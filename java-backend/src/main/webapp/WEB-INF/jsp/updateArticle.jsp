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
<tag:layout pageTitle="Обновить статью">
    <script type="text/javascript" src="<c:url value="/resources/js/updateArticle.js"/>"></script>
    <form:form class="form-horizontal center-block" method="post" modelAttribute="articleForm">

        <div class="form-group">
            <label for="publishingSelector" class="col-sm-4 h4">Журнал</label>
            <div class="col-sm-8">
                <form:hidden path="publishingId" id="hiddenPublishingId"/>
                <form:select path="publishingId" id="publishingSelector" cssClass="form-control">
                    <form:option value="" label="---Выберите журнал---"/>
                    <form:options items="${publishingDtoList}" itemValue="id" itemLabel="title"/>
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label for="topicSelector" class="label-control col-sm-4 h4">Рубрика</label>
            <div class="col-sm-8">
                <form:hidden path="topicId" id="hiddenTopicId"/>
                <form:select id="topicSelector" path="topicId" cssClass="form-control">
                    <form:option value="" label="---Выберите журнал---"/>
                    <form:options items="${topicShortInfos}" itemValue="id" itemLabel="name"/>
                </form:select>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="title" class="label-control col-sm-4 h4">Название</label>
            <div class="col-sm-8">
                <form:input htmlEscape="true" path="title" id="title" value="${articleForm.title}"
                            cssClass="form-control" name="title"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label for="content" class="h4">Содержание</label>
            </div>
            <div class="col-sm-12">
                <form:textarea htmlEscape="true" path="content" cssClass="form-control no-resize"/>
            </div>
        </div>


        <div class="form-group">
            <label class="label-control col-sm-4 h4">Соавторы</label>
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-sm-8">
                                <select id="availableCoauthors" class="form-control">

                                    <c:forEach items="${availableCoauthors}" var="coauthor">
                                        <option value="${coauthor.id}">
                                            <c:out value="${coauthor.shortFullName}"/>
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
                                    <Label for="reviewerSelector" class="h4 col-sm-4">Рецензент</Label>
                                    <div class="col-sm-8">
                                        <form:select multiple="false" path="shortInfos" id="reviewerSelector"
                                                     class="form-control">
                                            <form:option value="" label="---Выберите рецензента---"/>
                                            <form:options items="${reviewShortInfos}" itemValue="reviewerId"
                                                          itemLabel="reviewerShortName"/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <span id="reviewContent" class="form-control-static"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="text-right">
            <a href="${pageContext.request.contextPath}/article" class="btn btn-default">
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
