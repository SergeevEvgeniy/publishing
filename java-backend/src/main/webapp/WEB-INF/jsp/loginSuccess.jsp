<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>Login Success Page</title>
    </head>
    <body>
        <h3>Login successful. 
            <br>Hi ${user}!
            <br>Your Session ID=${sessionID}
        </h3>
        <br>
        <form action="logout" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>