$(function () {
    $("#publishingSelector").on("change", function (event) {
        var selectedValue = event.target.value;
        $.ajax({
            method: "GET",
            url: "topicsByPublishing/" + selectedValue,
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
        $.ajax({
            method: "GET",
            url: "employeesByPublishing/" + selectedValue,
            success: function (response) {
                var coauthorSelector = $("#availableCoauthors");
                coauthorSelector.empty();
                response.forEach(function (elem) {
                    var option = document.createElement("option");
                    option.value = elem.id;
                    option.textContent = getShortName(elem);
                    coauthorSelector.append(option);
                });

                function getShortName(elem) {
                    return elem.lastName + " "
                        + elem.firstName.charAt(0) + "."
                        + elem.middleName.charAt(0) + ".";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
});
