<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout>

    <h3 class="page-header">Номера</h3>
    <form>
        <div class="text-right form-group">
            <a href="<c:url value="?mode=create"/>" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span>Добавить
            </a>
        </div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th width="20%">Журнал/газета</th>
                    <th width="10%">Номер</th>
                    <th width="20%">Дата</th>
                    <th width="40%">Описание</th>
                    <th width="10%"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="issueForm" items="${issueFormList}">
                    <tr>
                        <td>${issueForm.publishingTitle}</td>
                        <td>${issueForm.number}</td>
                        <td>
                            <fmt:parseDate var="date" value="${issueForm.localDate}" type="date" pattern="yyyy-MM-dd"/>
                            <time datetime="${issueForm.localDate}">
                                <fmt:formatDate value="${date}" pattern="dd MMMM yyyy" type="date"/>
                            </time>
                        </td>
                        <td>Будущее описание</td>
                        <td>
                            <c:if test="${!issueForm.published}">
                                <a class="btn btn-default btn-sm"
                                   href="${pageContext.request.contextPath}/issues?mode=edit&id=${issueForm.issueId}">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>
                                <button type="button" class="btn btn-default btn-sm delete-button">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </button>
                                <input type="hidden" value="${issueForm.issueId}">
                            </c:if>
                            <c:if test="${issueForm.published}">
                                <a class="btn btn-default btn-sm"
                                   href="${pageContext.request.contextPath}/issues?mode=view&id=${issueForm.issueId}">
                                    <span class="glyphicon glyphicon-eye-open"></span>
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/issues.js"></script>
</tag:layout>
