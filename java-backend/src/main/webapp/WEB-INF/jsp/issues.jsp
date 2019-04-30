<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout pageTitle="Номера">

    <h3 class="page-header">Номера</h3>
        <div class="text-right form-group">
            <a href="<c:url value="/issues/creationForm"/>" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span>Добавить
            </a>
        </div>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
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
                            <td>Статей: ${issueInfo.numberOfArticle}</td>
                            <td>
                                <c:if test="${!issueInfo.published}">
                                    <a class="btn btn-default btn-sm"
                                       href="${pageContext.request.contextPath}/issues/editionForm/issue/${issueInfo.issueId}">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    <button type="button" class="btn btn-default btn-sm delete-button">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                    <input type="hidden" value="${issueInfo.issueId}">
                                </c:if>
                                <c:if test="${issueInfo.published}">
                                    <a class="btn btn-default btn-sm"
                                       href="${pageContext.request.contextPath}/issues/viewingForm/issue/${issueInfo.issueId}">
                                        <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    <script src="${pageContext.request.contextPath}/resources/js/issues.js"></script>
</tag:layout>
