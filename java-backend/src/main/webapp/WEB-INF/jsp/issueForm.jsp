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
            <%--@elvariable id="issueInfo" type="by.artezio.cloud.publishing.dto.IssueInfo"--%>
            <form:form class="form-horizontal" method="${mode != 'view' ? 'POST' : 'GET'}"
                       action="${actionFromURL}" modelAttribute="issueInfo">

                <div class="form-group">
                    <label class="control-label col-sm-1" for="publishing">
                        Журналы
                    </label>
                    <div class="col-sm-offset-1 col-sm-10">
                        <c:if test="${mode == 'view'}">
                            <p class="form-control-static">
                                    ${issueInfo.publishingTitle}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <select class="form-control" id="publishing" name="publishingId">
                                <c:forEach var="pub" items="${publishing}">
                                    <option value="${pub.id}">
                                            ${pub.title}
                                    </option>
                                </c:forEach>
                            </select>
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
                                    ${issueInfo.number}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <input type="text" class="form-control" id="number" name="number"
                                   value="${issueInfo.number}"/>
                        </c:if>
                    </div>
                    <label class="control-label col-sm-offset-2 col-sm-2" for="date">
                        Дата
                    </label>
                    <div class="col-sm-3">
                        <c:if test="${mode == 'view'}">
                            <p class="form-control-static">
                                    ${issueInfo.localDate}
                            </p>
                        </c:if>
                        <c:if test="${mode != 'view'}">
                            <input type="date" class="form-control" id="date" name="localDate"
                                   value="${issueInfo.localDate}"/>
                        </c:if>
                    </div>
                </div>

                <h4>Содержание</h4>
                <div class="panel panel-default">

                    <div class="panel-heading"
                        <c:if test="${mode == 'view'}">
                            hidden
                        </c:if>
                    >

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
                            <input type="button" class="btn btn-success" value="Добавить">
                        </div>

                    </div>

                    <ul class="list-group">

                    </ul>

                </div>

                <h4>Реклама</h4>
                <div class="panel panel-default">

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

                    <ul class="list-group">

                    </ul>

                </div>

                <div class="form-group">
                    <div class="col-sm-offset-8 col-lg-offset-9 col-sm-4 col-lg-3">
                        <input type="button" class="btn btn-info form-control" value="Опубликовать">
                    </div>
                </div>

                <div class="text-right">
                    <input type="button" class="btn btn-secondary" value="Отменить">
                    <input type="button" class="btn btn-primary" value="Сохранить">
                </div>

            </form:form>

        </div>

    </div>

</tag:layout>
