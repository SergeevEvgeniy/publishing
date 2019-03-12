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
    //событие, для отображения владки "Информация о журналисте"
    //оно просто для теста
    $parentElement.on('click', 'button', function (event) {
        var target = event.target;
        var journalistName = target.closest('tr').firstElementChild.textContent;
        var journalistStatComponent = new JournalistStatComponent($('#app'));
        journalistStatComponent.appendComponent(journalistName);
    })

    this.render = function render(data) {
        $parentElement.empty().append($resultPage);
        journalistList.render(data.slice(0, 5));       
    };
}

module.exports = JournalistResultComponent;
