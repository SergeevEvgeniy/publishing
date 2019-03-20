configureMultiSelectComponent('topics', 'Publishing.TopicsId');
configureMultiSelectComponent('journalists', 'Publishing.EmployeesId');
configureMultiSelectComponent('editors','Publishing.EmployeesId');

function configureMultiSelectComponent(componentId, propertyName) {
    var component = document.getElementById(componentId);
    var selector = component.querySelector('.form-control');
    var listItems = component.querySelector('ul');
    var addButton = component.querySelector('.btn-success');

    selector.addEventListener('change', onSelectListChange);
    listItems.addEventListener('click', onListClick);
    addButton.addEventListener('click', onAddButtonClick);
    onSelectListChange();

    function onSelectListChange() {
        addButton.disabled = selector.selectedIndex == 0;
        selector.disabled = selector.options.length == 1;
    }

    function onAddButtonClick() {
        var i = selector.selectedIndex;
        var option = selector.options[i];
        var value = option.value;
        var text = option.text;

        createListItem(value, text);
        selector.removeChild(option);
        onSelectListChange();
    }

    function onListClick(event) {
        target = event.target;

        while (target != this) {
            if (target.tagName == 'BUTTON') {
                var listItem = target.closest('li');
                var contentDiv = target.parentElement.previousElementSibling;
                var text = contentDiv.textContent;
                var value = target.previousElementSibling.value;

                var option = new Option(text, value);
                selector.appendChild(option);
                listItems.removeChild(listItem);
                onSelectListChange();
                return;
            }
            target = target.parentElement;
        }
    }


    function createListItem(value, text) {
        var li = document.createElement('li');
        li.classList.add('list-group-item');

        var divRow = document.createElement('div');
        divRow.classList.add('row');

        var divTitle = document.createElement('div');
        divTitle.classList.add('col-6');

        var divTrash = document.createElement('div');
        divTrash.classList.add('col-6', 'text-right');

        var hiddenInput = document.createElement('input');
        hiddenInput.setAttribute('type', 'hidden');
        hiddenInput.setAttribute('name', propertyName);
        hiddenInput.setAttribute('value', value);

        var removeButton = document.createElement('button');
        removeButton.classList.add('btn', 'btn-light');

        var trashIcon = document.createElement('i');
        trashIcon.classList.add('fas', 'fa-trash-alt');

        li.appendChild(divRow);
        divRow.appendChild(divTitle);
        divRow.appendChild(divTrash);

        divTitle.textContent = text;

        divTrash.appendChild(hiddenInput);
        divTrash.appendChild(removeButton);

        removeButton.appendChild(trashIcon);

        listItems.appendChild(li);
    }
}