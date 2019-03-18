<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма добавления/редактирования номеров</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
</head>
<body>
    <div class="container">
        <h3 class="page-header">Форма добавления/просмотра/редактирования номеров</h3>
        <%--@elvariable id="issueForm" type="by.artezio.cloud.publishing.dto.IssueInfo"--%>
        <form:form class="form-horizontal" method="${method}"
                   action="${pageContext.request.contextPath}/issues/issue" modelAttribute="issueForm">

            <div class="form-group row">
                <label class="control-label col-sm-offset-2 col-sm-1" for="publishing">
                    Журналы
                </label>
                <div class="col-sm-7">
                    <select class="form-control" id="publishing" name="publishingId">
                        <option value="">Выберете журнал</option>
                        <c:forEach var="p" items="${publishing}">
                            <option value="${p.id}">${p.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-offset-2 col-sm-1" for="number">
                    Номер
                </label>
                <div class="col-sm-3">
                    <c:if test="${method == 'POST'}">
                        <input type="text" class="form-control" id="number" name="number"/>
                    </c:if>
                    <c:if test="${method == 'PUT'}">
                        <input type="text" class="form-control" id="number" name="number" value="${issueForm.number}"/>
                    </c:if>
                </div>
                <label class="control-label col-sm-1" for="date">Дата</label>
                <div class="col-sm-3">
                    <c:if test="${method == 'POST'}">
                        <input type="date" class="form-control" id="date" name="date"/>
                    </c:if>
                    <c:if test="${method == 'PUT'}">
                        <input type="date" class="form-control" id="date" name="date" value="${issueForm.localDate}"/>
                    </c:if>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="form-group row">
                                <label class="control-label col-sm-2" for="topics">Рубрика</label>
                                <div class="col-sm-10">
                                    <c:if test="${method == 'POST'}">
                                        <select class="form-control" id="topics" name="topicId" disabled></select>
                                    </c:if>
                                    <c:if test="${method == 'PUT'}">
                                        <select class="form-control" id="topics" name="topicId"></select>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-sm-2" for="authors">Автор</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="authors" name="authorId" disabled></select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-sm-2" for="articles">Статья</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="articles" disabled></select>
                                </div>
                            </div>
                            <div class="text-right">
                                <button class="btn btn-success">
                                    <span class="glyphicon glyphicon-plus"></span>Добавить
                                </button>
                            </div>
                        </div>
                        <div class="panel-body"></div>
                    </div>
                </div>
            </div>

        </form:form>
    </div>

    <script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/libs/bootstrap.js"/>"></script>
    <c:choose>
        <c:when test="${method == 'POST'}">
            <script type="text/javascript" src="<c:url value="/resources/js/create-issue.js"/>"></script>
        </c:when>
    </c:choose>
</body>
</html>
