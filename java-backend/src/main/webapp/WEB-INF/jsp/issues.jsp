<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout>
    <fmt:setLocale value="ru-RU"/>
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
                <c:forEach var="issueInfo" items="${issueInfoList}">
                    <tr>
                        <td>${issueInfo.publishingTitle}</td>
                        <td>${issueInfo.number}</td>
                        <td>
                            <fmt:parseDate var="date" value="${issueInfo.localDate}" type="date" pattern="yyyy-MM-dd"/>
                            <time datetime="${issueInfo.localDate}">
                                <fmt:formatDate value="${date}" pattern="dd MMMM yyyy" type="date"/>
                            </time>
                        </td>
                        <td>Будущее описание</td>
                        <td>Будущие кнопки</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</tag:layout>
