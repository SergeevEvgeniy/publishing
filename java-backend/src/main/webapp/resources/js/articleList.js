$(function () {
    $("button[name='deleteArticle']").on("click", function (event) {
        var id = event.target.value;
        $.ajax({
            method: "DELETE",
            url: APP_CONTEXT_PATH + "/article/delete/" + id,
            success: function () {
                location.reload();
            },
            error: function () {
                location.reload();
            }
        });
    });
});
