<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<tag:layout pageTitle="Номера">

    <c:if test="${result != null}">

        <div class="modal fade" id="resultModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <c:choose>
                        <c:when test="${result.status == 'created'}">
                            <c:set var="rowClass" value="success"/>
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">X</button>
                                <h3 class="modal-title">Успешное создание номера</h3>
                            </div>
                            <div class="modal-body">
                                <p>Номер: ${result.number}</p>
                                <p>Публикация: ${result.publishingTitle}</p>
                            </div>
                        </c:when>
                        <c:when test="${result.status == 'updated'}">
                            <c:set var="rowClass" value="warning"/>
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">X</button>
                                <h3 class="modal-title">Успешное обновление номера</h3>
                            </div>
                            <div class="modal-body">
                                <p>Номер: ${result.number}</p>
                                <p>Публикация: ${result.publishingTitle}</p>
                            </div>
                        </c:when>
                        <c:when test="${result.status == 'publicate'}">
                            <c:set var="rowClass" value="info"/>
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">X</button>
                                <h3 class="modal-title">Успешная публикация номера</h3>
                            </div>
                            <div class="modal-body">
                                <p>Номер: ${result.number}</p>
                                <p>Публикация: ${result.publishingTitle}</p>
                            </div>
                        </c:when>
                        <c:when test="${result.status == 'deleted'}">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">X</button>
                                <h3 class="modal-title">Успешное удаление номера</h3>
                            </div>
                            <div class="modal-body">
                                <p>Номер: ${result.number}</p>
                                <p>Публикация: ${result.publishingTitle}</p>
                            </div>
                        </c:when>
                    </c:choose>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal">Закрыть</button>
                    </div>
                </div>
            </div>
        </div>

    </c:if>

    <div class="modal fade" id="deleteIssueModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">X</button>
                    <h3 class="modal-title">Удаление номера</h3>
                </div>
                <div class="modal-body">
                    <form method="post" id="deleteIssueForm" action="<c:url value="/issues/delete"/>">
                        <p>Номер: <span id="issueNumber"></span></p>
                        <p>Публикация: <span id="issuePublishing"></span></p>
                        <input type="hidden" name="issueId" id="issueId">
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">Отменить</button>
                    <input type="submit" form="deleteIssueForm" value="Подтвердить" class="btn btn-primary">
                </div>
            </div>
        </div>
    </div>

    <h3 class="page-header">Номера</h3>
        <div class="text-right form-group">
            <a href="<c:url value="/issues/new"/>" class="btn btn-success">
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
                        <tr class="${result.issueId == issueInfo.issueId ? rowClass : ''}">
                            <td class="publishingTitle">${issueInfo.publishingTitle}</td>
                            <td class="number">${issueInfo.number}</td>
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
                                       href="<c:url value="/issues/edit/${issueInfo.issueId}"/>">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    <button type="button" class="btn btn-default btn-sm delete-button">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                    <input type="hidden" value="${issueInfo.issueId}">
                                </c:if>
                                <c:if test="${issueInfo.published}">
                                    <a class="btn btn-default btn-sm"
                                       href="<c:url value="/issues/view/${issueInfo.issueId}"/>">
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
