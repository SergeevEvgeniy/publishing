$(function () {
    $('#cancel').click(function () {
        // current link is //site/mailing/settings
        var url = '/cloud_publishing_war_exploded/mailing';
        window.location = url;
        return false;
    });

    $('#mailingSelect').change(function (event) {
        // current link is //site/mailing/settings(?id=<id>, optionally)
        var id = $('#mailingSelect option:selected')[0].value;
        var url = '/cloud_publishing_war_exploded/mailing/settings/';
        if (id !== '') {
            url = url + "?id=" + id;
        }
        window.location = url;
        return false;
    });
});
