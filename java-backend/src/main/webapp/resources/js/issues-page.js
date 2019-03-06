
$(function () {

    var newIssue = null;

    $("#publishingSelect").on("change", function () {
        var publishingId = $(this).find("option:selected").val();
        newIssue = {publishingId : publishingId};
            $.getJSON(location.href + "/publishing",
                {id : publishingId},
                function (data) {
                    var topicsOptions = $.map(data, function (topic) {
                        var $options = $("<option></option>").attr("value", topic.id);
                        return $options.text(topic.name);
                    });
                    $("#topicsSelect").html(topicsOptions);
                });
    });

});
