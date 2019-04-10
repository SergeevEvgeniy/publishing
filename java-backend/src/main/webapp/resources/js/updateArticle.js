$(function () {
    $("#publishingSelector").on("change", function (event) {
        var selectedValue = event.target.value;
        $.ajax({
            method: "GET",
            url: APP_CONTEXT_PATH + "/article/topicsByPublishing/" + selectedValue,
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
            url: APP_CONTEXT_PATH + "/article/employeesByPublishing/" + selectedValue,
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

    var currentCoauthors = $("#currentCoauthors")[0];
    var availableCoauthors = $("#availableCoauthors")[0];
    $("#addCoauthor").on("click", function () {
        var selected = availableCoauthors[availableCoauthors.selectedIndex];

        var li = document.createElement("li");
        li.classList.add("list-group-item");

        var div0 = document.createElement("div");
        div0.classList.add("row");
        li.appendChild(div0);

        var div1 = document.createElement("div");
        div1.classList.add("col-xs-10");
        div1.textContent = selected.textContent;
        div0.appendChild(div1);

        var div2 = document.createElement("div");
        div2.classList.add("col-xs-2", "text-right");
        div0.appendChild(div2);

        var span = document.createElement("span");
        span.classList.add("glyphicon", "glyphicon-trash");
        span.style.cursor = "pointer";
        div2.appendChild(span);


        currentCoauthors.append(li);
        availableCoauthors.options.remove(selected);
    });
});
