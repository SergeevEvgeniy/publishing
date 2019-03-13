var resultTemplate = require('./journalist-result.hbs');
var JournalistListComponent = require('../journalist-list/journalist-list-component');
var $resultPage = $('<div>', {
    id: 'searchJournalistResult'
}).append(resultTemplate());
var JournalistStatComponent = require('../journalist-info/journalist-component');
var data = {
    componentId: 'journalistResult'
};

function JournalistResultComponent($parentElement) {
    var $journalistListElement = $resultPage.find('tbody');

    var journalistList = new JournalistListComponent($journalistListElement);
    $parentElement.on('click', 'button', function onJournalistInfoClickEvent(event) {
        var target = event.target;
        var journalistName = target.closest('tr').firstElementChild.textContent;
        var journalistStatComponent = new JournalistStatComponent($('#app'));
        journalistStatComponent.appendComponent(journalistName);
    });
    $parentElement.append($('<div>', {
        id: data.componentId
    }));

    this.render = function render(journalists) {
        $parentElement.find('#journalistResult').empty().append($resultPage);
        journalistList.render(journalists.slice(0, 10));
    };
}

module.exports = JournalistResultComponent;
