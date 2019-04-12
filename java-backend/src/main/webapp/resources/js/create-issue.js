$(function () {

    var $formSelects = $("form select");
    var $articleButton = $("#articleButton");
    var $articleElementTemplate = $("#articleElementTemplate .new-article-element");
    var $articleListElement = $(".article-list-element");

    $formSelects.on("change", function() {
        var $currentSelect = $(this);
        var currentSelectIndex = $formSelects
            .index($currentSelect);
        var nextSelectIndex = currentSelectIndex + 1;

        if (nextSelectIndex === $formSelects.length) {
            if ($currentSelect.val()) {
                $articleButton.prop("disabled", false);
            } else {
                $articleButton.prop("disabled", true);
            }
            return;
        }

        var nextSelect = $formSelects[nextSelectIndex];

        $formSelects.slice(nextSelectIndex)
            .prop("disabled", true)
            .find("option")
            .not(":first-child")
            .remove();

        if (!$currentSelect.val()) {
            return;
        }

        $.getJSON(getRestUrl(), function (data) {
            $.each(data, function (key, val) {
                var $option = $("<option></option>")
                    .val(key)
                    .text(val);
                $(nextSelect).append($option);
                var nextSelectId = $(nextSelect).attr("id");
                if (nextSelectId === "articles" && !isAvailableArticle(key)) {
                    $option.prop("hidden", true);
                }
            });
        });
        nextSelect.disabled = false;
    });

    $articleButton.on("click", function () {
        var $articleSelect = $("#articles");
        var $articleOptions = $articleSelect.find("option");
        var $authorOptions= $("#authors option");
        var $selectedOption = $articleOptions
            .filter(":selected");
        var articleId = $selectedOption.val();
        var articleTitle = $selectedOption.text();
        $selectedOption.prop("hidden", true);
        $articleOptions.first().prop("selected", true);
        if ($articleOptions.not("[hidden]").length === 1) {
            $authorOptions.first().prop("selected", true);
            $articleSelect.prop("disabled", true);
        }
        $articleElementTemplate
            .clone()
            .find(".input-article")
            .val(articleId)
            .end()
            .find('.added-article')
            .text(articleTitle)
            .end()
            .appendTo($articleListElement);
        $(this).prop("disabled", true);
        $("#publishingId")
            .find("option")
            .not("selected")
            .prop("hidden", true);
    });


    function getRestUrl() {
        var url = APP_CONTEXT_PATH + "/issues";
        var params = $formSelects.serializeArray();
        return params.reduce(function (accumulator, currentItem) {
            accumulator += '/' + currentItem.name + '/' + currentItem.value;
            return accumulator;
        }, url);
    }

    function isAvailableArticle(articleId) {
        var $articleInput = $articleListElement.
            find("input[value=" + articleId + "]");
        return $articleInput.length === 0;
    }

});

