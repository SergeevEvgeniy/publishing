<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout pageTitle="Рассылки">
    <div class="row panel-group">
        <div class="col-lg-6 col-md-8">
            <span class="h2">Список рассылки</span>
        </div>
        <div class="col-lg-6 col-md-4 text-right">
            <a href="mailing/settings" class="btn btn-success">
                <span class="glyphicon glyphicon-cog"></span>
                Settings
            </a>
        </div>
    </div>

    <table class="table table-condensed table-bordered">
        <thead class="thead-light">
            <tr>
                <th width="25%">Журнал/газета</th>
                <th width="25%">Номер</th>
                <th width="25%">Дата рассылки</th>
                <th width="25%">Результат</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="mailingInfo" items="${mailingInfoList}">
                <tr>
                    <td>
                        <c:out value="${mailingInfo.publishingTitle}"/>
                    </td>
                    <td>
                        <c:out value="${mailingInfo.issueNumber}"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${mailingInfo.date}" dateStyle="long" pattern="d MMMM YYYY"/>
                    </td>
                    <td style="color: ${mailingInfo.success ? 'green' : 'red'}">
                        <c:out value="${mailingInfo.result}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="row panel-group">
        <a class="btn btn-info" href="${pageContext.request.contextPath}/mailing/do_it">Сделать рассылку</a>
    </div>
</tag:layout>
