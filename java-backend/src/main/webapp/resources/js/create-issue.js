$(function () {

    onChangeSelectListener($("#publishing"), function (topics) {
        var $topics = $("#topics");
        $topics.html($("<option>Выберете рубрику</option>").val(""));
        var $options = $.map(topics, function (topic) {
            var $option = $("<option></option>").val(topic.id);
            return $option.text(topic.name);
        });
        $topics.append($options);
    });

    onChangeSelectListener($("#topics"), function (authors) {
        var $authors = $("#authors");
        $authors.html($("<option>Выберете автора</option>").val("empty"));
        var $options = $.map(authors, function (author) {
            var $option = $("<option></option>").val(author.id);
            return $option.text(author.lastName + " " + author.firstName + " " + author.middleName);
        });
        $authors.append($options);
    });

    onChangeSelectListener($("#authors"), function (articles) {
        var $articles = $("#articles");
        $articles.html($("<option>Выберете статью</option>").val("empty"));
        var $options = $.map(articles, function (article) {
            var $option = $("<option></option>").val(article.id);
            return $option.text(article.title);
        });
        $articles.append($options);
    });

    function onChangeSelectListener($select, handler) {
        $select.on("change", function () {
            var $formSelects = $("form select");
            var selectIndex = $formSelects.index($select);
            var nextSelectIndex = selectIndex + 1;
            $formSelects.slice(nextSelectIndex).each(function () {
                $(this).empty();
                this.disabled = true;
            });
            if (!$(this).val()) {
                return;
            }
            var nextSelect = $formSelects.get(nextSelectIndex);
            var requestParams = $formSelects.serializeArray();
            nextSelect.disabled = false;
            $.getJSON(createUrl(requestParams), handler);
        });

        function createUrl(requestParams) {
            var url = location.href.split("?")[0];
            for (var i = 0; i < requestParams.length; i++) {
                var param = requestParams[i];
                url += "/" + param.name + "/" + param.value;
            }
            return url;
        }

    }

});

