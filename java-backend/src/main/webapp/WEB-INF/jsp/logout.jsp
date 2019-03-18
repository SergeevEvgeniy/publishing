<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">

        <title>Logout Page</title>
        <meta charset="UTF-8">        
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="${pageContext.request.contextPath}/logout" method="post">
                <header>
                    <h1>
                        Logout
                    </h1>
                </header>
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
                        <a class="btn" onclick="javascript:history.back(); return false;">Back</a>
                    </div>
                </fieldset>
            </form>
        </div> 
    </body>
</html>