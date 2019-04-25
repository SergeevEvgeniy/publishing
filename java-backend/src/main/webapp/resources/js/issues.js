$(function () {

    $(".delete-button").on("click", function () {
        var $currentTableRow = $(this).closest("tr");
        var issueId = $currentTableRow.find("input").val();
        $.ajax(APP_CONTEXT_PATH + "/issues/issue/" + issueId, {
            type : "delete",
            success : function () {
                $currentTableRow.hide("normal", function () {
                    $currentTableRow.remove();
                });
            }
        });
    });

});
