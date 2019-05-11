$(function () {

    var $articleAddBtn = $("#articleAddBtn");
    var $advertisingAddBtn = $("#advertisingAddBtn");

    var $template = $("#elementTemplate .new-element");

    var $articleList = $(".article-list");
    var $advertisingList = $(".advertising-list");

    var $advertisingInput = $("#advertisingPath");

    var $formSelects = $("form select");
    var $publishing = $formSelects.eq(0);
    var $topics = $formSelects.eq(1);
    var $authors = $formSelects.eq(2);
    var $articles = $formSelects.eq(3);

    if ($articleList.has("li").length) {
        $publishing.find(":not(:selected)")
            .prop("hidden", true);
    }

    $publishing.on("change", function () {
        onChangeSelect($publishing, function (topics) {
            $.each(topics, function (i, topic) {
                $("<option></option>")
                    .val(topic.id)
                    .text(topic.name)
                    .appendTo($topics);
            });
        });
    });

    $topics.on("change", function () {
        onChangeSelect($topics, function (authors) {
            $.each(authors, function (i, author) {
                $("<option></option>")
                    .val(author.id)
                    .text(author.lastName)
                    .appendTo($authors);
            });
        });
    });

    $authors.on("change", function () {
        onChangeSelect($authors, function (articles) {
            $.each(articles, function (i, article) {
                var $option = $("<option></option>")
                    .val(article.id)
                    .text(article.title)
                    .appendTo($articles);
                var $articleInput = $articleList
                    .find("input[value=" + article.id + "]");
                if ($articleInput.length) {
                    $option.prop("hidden", true);
                }
            });
        });
    });

    $articles.on("change", function () {
        if ($articles.val()) {
            $articleAddBtn.prop("disabled", false);
        } else {
            $articleAddBtn.prop("disabled", true);
        }
    });

    function onChangeSelect($select, getJSONHandler) {

        var nextIndex = $formSelects.index($select) + 1;

        if (!$articleAddBtn.prop("disabled")) {
            $articleAddBtn.prop("disabled", true);
        }

        $formSelects.slice(nextIndex).each(function () {
            $(this).prop("disabled", true)
                .find("option")
                .not(":first-child")
                .remove();
        });
        if (!$select.val()) {
            return;
        }

        var url = APP_CONTEXT_PATH + "/issues/"
            + $formSelects[nextIndex].id;
        var params = $formSelects.serializeArray();
        $.getJSON(url, params, getJSONHandler);

        $formSelects.eq(nextIndex)
            .prop("disabled", false);
    }

    $articleAddBtn.on("click", function () {
        var $options = $articles.find("option");
        var $selectedOption = $options
            .filter(":selected")
            .prop("hidden", true);
        var articleId = $selectedOption.val();
        var articleTitle = $selectedOption.text();
        $options.first().prop("selected", true);
        if ($options.not("[hidden]").length === 1) {
            $authors.children().first()
                .prop("selected", true);
            $articles.prop("disabled", true);
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
            .end()
            .appendTo($articleList)
            .slideDown("fast");
        $articleAddBtn.prop("disabled", true);
        $publishing
            .find("option")
            .not(":selected")
            .prop("hidden", true);
    });

    $advertisingAddBtn.on("click", function () {
        var path = $advertisingInput.val();
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
            .end()
            .appendTo($advertisingList)
            .slideDown("fast");
        $advertisingAddBtn.prop("disabled", true);
        $advertisingInput.val("");
    });

    $articleList.on("click", ".delete-article", function () {
        var $listItem = $(this)
            .closest(".new-element");
        var articleId = $listItem
            .find("input")
            .val();
        $articles.find("option[value=" + articleId + "]")
            .prop("hidden", false);
        $listItem.slideUp("fast", function () {
            $listItem.remove();
            if (!$articleList.children().length) {
                $publishing
                    .find("option")
                    .prop("hidden", false);
            }
        });
    });

    $advertisingList.on("click", ".delete-advertising", function () {
        $(this).closest(".new-element")
            .slideUp("fast", function () {
                $(this).remove();
            });
    });

    $advertisingInput.on("input", function () {
        $advertisingAddBtn.prop("disabled", !$advertisingInput.val());
    });

    $("#publishBtn").on("click", function () {
        $("#published").val("true");
    });

});

