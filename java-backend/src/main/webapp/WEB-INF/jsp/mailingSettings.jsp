<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Настройка</title>
    <script src="${pageContext.request.contextPath}/resources/js/libs/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/libs/bootstrap.js"></script>
</head>
<body>
    <form>
        <label for="mailingSelect">
            Журнал
        </label>
        <select id="mailingSelect" onchange="location = this.value">
            <option value="NONE"> --- Выберите журнал/газету --- </option>
            <c:forEach var="item" items="${publishingList}">
                <option data-id="${item.getId()}" value="mailing/settings/${item.getId()}">
                        ${item.getTitle()}
                </option>
            </c:forEach>
        </select>
    </form>
    <span>Список рассылки</span>
    <table>
        <tr>
            <td>Журнал/газета</td>
            <td>Журнал/газета</td>
            <td>Журнал/газета</td>
            <td>Результат</td>
        </tr>
    </table>
</body>
</html>
