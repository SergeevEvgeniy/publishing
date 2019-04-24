$(function () {

    $(".delete-button").on("click", function () {
        var $currentTableRow = $(this).closest("tr");
        var $inputForId = $(this).next("input");
        var issueId = $inputForId.val();
        $.ajax(APP_CONTEXT_PATH + "/issue/" + issueId, {
            type : "delete",
            success : function () {
                $currentTableRow.remove();
            }
        });
    });

});
