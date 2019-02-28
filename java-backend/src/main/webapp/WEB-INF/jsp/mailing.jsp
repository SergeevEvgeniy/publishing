<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рассылка</title>
</head>
<body>
    <a href="mailing/settings">Settings</a>
    <span>Список рассылки</span>
    <table>
        <tr>
            <td>Журнал/газета</td>
            <td>Номер</td>
            <td>Дата рассылки</td>
            <td>Результат</td>
        </tr>
        <c:forEach var="mailing" items="${mailingList}">
            <tr>
                <td>???</td>
                <td>???</td>
                <td>???</td>
                <td>???</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
