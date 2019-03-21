$(function () {
    $("#publishingSelector").on("change", function (event) {
        var selectedValue = event.target.value;
        $.ajax({
            method: "GET",
            url: "publishing/" + selectedValue,
            success: function (response) {
                var topicSelector = $("#topicSelector");
                topicSelector.empty();
                response.forEach(function (obj) {
                    var option = document.createElement("option");
                    option.value = obj.id;
                    option.textContent = obj.name;
                    topicSelector.append(option);
                });
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
});
