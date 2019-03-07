<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <form:form action="login" method="post" modelAttribute="loginForm">
            Username: <form:input type="email" path="email" required="required"/>
            <br>
            Password: <form:input type="password" path="password" required="required"/>
            <br>
            <input type="submit" value="Login">
            <p>${authResult.message}</p>
        </form:form>
    </body>
</html>