var optionList = require('./journalist-list.hbs');
var $ = require('jquery');

function OptionListComponent($parentElement) {
    this.render = function render(elementList) {
        $($parentElement).empty().append(optionList({
            elementList: elementList
        }));
    };
}

module.exports = OptionListComponent;
