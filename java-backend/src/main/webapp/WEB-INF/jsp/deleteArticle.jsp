<%--
  Created by IntelliJ IDEA.
  User: Denis Shubin
  Date: 23.04.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tag:layout>
    <div class="col-sm-offset-2 col-sm-8">
        <div class="row">
            <div class="col-12-sm">
                <c:if test="${articleExists}">
                    <form:form method="post">
                        <div class="row">
                            <span class="h1">
                                Вы действительно хотите удалить статью "<c:out value="${article.articleName}"/>"?
                            </span>
                        </div>

                        <div class="row">
                            <input class="btn btn-success" type="submit" value="Да"/>
                            <a class="btn btn-danger"
                               href="${pageContext.request.contextPath}/article">Нет</a>
                        </div>
                    </form:form>
                </c:if>
                <c:if test="${not articleExists}">
                    <div class="row">
                        <span class="h1">Данная статья не существует!</span>
                    </div>
                    <div class="row">
                        <a class="btn btn-danger btn-block" href="${pageContext.request.contextPath}/article">Назад</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</tag:layout>
