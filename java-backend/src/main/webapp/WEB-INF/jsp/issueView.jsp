<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout pageTitle="Просмотр номера">
    <h3 class="page-header">Форма просмотра</h3>

    <div class="row">

        <div class="col-sm-offset-2 col-sm-8">

            <form class="form-horizontal">

                <div class="form-group">
                    <label class="control-label col-sm-1" for="publishing">
                        Журналы
                    </label>

                    <div class="col-sm-offset-1 col-sm-10">
                        <p class="form-control-static" id="publishing">
                                ${issueView.publishingTitle}
                        </p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-1" for="number">
                        Номер
                    </label>
                    <div class="col-sm-offset-1 col-sm-3">
                        <p class="form-control-static" id="number">
                                ${issueView.number}
                        </p>
                    </div>
                    <label class="control-label col-sm-offset-2 col-sm-2" for="date">
                        Дата
                    </label>
                    <div class="col-sm-3">
                        <p class="form-control-static" id="date">
                                ${issueView.localDate}
                        </p>
                    </div>
                </div>

                <h4>Содержание</h4>
                <ul class="list-group article-list">
                    <c:forEach var="article" items="${issueView.articles}">
                        <li class="list-group-item new-element">
                            <input type="hidden" name="articlesId"
                                   class="input-article" value="${article.id}"/>
                            <div class="row">
                                <div class="col-xs-10 element-title">
                                        ${article.title}
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <c:if test="${issueView.advertisingPath.size() != 0}">
                    <h4>Реклама</h4>
                    <ul class="list-group advertising-list">
                        <c:forEach var="advertising" items="${issueView.advertisingPath}">
                            <li class="list-group-item new-element">
                                <input type="hidden" name="advertisingPath" value="${advertising}"/>
                                <div class="row">
                                    <div class="col-xs-10 element-title">
                                        <a href="${advertising}">
                                                ${advertising}
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

             </form>

        </div>

    </div>

</tag:layout>
