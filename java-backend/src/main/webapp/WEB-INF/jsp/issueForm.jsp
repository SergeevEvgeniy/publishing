<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout>

    <c:set var="mode" value="${param.mode}"/>

    <h3 class="page-header">
        <c:choose>
            <c:when test="${mode == 'create'}">
                Форма создания
                <c:set var="actionFromURL"
                       value="${pageContext.request.contextPath}/issues/issue/${param.id}"/>
            </c:when>
            <c:when test="${mode == 'edit'}">
                Форма редактирования
                <c:set var="actionFromURL"
                       value="${pageContext.request.contextPath}/issues/issue/${param.id}"/>
            </c:when>
            <c:when test="${mode == 'view'}">
                Форма просмотра
                <c:set var="actionFromURL"
                       value="#"/>
            </c:when>
        </c:choose>
    </h3>

    <div class="row">

        <div class="col-sm-offset-2 col-sm-8">

            <form:form class="form-horizontal" method="${mode != 'view' ? 'POST' : 'GET'}"
                       action="${actionFromURL}" modelAttribute="issueForm">

                <div class="form-group">
                    <label class="control-label col-sm-1" for="publishingId">
                        Журналы
                    </label>
                    <div class="col-sm-offset-1 col-sm-10">
                        <c:if test="${mode == 'view'}">
                            <p class="form-control-static">
                                    ${issueForm.publishingTitle}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <form:select cssClass="form-control" path="publishingId">
                                <c:if test="${mode == 'create'}">
                                    <form:option value="" label=">--Выберете журнал--<" />
                                </c:if>
                                <form:options items="${publishing}"/>
                            </form:select>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-1" for="number">
                        Номер
                    </label>
                    <div class="col-sm-offset-1 col-sm-3">
                        <c:if test="${mode == 'view'}">
                            <p class="form-control-static">
                                    ${issueForm.number}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <form:input type="text" class="form-control" path="number"/>
                        </c:if>
                    </div>
                    <label class="control-label col-sm-offset-2 col-sm-2" for="localDate">
                        Дата
                    </label>
                    <div class="col-sm-3">
                        <c:if test="${mode == 'view'}">
                            <p class="form-control-static">
                                    ${issueForm.localDate}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <form:input type="date" class="form-control" path="localDate"/>
                        </c:if>
                    </div>
                </div>

                <h4>Содержание</h4>
                <div class="panel panel-default">

                    <c:if test="${mode != 'view'}">
                        <div class="panel-heading">

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="topics">Рубрика</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="topics" name="topicId" disabled></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="authors">Автор</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="authors" name="authorId" disabled></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="articles">Статья</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="articles" disabled></select>
                                </div>
                            </div>

                            <div class="text-right">
                                <input type="button" class="btn btn-success" value="Добавить" disabled>
                            </div>

                        </div>
                    </c:if>

                    <ul class="list-group">
                        <c:forEach var="article" items="${articles}">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <span>${article.title}</span>
                                    </div>
                                    <c:if test="${mode != 'view'}">
                                        <div class="col-xs-2 text-right">
                                            <span class="glyphicon glyphicon-trash" style="cursor: pointer"></span>
                                        </div>
                                    </c:if>
                                </div>
                        </c:forEach>
                    </ul>

                </div>

                <h4>Реклама</h4>
                <div class="panel panel-default">

                    <c:if test="${mode != 'view'}">
                        <div class="panel-heading">

                            <div class="row">
                                <div class="col-sm-9">
                                    <input type="text" class="form-control">
                                </div>
                                <div class="col-sm-3">
                                    <input type="button" class="btn btn-success form-control" value="Добавить">
                                </div>
                            </div>

                        </div>
                    </c:if>

                    <ul class="list-group">
                        <c:forEach var="advertising" items="${advertising}">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <a href="${advertising.filePath}">
                                                ${advertising.filePath}
                                        </a>
                                    </div>
                                    <c:if test="${mode != 'view'}">
                                        <div class="col-xs-2 text-right">
                                            <span class="glyphicon glyphicon-trash" style="cursor: pointer"></span>
                                        </div>
                                    </c:if>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                </div>

                <c:if test="${mode == 'edit'}">
                    <div class="form-group">
                        <div class="col-sm-offset-8 col-lg-offset-9 col-sm-4 col-lg-3">
                            <input type="button" class="btn btn-info form-control" value="Опубликовать">
                        </div>
                    </div>
                </c:if>

                <c:if test="${mode != 'view'}">
                    <div class="text-right">
                        <input type="button" class="btn btn-secondary" value="Отменить">
                        <input type="button" class="btn btn-primary" value="Сохранить">
                    </div>
                </c:if>

            </form:form>

        </div>

    </div>

</tag:layout>
