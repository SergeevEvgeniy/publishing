var resultTemplate = require('./journalist-result.hbs');
var JournalistListComponent = require('../journalist-list/journalist-list-component');
var $resultPage = $('<div>', {
    id: 'searchJournalistResult'
});
var JournalistStatComponent = require('../journalist-info/journalist-component');
$resultPage.append(resultTemplate());

function JournalistResultComponent($parentElement) {
    var $journalistListElement = $resultPage.find('tbody');

    var journalistList = new JournalistListComponent($journalistListElement);
    $parentElement.on('click', 'button', function onJournalistInfoClickEvent(event) {
        var target = event.target;
        var journalistName = target.closest('tr').firstElementChild.textContent;
        var journalistStatComponent = new JournalistStatComponent($('#app'));
        journalistStatComponent.appendComponent(journalistName);
    });

    this.render = function render(data) {
        $parentElement.empty().append($resultPage);
        journalistList.render(data.slice(0, 10));
    };
}

module.exports = JournalistResultComponent;
