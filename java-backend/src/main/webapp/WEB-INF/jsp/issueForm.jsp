<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма добавления/редактирования номеров</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
</head>
<body>
    <div class="container">
        <c:url value="#" var="issuesURL"/>
        <sf:form class="form-horizontal col-sm-offset-1" method="post" action="${issuesURL}"
                 modelAttribute="issueFormDto">
            <div class="form-group">
                <label class="control-label col-sm-2" for="publishing">Журналы</label>
                <div class="col-sm-8">
                    <sf:select class="form-control" id="publishing" path="publishingId">
                        <option value="empty">Выберете журнал</option>
                        <c:forEach var="p" items="${publishing}">
                            <sf:option value="${p.id}">${p.title}</sf:option>
                        </c:forEach>
                    </sf:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="numbers">Номер</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="numbers"/>
                </div>
                <label class="control-label col-sm-2" for="date">Дата</label>
                <div class="col-sm-3">
                    <input type="date" class="form-control" id="date"/>
                </div>
            </div>
            <fieldset id="content">
                <legend>Содержание</legend>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="topics">Рубрика</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="topics" name="topicId" disabled></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="authors">Автор</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="authors" name="authorId" disabled></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="articles">Статья</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="articles" disabled></select>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>

    <script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/libs/bootstrap.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/issue-page.js"/>"></script>
</body>
</html>
