$(function () {

    $(".delete-button").on("click", function () {
        var $currentTableRow = $(this).closest("tr");
        var $inputForId = $(this).next("input");
        var issueId = $inputForId.val();
        $.ajax(location.href + "/issue/" + issueId, {
            type : "delete",
            success : function () {
                $currentTableRow.remove();
            }
        });
    });

});
