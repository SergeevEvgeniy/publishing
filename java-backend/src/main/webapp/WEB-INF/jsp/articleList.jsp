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
    <span class="glyphicon glyphicon-search"></span>
    <div class="container">
        <form:form modelAttribute="data">
            <div class="text-right form-group">
                <a href="update" class="btn btn-success ">Добавить статью</a>
            </div>
            <table class="table table-bordered table-hover ">
                <thead>
                    <tr>
                        <th>Название</th>
                        <th>Журнал/газета</th>
                        <th>Рубрика</th>
                        <th>Автор</th>
                        <th>Соавторы</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="element" items="${data}">
                        <tr>
                            <td>${element.title}</td>
                            <td>${element.publishing}</td>
                            <td>${element.topic}</td>
                            <td>${element.authorFullName}</td>
                            <td>
                                <ul>
                                    <c:forEach items="${element.coauthors}" var="coauthor">
                                        <li> ${coauthor}</li>
                                        </c:forEach>
                                </ul>
                            </td>
                            <td>

                                <a href="update/${element.articleId}">
                                    <span class="glyphicon glyphicon-edit btn btn-lg btn-success"> </span>
                                </a>

                                <span class="glyphicon glyphicon-trash btn btn-lg btn-danger"></span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form:form>
    </div>
</tag:layout>
