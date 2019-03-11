<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Настройка</title>
        <jsp:include page="styleLinks.jsp"/>

        <script src="${pageContext.request.contextPath}/resources/js/libs/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mailing.js"></script>
    </head>
    <body>
        <jsp:include page="logoutHeader.jsp"/>
        <div class="container" style="width: 700px">
            <%--@elvariable id="subscribers" type="by.artezio.cloud.publishing.dto.Subscribers"--%>
            <form:form modelAttribute="subscribers" method="post" action="${pageContext.request.contextPath}/mailing/settings" class="form-horizontal">
                <div class="form-group">
                    <label for="mailingSelect" class="label-control col-lg-4 col-md-4 col-sm-4 h4">Журнал/газета</label>
                    <div class="col-lg-8 col-md-8 col-sm-8">
                        <select name="publishingId" id="mailingSelect" class="form-control">
                            <option value="">--- Выберите журнал/газету ---</option>
                            <c:forEach var="item" items="${publishingList}">
                                <option value="${item.id}" <c:if test="${id == item.id}">selected</c:if>>
                                    <c:out value="${item.title}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <h4 class="text-left">Список email-адресов</h4>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-lg-9 col-md-7">
                                <input id="emailAddress" type="email" class="form-control" placeholder="Новый email-адрес">
                                <div class="alert alert-danger hide incorrect-email-message"></div>
                            </div>
                            <div class="col-lg-3 col-md-5">
                                <button <c:if test="${id == null}">disabled</c:if> type="button" id="addBtn" class="btn btn-block btn-success">Добавить</button>
                                </div>
                            </div>
                        </div>
                        <ul id="emailList" class="list-group">
                        <c:forEach var="email" items="${emailList}">
                            <li class="list-group-item">
                                <input type="hidden" value="${email}" name="emails"/>
                                <div class="row">
                                    <div class="col-xs-10 added-email">${email}</div>
                                    <div class="col-xs-2 text-right">
                                        <span class="glyphicon glyphicon-trash delete-email" style="cursor: pointer"></span>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="text-right">
                    <a id="cancel" class="btn btn-default" href="${pageContext.request.contextPath}/mailing">Отменить</a>
                    <button id="save" type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </form:form>
            <div id="emailElementTemplate" class="hidden">
                <li class="list-group-item new-email-element">
                    <input type="hidden" name="emails" class="input-email">
                    <div class="row">
                        <div class="col-xs-10 added-email"></div>
                        <div class="col-xs-2 text-right">
                            <span class="glyphicon glyphicon-trash delete-email" style="cursor: pointer"></span>
                        </div>
                    </div>
                </li>
            </div>
        </div>
    </body>
</html>
