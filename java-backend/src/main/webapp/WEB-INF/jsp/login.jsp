<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">

        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <form:form class="form-signin" action="login" method="post" modelAttribute="loginForm">
                <h1>Please sign in</h1>
                <label>Username:</label>
                <form:input class="form-control" placeholder="Email address" type="email" path="email" required="required"/>
                <label>Password: </label>
                <form:input class="form-control" placeholder="Password" type="password" path="password" required="required"/>
                <input class="btn btn-primary btn-block" type="submit" value="Login">
                <p>${authResult.message}</p>
            </form:form>
        </div>
    </body>
</html>