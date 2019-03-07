<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
        <title>Logout Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container" style="width: 500px">
            <header>
                <h1>
                    Logout
                </h1>
            </header>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/logout" method="post">
                <fieldset>
                    <div class="control-group">
                        <div class="controls text">
                            You currently logged as '<span>${user.name}</span>'.
                        </div>
                        <div class="controls text">
                            Are you sure you want to logout?
                        </div>
                    </div>
                    <div class="form-actions">
                        <button id="logoutButton" class="btn btn-danger" type="submit">Logout</button>
                        <a class="btn" href="${pageContext.request.contextPath}/home">Back</a>
                    </div>
                </fieldset>
            </form>
        </div> 
    </body>
</html>