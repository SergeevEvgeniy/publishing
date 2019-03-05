<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рассылка</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
</head>
<body>

<div class="container">
    <span class="h3">Список рассылки</span>
    <button type="button" class="btn btn-lg btn-success pull-right">
        <a href="mailing/settings">
            <span class="glyphicon glyphicon-cog"/>
            Settings
        </a>
    </button>
    <table class="table table-condensed table-bordered">
            <thead class="thead-light">
                <tr>
                    <th class="col-sm-2">Журнал/газета</th>
                    <th class="col-sm-2">Номер</th>
                    <th class="col-sm-2">Дата рассылки</th>
                    <th class="col-sm-12">Результат</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mailingInfo" items="${mailingInfoList}">
                    <tr>
                        <td>
                            <%--TODO исправить на 'mailing.publishingTitle'--%>
                            <c:out value="${mailingInfo.publishingId}"/>
                        </td>
                        <td>
                            <%--TODO исправить на 'mailing.issueNumber'--%>
                            <c:out value="${mailingInfo.issueId}"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${mailingInfo.date}" dateStyle="long" pattern="d MMMM YYYY"/>
                        </td>
                        <td style="color: ${mailingInfo.result.equals('Успешно') ? 'green' : 'red'}">
                            <c:out value="${mailingInfo.result}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>
</body>
</html>
