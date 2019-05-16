<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<tag:layout pageTitle="Settings">
    <h3 class="page-header">
        Настройки
    </h3>
    <form action="${pageContext.request.contextPath}/settings" method="post">
        <fieldset>
            <div>
                <label>Количество записей на странице</label>
                <select>
                    <option>Default</option>
                </select>
            </div>
            <div>
                <label>Домашняя страница</label>
                <select>
                    <option>Default</option>
                </select>
            </div>
            <div class="form-actions">
                <button class="btn btn-success" type="submit">Save</button>
                <a class="btn btn-danger" href="${pageContext.request.getHeader("referer")}">Back</a>
            </div>
        </fieldset>
    </form>
</tag:layout>