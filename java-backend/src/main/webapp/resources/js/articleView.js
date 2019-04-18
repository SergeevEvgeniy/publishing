$(function () {
    var $reviewerSelector = $("#reviewerSelector");
    var $reviewContent = $("#reviewContent");
    var articleId = document.getElementById("articleId").value;
    $reviewerSelector.on("click", function (event) {
        var selected = $reviewerSelector[$reviewerSelector.selectedIndex].value;

        if (selected !== "none") {
            $.ajax({
                method: "GET",
                url: APP_CONTEXT_PATH + "/article/review/" + articleId + "/" + selected,
                success: function (response) {
                    console.log(response);
                    $reviewContent.text(response.content);
                }
            })
        }
    });
});
