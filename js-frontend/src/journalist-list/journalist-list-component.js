var journalistListTemplate = require('./journalist-list.hbs');
var $ = require('jquery');

function JournalistListComponent($parentElement) {
    this.render = function render(elementList) {
        $($parentElement).empty().append(journalistListTemplate({
            journalistList: elementList
        }));
    };
}

module.exports = JournalistListComponent;
