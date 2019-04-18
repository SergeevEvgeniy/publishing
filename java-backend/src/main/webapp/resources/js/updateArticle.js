$(function () {

    var $currentCoauthorTemplate = $("#currentCoauthorTemplate li");
    var $availableCoauthors = $("#availableCoauthors");
    var $hiddenPublishingId = $("#hiddenPublishingId");
    var $hiddenTopicId = $("#hiddenTopicId");
    var $currentCoauthors = $("#currentCoauthors");
    var $addedCurrentCoauthors = $("#addedCurrentCoauthors");
    var $topicSelector = $("#topicSelector");
    var $publishingSelector = $("#publishingSelector");

    $publishingSelector.on("change", function (event) {
        var selectedValue = event.target.value;

        $availableCoauthors.empty();
        $topicSelector.empty();
        $currentCoauthors.empty();
        $addedCurrentCoauthors.empty();

        if (selectedValue === "none") {
            $topicSelector.attr("disabled", "disabled");
            return;
        }
        $topicSelector.removeAttr("disabled");
        $hiddenPublishingId.val(selectedValue);
        $.ajax({
            method: "GET",
            url: APP_CONTEXT_PATH + "/article/topicsByPublishing/" + selectedValue,
            success: function (response) {
                $("<option>")
                    .val("none")
                    .text("---Выберите рубрику---")
                    .appendTo($topicSelector);

                response.forEach(function (obj) {
                    $("<option>")
                        .val(obj.id)
                        .text(obj.name)
                        .appendTo($topicSelector);
                });
            },
            error: function (response) {
                console.log(response);
            }
        });

        $.ajax({
            method: "GET",
            url: APP_CONTEXT_PATH + "/article/employeesByPublishing/" + selectedValue,
            success: function (response) {
                $availableCoauthors.empty();
                response.forEach(function (elem) {
                    $('<option>')
                        .val(elem.id)
                        .text(elem.shortFullName)
                        .appendTo($availableCoauthors);
                });
            },
            error: function (response) {
                console.log(response);
            }
        });
    });

    $topicSelector.on("change", function (event) {
        var $selected = $topicSelector.find("option:selected");
        $hiddenTopicId.val($selected.val());
    });

    $("#addCoauthor").on("click", function () {
        var $selected = $availableCoauthors.find("option:selected");
        if ($selected.length === 0) {
            return;
        }

        $currentCoauthorTemplate
            .clone()
            .data('employeeId', $selected.val())
            .data('employeeName', $selected.text())
            .find("input")
            .val($selected.val())
            .end()
            .find(".new-full-name")
            .text($selected.text())
            .end()
            .appendTo($currentCoauthors);

        $selected.remove();
    });

    $currentCoauthors.on("click", '.delete-coauthor', function () {
        var $deleteButton = $(this);
        var $li = $deleteButton.closest("li");

        $('<option>')
            .val($li.data('employeeId'))
            .text($li.data('employeeName'))
            .appendTo($availableCoauthors);
        $li.remove();
    });

    var $reviewContent = $("#reviewContent");
    var articleId = $("#articleId").text();
    $("#reviewerSelector").on("click", function (event) {
        var selectedValue = event.target.value;

        if (!selectedValue) {
            $reviewContent.empty();
            return;
        }

        $.ajax({
            method: "GET",
            url: APP_CONTEXT_PATH + "/article/review/" + articleId + "/" + selectedValue,
            success: function (response) {
                $reviewContent.text(response.content);
            }
        });
    });

});
