var resultTemplate = require('./journalist-result.hbs');
var PaginationComponent = require('../journalist-pagination/pagination-component');
var JournalistListComponent = require('../journalist-list/journalist-list-component');
var $resultPage = $('<div>', {
    id: 'searchJournalistResult'
});
var { JournalistStatComponent } = require('../journalist-info/journalist-component');
$resultPage.append(resultTemplate());

function JournalistResultComponent($parentElement) {
    var $paginationElement = $resultPage.find('#pagination');
    var $journalistListElement = $resultPage.find('tbody');

    var pagination = new PaginationComponent($paginationElement);
    var journalistList = new JournalistListComponent($journalistListElement);
    //событие, для отображения владки "Информация о журналисте"
    //оно просто для теста
    $parentElement.on('click', 'button', function (event) {
        var target = event.target;
        var journalistName = target.closest('tr').firstElementChild;
        var journalistStatComponent = new JournalistStatComponent($('#app'));
        journalistStatComponent.appendComponent(journalistName);
    })

    this.render = function render(data) {
        var journalistQuantity = data.length;
        $parentElement.empty().append($resultPage);
        pagination.setItemsQuantity(journalistQuantity);
        pagination.onPageChange(function pageChangeListener(itemsDisplayQuantity, activePageNumber) {
            var startItemIndex = itemsDisplayQuantity * activePageNumber - itemsDisplayQuantity;
            var endItemIndex = itemsDisplayQuantity * activePageNumber;
            journalistList.render(data.slice(startItemIndex, endItemIndex));
        });
        pagination.render();
    };
}

module.exports = JournalistResultComponent;
