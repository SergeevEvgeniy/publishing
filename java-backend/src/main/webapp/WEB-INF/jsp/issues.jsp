<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список номеров</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
</head>
<body>
    <div class="container">
            <h3 class="page-header">Номера</h3>
            <div class="text-right" style="margin-bottom: 10px">
                <a href="<c:url value="?new"/>" class="btn btn-success">
                    <span class="glyphicon glyphicon-plus"></span>Добавить
                </a>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Журнал/газета</th>
                        <th>Номер</th>
                        <th>Дата</th>
                        <th>Описание</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/libs/bootstrap.js"/>"></script>
</body>
</html>
