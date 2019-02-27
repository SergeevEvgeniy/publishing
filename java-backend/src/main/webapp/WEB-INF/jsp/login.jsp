<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <form action="login" method="post">
            Username: <input type="text" name="email" value="${email}">
            <br>
            Password: <input type="password" name="password" value="${password}">
            <br>
            <input type="submit" value="Login">
            <!--errors-->
        </form>
    </body>
</html>