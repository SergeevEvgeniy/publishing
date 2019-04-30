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
<tag:layout pageTitle="Просмотр номера">
    <script type="text/javascript" src="<c:url value="/resources/js/articleView.js"/>"></script>
    <div class="form-horizontal center-block">
        <input type="hidden" value="${model.articleId}" id="articleId">

        <div class="form-group">
            <label class="col-sm-4 h4">Журнал</label>
            <div class="col-sm-8">
                <span class="form-control-static"><c:out value="${model.publishingName}"/></span>
            </div>
        </div>

        <div class="form-group">
            <label class="label-control col-sm-4 h4">Рубрика</label>
            <div class="col-sm-8">
                <span class="form-control-static"><c:out value="${model.topicName}"/></span>
            </div>
        </div>

        <div class="form-group">
            <label class="label-control col-sm-4 h4">Название</label>
            <div class="col-sm-8">
                <span class="form-control-static"><c:out value="${model.articleName}"/></span>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label class="h4">Содержание</label>
            </div>
            <div class="col-sm-12">
                <span class="form-control-static"><c:out value="${model.content}"/></span>
            </div>
        </div>

        <c:if test="${model.coauthors.size() != 0}">
            <div class="form-group">
                <label class="label-control col-sm-4 h4">Соавторы</label>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <ul id="currentCoauthors" class="list-group">
                            <c:forEach var="currentCoauthor" items="${model.coauthors}">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-sm-10">
                                                ${currentCoauthor.firstName}
                                                ${currentCoauthor.lastName}
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${model.reviews.size() != 0}">
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
                                            <option value="none">---Выберите рецензента---</option>
                                            <c:forEach var="element" items="${model.reviews}">
                                                <option
                                                    value="${element.key.id}">${element.key.lastName} ${element.key.firstName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <span id="reviewContent" class="form-control-static no-resize"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <div class="text-right">
            <a href="${pageContext.request.contextPath}/article" class="btn btn-default">
                Назад
            </a>
        </div>
    </div>
</tag:layout>
