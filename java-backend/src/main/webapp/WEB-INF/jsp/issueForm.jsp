<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout pageTitle="IssueForm">

    <h3 class="page-header">
        <c:choose>
            <c:when test="${mode == 'create'}">
                Форма создания
            </c:when>
            <c:when test="${mode == 'edit'}">
                Форма редактирования
            </c:when>
        </c:choose>
    </h3>

    <div class="row">

        <div class="col-sm-offset-2 col-sm-8">

            <form:form class="form-horizontal" method="POST"
                       action="${pageContext.request.contextPath}/issues/issue/${issueId}"
                       modelAttribute="issueForm">

                <spring:bind path="publishingId">
                    <div class="form-group has-feedback ${status.error ? 'has-error' : ''}">
                        <label class="control-label col-sm-1" for="publishing">
                            Журналы
                        </label>
                        <div class="col-sm-offset-1 col-sm-10">
                            <form:select cssClass="form-control" path="publishingId" id="publishing">
                                <c:if test="${mode == 'create'}">
                                    <form:option value="">>--Выберете журнал--<</form:option>
                                    <c:forEach var="p" items="${publishing}">
                                        <form:option value="${p.id}">${p.title}</form:option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${mode == 'edit'}">
                                    <form:option value="${issueView.publishingId}">
                                        ${issueView.publishingTitle}
                                    </form:option>
                                </c:if>
                            </form:select>
                            <form:errors path="publishingId" cssClass="text-danger"/>
                        </div>
                    </div>
                </spring:bind>


                <div class="form-group">
                    <spring:bind path="number">
                        <div class="has-feedback ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-1" for="number">
                                Номер
                            </label>
                            <div class="col-sm-offset-1 col-sm-3">
                                <form:input type="text" class="form-control" path="number"
                                    value="${issueView.number}"/>
                                <form:errors path="number" cssClass="text-danger"/>
                            </div>
                        </div>
                    </spring:bind>
                    <spring:bind path="localDate">
                        <div class="has-feedback ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-offset-2 col-sm-2" for="localDate">
                                Дата
                            </label>
                            <div class="col-sm-3">
                                <form:input type="date" class="form-control" path="localDate"
                                    value="${issueView.localDate}"/>
                                <form:errors path="localDate" cssClass="text-danger"/>
                            </div>
                        </div>
                    </spring:bind>
                </div>

                <h4>Содержание</h4>
                <div class="panel panel-default">

                    <div class="panel-heading">

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="topics">Рубрика</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="topics" name="topicId"
                                        <c:if test="${topics == null}">disabled</c:if> >
                                    <option value="">
                                        >--Выберете рубрику--<
                                    </option>
                                    <c:forEach var="topic" items="${topics}">
                                        <option value="${topic.id}">
                                                ${topic.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="authors">Автор</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="authors" name="authorId" disabled>
                                    <option value="">
                                        >--Выберете автора--<
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="articles">Статья</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="articles" disabled>
                                    <option value="">
                                        >--Выберете статью--<
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="text-right">
                            <input type="button" class="btn btn-success" value="Добавить"
                                   id="articleAddBtn" disabled>
                        </div>
                        <form:errors path="articlesId" cssClass="text-danger"/>
                    </div>

                    <ul class="list-group article-list">
                        <c:forEach var="article" items="${issueView.articles}">
                            <li class="list-group-item new-element">
                                <input type="hidden" name="articlesId"
                                       class="input-article" value="${article.id}"/>
                                <div class="row">
                                    <div class="col-xs-10 element-title">
                                        ${article.title}
                                    </div>

                                    <div class="col-xs-2 text-right">
                                        <span class="glyphicon glyphicon-trash delete-article"
                                              style="cursor: pointer"></span>
                                    </div>

                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                </div>

                <h4>Реклама</h4>
                <div class="panel panel-default">


                    <div class="panel-heading">

                        <div class="row">
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="advertisingPath">
                            </div>
                            <div class="col-sm-3">
                                <input type="button" class="btn btn-success form-control"
                                       value="Добавить" id="advertisingAddBtn" disabled>
                            </div>
                        </div>

                    </div>


                    <ul class="list-group advertising-list">
                        <c:forEach var="advertising" items="${issueView.advertisingPath}">
                            <li class="list-group-item new-element">
                                <input type="hidden" name="advertisingPath" value="${advertising}"/>
                                <div class="row">
                                    <div class="col-xs-10 element-title">
                                        <a href="${advertising}">
                                                ${advertising}
                                        </a>
                                    </div>

                                    <div class="col-xs-2 text-right">
                                        <span class="glyphicon glyphicon-trash delete-advertising" style="cursor: pointer"></span>
                                    </div>

                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                </div>

                <c:if test="${mode == 'edit'}">
                    <div class="form-group">
                        <div class="col-sm-offset-8 col-lg-offset-9 col-sm-4 col-lg-3">
                            <input type="submit" id="publishBtn" class="btn btn-info form-control" value="Опубликовать">
                            <form:hidden path="published"/>
                        </div>
                    </div>
                </c:if>

                <div class="text-right">
                    <a id="cancel" class="btn btn-default" href="${pageContext.request.contextPath}/issues">
                        Отменить
                    </a>
                    <input type="submit" class="btn btn-primary" value="Сохранить">
                </div>

            </form:form>

        </div>

    </div>

    <div id="elementTemplate" class="hidden">
        <li class="list-group-item new-element" style="display: none">
            <input type="hidden">
            <div class="row">
                <div class="col-xs-10 element-title"></div>
                <div class="col-xs-2 text-right">
                    <span class="glyphicon glyphicon-trash"
                          style="cursor: pointer"></span>
                </div>
            </div>
        </li>
    </div>

    <script src="${pageContext.request.contextPath}/resources/js/issue-form.js"></script>

</tag:layout>
