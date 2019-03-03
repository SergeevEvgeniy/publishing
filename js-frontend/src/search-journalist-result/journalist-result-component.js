var resultTemplate = require('./journalist-result.hbs');
var PaginationComponent = require('../journalist-pagination/pagination-component');
var JournalistListComponent = require('../journalist-list/journalist-list-component');
var $resultPage = $('<div>', {
    id: 'searchJournalistResult'
});
$resultPage.append(resultTemplate());

function JournalistResultComponent($parentElement) {
    //var journalistListData;
    var $paginationElement = $resultPage.find('#pagination');
    var $journalistListElement = $resultPage.find('tbody');

    var pagination = new PaginationComponent($paginationElement);
    var journalistList = new JournalistListComponent($journalistListElement);
    this.render = function render(data) {
        var journalistQuantity = data.length;
        $parentElement.empty().append($resultPage);
        pagination.setItemsQuantity(journalistQuantity);
        pagination.onPageChange(function pageChangeListener(itemsDisplayQuantity, activePageNumber) {
            console.log(itemsDisplayQuantity + ' activePageNumber = ' + activePageNumber);
            var endElement = itemsDisplayQuantity * activePageNumber;
            journalistList.render(data.slice(endElement - itemsDisplayQuantity, endElement));
        });
        pagination.render();
    };
}

module.exports = JournalistResultComponent;
