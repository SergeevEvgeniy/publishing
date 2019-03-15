$(function () {

    var $emailListElement = $('#emailList');
    var emailReqexp = /^[\w%_\-.\d]+@[\w.\-]+.[A-Za-z]{2,6}$/;
    var $emailElementTemplate = $('#emailElementTemplate .new-email-element');

    $('#mailingSelect').on('change', function () {
        // current link is //site/mailing/settings(?id=<id>, optionally)
        var id = $(this).val();
        var url = APP_CONTEXT_PATH + '/mailing/settings';
        if (id !== '') {
            url = url + "?id=" + id;
        }
        window.location = url;
        return false;
    });

    $emailListElement.on('click', '.delete-email', function (event) {
        $(this).closest('li').remove();
    });

    $('#addBtn').on('click', function () {
        var $emailAddressInput = $('#emailAddress');
        var $incorrectEmailMessageElement = $emailAddressInput.parent().find('.incorrect-email-message');
        var newEmail = $emailAddressInput.val();
        if (!newEmail.match(emailReqexp)) {
            $incorrectEmailMessageElement.text("Entered value is not a valid email.").removeClass('hide');
            return;
        }
        var isDuplicate = $emailListElement.find('.added-email')
            .filter(function (index, emailElement){
                return newEmail === emailElement.textContent.trim();
            })
            .length > 0;

        if (isDuplicate) {
            $incorrectEmailMessageElement.text("Email already in").removeClass('hide');
            return;
        }
        $incorrectEmailMessageElement.addClass('hide');
        $emailAddressInput.val('');
        $emailElementTemplate
            .clone()
            .find('.input-email')
            .val(newEmail)
                .end()
            .find('.added-email')
            .text(newEmail)
                .end()
            .appendTo($emailListElement);
        console.log('Added new email: %s', newEmail);
    });

});
