$(function () {

    $("#resultModal").modal("show");

    $(".delete-button").on("click", function () {
        var $issueTableRow = $(this).closest("tr");
        $("#issueNumber").text($issueTableRow.find(".number").text());
        $("#issuePublishing").text($issueTableRow.find(".publishingTitle").text());
        $("#issueId").val($issueTableRow.find("input").val());
        $("#deleteIssueModal").modal("show");
    });

});
