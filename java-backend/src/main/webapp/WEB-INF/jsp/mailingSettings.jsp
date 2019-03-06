<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Настройка</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resources/js/libs/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mailing.js"></script>
</head>
<body>

<div class="container" style="width: 700px">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="mailingSelect" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Журнал/газета</label>
            <div class="col-lg-8 col-md-8 col-sm-8">
                <select id="mailingSelect" class="form-control">
                    <option value="">--- Выберите журнал/газету ---</option>
                    <c:forEach var="item" items="${publishingList}">
                        <option value="${item.id}" <c:if test="${id == item.id}">selected</c:if>>
                            <c:out value="${item.title}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <h4 class="text-left">Список email-адресов</h4>
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-lg-9 col-md-7">
                        <input id="emailAddress" type="email" class="form-control" placeholder="Новый email-адрес">
                    </div>
                    <div class="col-lg-3 col-md-5">
                        <div id="addBtn" class="btn btn-block btn-success">Добавить</div>
                    </div>
                </div>
            </div>
            <ul id="emailList" class="list-group">
                <c:forEach var="email" items="${emailList}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-xs-10">${email}</div>
                            <div class="col-xs-2 text-right">
                                <span class="glyphicon glyphicon-trash" style="cursor: pointer"></span>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="text-right">
            <button id="cancel" class="btn btn-default">Отменить</button>
            <button id="save" type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>
</div>
</body>
</html>
