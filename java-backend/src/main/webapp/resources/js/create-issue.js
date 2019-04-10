$(function () {

    var $formSelects = $("form select");
    $formSelects.on("change", function() {
        var $currentSelect = $(this);
        var currentSelectIndex = $formSelects.index($currentSelect);
        var nextIndex = currentSelectIndex + 1;
        if (nextIndex === $formSelects.length) {
            return;
        }
        for (var i = nextIndex; i < $formSelects.length; i++) {
            var select = $formSelects[i];
            select.disabled = true;
            $(select).find("option")
                .not(":first")
                .remove();
        }
        if (!$currentSelect.val()) {
            return;
        }
        var nextSelect = $formSelects[nextIndex];
        $.getJSON(getRestUrl(), function (data) {
            $.each(data, function (key, val) {
                var option = $("<option></option>")
                    .val(key)
                    .text(val);
                $(nextSelect).append(option);
            });
        });
        nextSelect.disabled = false;
    });

    function getRestUrl() {
        var url = location.href.split("?")[0];
        var params = $formSelects.serializeArray();
        return params.reduce(function (accumulator, currentItem) {
            accumulator += '/' + currentItem.name + '/' + currentItem.value;
            return accumulator;
        }, url);
    }



});

