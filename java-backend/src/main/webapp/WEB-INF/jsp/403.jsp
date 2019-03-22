<%-- 
    Document   : 403
    Created on : Mar 19, 2019, 2:12:12 PM
    Author     : Sergeev Evgeniy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag" %>
<!DOCTYPE html>
<tag:layout>
    <div class="alert alert-error">
        <div>
            <strong>Okay, Houston, we've had a problem here.</strong>
        </div>
        <ul>
            <li>Action not allowed. Try again please.</li>
        </ul>
    </div>
    <div class="form-horizontal">
        <div class="form-actions">
            <a class="btn" href="${pageContext.request.contextPath}/home">Back</a>
        </div>
    </div>
</tag:layout>