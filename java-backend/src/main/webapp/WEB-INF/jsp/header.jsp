<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Cloud-publishing</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/mailing">Mailing</a></li>
                <li><a href="${pageContext.request.contextPath}/article">Article</a></li>
                <li><a href="${pageContext.request.contextPath}/issues">Issues</a></li>
            </ul>
            <div class="nav navbar-right navbar-brand">
                <span>${currentUser.name}</span>
                <a href="${pageContext.request.contextPath}/logout" data-toggle="modal" data-target="#logoutModal">
                    <i class="glyphicon glyphicon-off"></i>
                </a>
            </div>
        </div>
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>
