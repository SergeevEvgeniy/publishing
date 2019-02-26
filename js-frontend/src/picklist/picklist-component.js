var optionList = require('./picklist.hbs');

function PicklistComponent($parentElement, name, defaultText) {
    this.render = function render(elementList) {
        $parentElement.append(optionList({
            name: name,
            defaultText: defaultText,
            elementList: elementList
        }));
    };

    this.clear = function clear() {
        $parentElement.empty();
    };

    this.selectDefault = function selectDefault() {
        $parentElement.find('select').prop('selectedIndex', 0);
    };
}

module.exports = PicklistComponent;
