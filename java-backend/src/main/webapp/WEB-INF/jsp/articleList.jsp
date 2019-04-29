<%--
  Created by IntelliJ IDEA.
  User: Denis Shubin
  Date: 27.02.2019
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout>
    <c:if test="${isJournalist}">
        <div class="text-right form-group">
            <a href="article/new" class="btn btn-success ">Добавить статью</a>
        </div>
    </c:if>

    <table class="table table-bordered table-hover ">
        <thead>
        <tr>
            <th>Название</th>
            <th>Журнал/газета</th>
            <th>Рубрика</th>
            <c:if test="${not isJournalist}">
                <th>Автор</th>
            </c:if>
            <th>Соавторы</th>
                <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${data}">
            <tr>
                <td><c:out value="${element.title}" /></td>
                <td>${element.publishing}</td>
                <td>${element.topic}</td>
                <c:if test="${not isJournalist}">
                    <td>${element.authorFullName}</td>
                </c:if>
                <td>
                    <ul class="list-unstyled">
                        <c:forEach items="${element.coauthors}" var="coauthor">
                            <li> ${coauthor}</li>
                        </c:forEach>
                    </ul>
                </td>
                    <td>
                        <c:if test="${(isJournalist and element.published) or isChiefEditor}">
                            <a href="article/get/${element.articleId}" type="submit"
                               class="glyphicon glyphicon-eye-open btn btn-sm btn-success"></a>
                        </c:if>

                        <c:if test="${isJournalist and not element.published}">
                            <a href="article/update/${element.articleId}"
                               class="glyphicon glyphicon-edit btn btn-sm btn-success"></a>
                        </c:if>

                        <c:if test="${isJournalist and not element.reviewed}">
                            <a href="article/delete/${element.articleId}" class="glyphicon glyphicon-trash btn btn-sm btn-danger"></a>
                        </c:if>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tag:layout>
