$(function () {
    $('#cancel').click(function () {
        // current link is //site/mailing/settings
        window.location = '../mailing';
        return false;
    });

    $('#mailingSelect').change(function () {
        // current link is //site/mailing/settings(?id=<id>, optionally)
        var id = $('#mailingSelect option:selected')[0].value;
        var url = '../mailing/settings';
        if (id !== '') {
            url = url + "?id=" + id;
        }
        window.location = url;
        return false;
    });

    var emailList = $('#emailList');
    console.log(emailList);

    emailList.click(function (event) {
        var target = event.target.closest('span');
        if (target === null) {
            return;
        }
        var li = target.closest('li');
        li.remove();
    });
});
