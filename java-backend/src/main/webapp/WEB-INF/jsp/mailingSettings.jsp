<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Настройка</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
</head>
<body>
<form>
    <label for="mailingSelect">
        Журнал
    </label>
    <select id="mailingSelect" onchange="location = this.value">
        <option
            <c:if test="${id != null}">
                value="../settings"
            </c:if>
        >
            --- Выберите журнал/газету ---
        </option>
        <c:forEach var="item" items="${publishingList}">
            <option
                <c:if test="${id == null}">
                    <c:out value="${'value=settings/'.concat(item.id)}"/>
                </c:if>
                <c:if test="${id != null}">
                    <c:out value="${'value=../settings/'.concat(item.id)}"/>
                </c:if>
                <c:if test="${id == item.id}">
                    <c:out value="selected"/>
                </c:if>
            >
                <c:out value="${item.title}"/>
            </option>
        </c:forEach>
    </select>
    <table class="table " border="2px">
        <tr>
            <td>Email адрес</td>
            <td>Кнопочка для удаления</td>
        </tr>
        <c:forEach var="email" items="${emailList}">
            <tr>
                <td>${email}</td>
                <%--TODO Сделать кнопочку--%>
                <td>КНОПОЧКА</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
