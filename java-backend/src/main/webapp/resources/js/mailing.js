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

    emailList.click(function (event) {
        var target = event.target.closest('span');
        if (target === null) {
            return;
        }
        target.closest('li').remove();
    });

    $('#addBtn').click(function () {
        var newEmail = $('#emailAddress').val();
        if (newEmail.match(/^[\w%_\-.\d]+@[\w.\-]+.[A-Za-z]{2,6}$/)) {
            emailList.append(createNewEmailListItem(newEmail));
        } else {
            alert("! newEmail.match(/^[\\w%_\\-.\\d]+@[\\w.\\-]+.[A-Za-z]{2,6}$/)");
        }
        console.log(newEmail);
    });

    function createTrashIcon() {
        var div = document.createElement('div');
        div.className = 'col-xs-2 text-right';
        var span = document.createElement('span');
        span.className = 'glyphicon glyphicon-trash';
        span.style.cursor = 'pointer';
        div.appendChild(span);
        return div;
    }

    function createNewEmailListItem(email) {
        var li = document.createElement('li');
        li.className = 'list-group-item';
        var rowDiv = document.createElement('div');
        rowDiv.className = 'row';
        var emailDiv = document.createElement('div');
        emailDiv.textContent = email;
        emailDiv.className = 'col-xs-10';
        rowDiv.appendChild(emailDiv);
        rowDiv.appendChild(createTrashIcon());
        li.appendChild(rowDiv);
        return li;
    }
});
