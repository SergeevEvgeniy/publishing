$(function () {

    var $formSelects = $("form select");
    var $articleAddBtn = $("#articleAddBtn");
    var $advertisingAddBtn = $("#advertisingAddBtn");
    var $template = $("#elementTemplate .new-element");
    var $articleList = $(".article-list");
    var $advertisingList = $(".advertising-list");

    $formSelects.on("change", function() {
        var $currentSelect = $(this);
        var nextSelectIndex = $formSelects
            .index($currentSelect) + 1;

        if (nextSelectIndex === $formSelects.length && $currentSelect.val()) {
            $articleAddBtn.prop("disabled", false);
            return;
        }
        $articleAddBtn.prop("disabled", true);

        var $nextSelect = $formSelects.eq(nextSelectIndex);

        $formSelects.slice(nextSelectIndex).each(function () {
            $(this).prop("disabled", true)
                .find("option")
                .not(":first-child")
                .remove();
        });

        if (!$currentSelect.val()) {
            return;
        }

        $.getJSON(getRestUrl(), function (data) {
            $.each(data, function (key, val) {
                var $option = $("<option></option>")
                    .val(key)
                    .text(val);
                $nextSelect.append($option);
                var nextSelectId = $nextSelect.attr("id");
                if (nextSelectId === "articles" && !isAvailableArticle(key)) {
                    $option.prop("hidden", true);
                }
            });
        });
        $nextSelect.prop("disabled", false);
    });

    $articleAddBtn.on("click", function () {
        var $articleSelect = $("#articles");
        var $articleOptions = $articleSelect.find("option");
        var $selectedOption = $articleOptions
            .filter(":selected")
            .prop("hidden", true);
        var articleId = $selectedOption.val();
        var articleTitle = $selectedOption.text();
        $articleOptions.first().prop("selected", true);
        if ($articleOptions.not("[hidden]").length === 1) {
            $("#authors option").first()
                .prop("selected", true);
            $articleSelect.prop("disabled", true);
        }
        $template
            .clone()
            .find("input")
            .val(articleId)
            .attr("name", "articlesId")
            .end()
            .find('.element-title')
            .text(articleTitle)
            .end()
            .find("span")
            .addClass("delete-article")
            .on("click", deleteArticle)
            .end()
            .appendTo($articleList)
            .slideDown("fast");
        $(this).prop("disabled", true);
        $("#publishing")
            .find("option")
            .not(":selected")
            .prop("hidden", true);
    });

    $advertisingAddBtn.on("click", function () {
        var path = $("#advertisingPath").val();
        var $link = $("<a></a>").attr("href", path)
            .text(path);
        $template
            .clone()
            .find("input")
            .val(path)
            .attr("name", "advertisingPath")
            .end()
            .find('.element-title')
            .append($link)
            .end()
            .find("span")
            .addClass("delete-advertising")
            .on("click", deleteAdvertising)
            .end()
            .appendTo($advertisingList)
            .slideDown("fast");
        $(this).prop("disabled", true);
        $("#advertisingPath").val("");
    });

    $(".delete-article").on("click", deleteArticle);

    $(".delete-advertising").on("click", deleteAdvertising);

    $("#advertisingPath").on("input", function () {
        if ($(this).val()) {
            $advertisingAddBtn.prop("disabled", false);
        } else {
            $advertisingAddBtn.prop("disabled", true);
        }
    });

    function deleteArticle() {
        var $listItem = $(this)
            .closest(".new-element");
        var articleId = $listItem
            .find("input")
            .val();
        $("#articles option")
            .filter("[value=" + articleId + "]")
            .prop("hidden", false);
        $listItem.slideUp("fast", function () {
            $listItem.remove();
        });
        if (!$articleList.has("li").length) {
            $("#publishing")
                .find("option")
                .not(":selected")
                .prop("hidden", false);
        }
    }

    function deleteAdvertising() {
        $(this).closest(".new-element")
            .slideUp("fast", function () {
                $(this).remove();
            });
    }

    function getRestUrl() {
        var url = APP_CONTEXT_PATH + "/issues";
        var params = $formSelects.serializeArray();
        return params.reduce(function (accumulator, currentItem) {
            accumulator += '/' + currentItem.name + '/' + currentItem.value;
            return accumulator;
        }, url);
    }

    function isAvailableArticle(articleId) {
        var $articleInput = $articleList.
            find("input[value=" + articleId + "]");
        return $articleInput.length === 0;
    }

});

