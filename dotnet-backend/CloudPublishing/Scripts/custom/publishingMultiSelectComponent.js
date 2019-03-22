configureMultiSelectComponent('#topics', 'Publishing.TopicsId');
configureMultiSelectComponent('#journalists', 'Publishing.EmployeesId');
configureMultiSelectComponent('#editors','Publishing.EmployeesId');

function configureMultiSelectComponent(componentSelector, propertyName) {
    var $component = $(componentId);
    var selector = component.querySelector('.form-control');
    var listItems = component.querySelector('ul');
    var addButton = component.querySelector('.btn-success');

    $component
        .on('change', 'select', updateSelectState)
        .on('click', '.btn-success', onAddButtonClick)
        .on('click', 'ul li button', onListClick);

    selector.addEventListener('change', updateSelectState);
    listItems.addEventListener('click', onListClick);
    addButton.addEventListener('click', onAddButtonClick);
    updateSelectState();

    function updateSelectState() {
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
        updateSelectState();
    }

    function onListClick() {
        var $button = $(this);

        var listItem = $button.closest('li');
        var contentDiv = $button.parent().previousElementSibling;
        var text = contentDiv.textContent;
        var value = $button.previousElementSibling.value;

        var option = new Option(text, value);
        selector.appendChild(option);
        listItems.removeChild(listItem);
        updateSelectState();
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