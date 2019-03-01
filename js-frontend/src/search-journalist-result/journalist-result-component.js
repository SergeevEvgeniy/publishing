var resultTemplate = require('./journalist-result.hbs');
var PaginationComponent = require('../journalist-pagination/pagination-component');
var $resultPage = $('<div>', {
    id: 'searchJournalistResult'
});
$resultPage.append(resultTemplate());
function JournalistResultComponent($parentElement) {
    var journalistListData;
    var journalistDisplayQuantity = 5;

    var pagination = new PaginationComponent('#pagination');
    this.render = function render(data) {
        $parentElement.empty().append($resultPage);
        journalistListData = data;
        console.log(journalistListData);
        console.log(journalistDisplayQuantity);
        pagination.render();
        // $parentElement.append(resultTemplate({
        //     journalistList: data
        // }));
    };
}

module.exports = JournalistResultComponent;
