<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tag:layout>
    <h3 class="page-header">Форма добавления/просмотра/редактирования номеров</h3>
    <%--@elvariable id="issueForm" type="by.artezio.cloud.publishing.dto.IssueInfo"--%>
    <form:form class="form-horizontal"
               action="${pageContext.request.contextPath}/issues/issue">

        <div class="form-group row">
            <label class="control-label col-sm-offset-2 col-sm-1" for="publishing">
                Журналы
            </label>
            <div class="col-sm-7">
                <select class="form-control" id="publishing" name="publishingId">

                </select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-sm-offset-2 col-sm-1" for="number">
                Номер
            </label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="number" name="number"/>
            </div>
            <label class="control-label col-sm-1" for="date">Дата</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" id="date" name="date"/>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="form-group row">
                            <label class="control-label col-sm-2" for="topics">Рубрика</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="topics" name="topicId"></select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2" for="authors">Автор</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="authors" name="authorId" disabled></select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-sm-2" for="articles">Статья</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="articles" disabled></select>
                            </div>
                        </div>
                        <div class="text-right">
                            <button class="btn btn-success">
                                <span class="glyphicon glyphicon-plus"></span>Добавить
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form:form>


</tag:layout>
