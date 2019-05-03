<!--Button-->
<a href="#logoutModal" data-toggle="modal" data-target="#logoutModal">
    <i class="glyphicon glyphicon-off"></i>
</a>

<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4> Logout </h4>
            </div>
            <div class="modal-body">
                <fieldset>
                    <div class="control-group">
                        <div class="controls text">
                            You currently logged as '<span>${currentUser.name}</span>'.
                        </div>
                        <div class="controls text">
                            Are you sure you want to logout?
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="modal-footer">
                <form class="form-signin" action="${pageContext.request.contextPath}/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>                        
                    <button class="btn btn-default" data-dismiss="modal">Cancel</button>                        
                </form>
            </div>
        </div>
    </div>
</div>