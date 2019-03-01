var optionList = require('./picklist.hbs');

function PicklistComponent($parentElement, name, defaultText) {
    this.render = function render(elementList) {
        $parentElement.empty().append(optionList({
            name: name,
            defaultText: defaultText,
            elementList: elementList
        }));
    };

    this.selectDefault = function selectDefault() {
        $parentElement.find('select').prop('selectedIndex', 0);
    };
}

module.exports = PicklistComponent;
