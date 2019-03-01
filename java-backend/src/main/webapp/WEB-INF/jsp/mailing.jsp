<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рассылка</title>
</head>
<body>
    <a href="mailing/settings">Settings</a>
    <span>Список рассылки</span>

    <table border="2px">
        <thead>
            <tr>
                <td>Журнал/газета</td>
                <td>Номер</td>
                <td>Дата рассылки</td>
                <td>Результат</td>
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
                    <td>
                        <c:out value="${mailingInfo.result}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
