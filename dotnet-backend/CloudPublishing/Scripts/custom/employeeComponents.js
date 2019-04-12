(function() {
    function generateAlert(successful, message, delay) {
        var div = document.createElement("DIV");
        var button = document.createElement("BUTTON");
        var i = document.createElement("I");
        var text = document.createElement("SPAN");
        button.appendChild(i);
        div.appendChild(text);
        div.appendChild(button);
        div.className = "alert alert-dismissible fade show";
        div.classList.add(successful ? "alert-success" : "alert-danger");
        div.setAttribute("role", "alert");
        text.textContent = message;
        button.dataset.dismiss = "alert";
        button.className = "close";
        button.type = "button";
        button.setAttribute("aria-label", "Close");
        i.className = "fas fa-times";
        i.setAttribute("aria-hidden", "true");
        $(div).delay(delay).fadeIn(100,
            function () {
                $(this).alert("close");
            });

        return div;
    }

    window.showAlert = function (successful, message, container, fadeDelay) {
        container.appendChild(generateAlert(successful, message, fadeDelay));
    }
})();

window.AjaxTableComponent = (function() {
    function AjaxTableComponent(table, removeButtonClassName, deleteUrl) {
        var elementRemoveListener = null;

        this.onElementRemove = function(listener) {
            elementRemoveListener = listener;
        };

        var tbody = table.tBodies[0];
        tbody.addEventListener("click", function(event) {
            var target = event.target;
            while (target !== this) {
                if (target.classList.contains(removeButtonClassName)) {
                    $.ajax({
                        type: "POST",
                        url: deleteUrl,
                        data: { id: target.parentNode.parentNode.parentNode.id},
                        //contentType: "application/json; charset=utf-8",
                        success: function (result) {
                            if (result.isSuccessful) {
                                removeRow(tbody, id);
                            }
                            if (elementRemoveListener) {
                                elementRemoveListener(result);
                            }
                        }
                    });
                }
                target = target.parentNode;
            }
        });
    }

    function removeRow(container, id) {
        var row = container.rows.namedItem(id);
        console.log(row);
        container.removeChild(row);
    };

    return AjaxTableComponent;
})();