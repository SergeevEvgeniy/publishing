$(function () {
    var $reviewerSelector = $("#reviewerSelector");
    var $reviewContent = $("#reviewContent");
    var articleId = $("#articleId").val();
    $reviewerSelector.on("click", function () {
        var $selected = $reviewerSelector.val();

        if ($selected !== "none") {
            $.ajax({
                method: "GET",
                url: APP_CONTEXT_PATH + "/article/review/" + articleId + "/" + $selected,
                success: function (response) {
                    $reviewContent.text(response.content);
                }
            })
        }
    });
});
