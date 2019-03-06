var pagesTemplate = require('./pages.hbs');

function PagesComponent($parentElement) {
    var pageList;
    var activePageNumber;

    this.setActivePageNumber = function setActivePageNumber(pageNumber) {
        activePageNumber = pageNumber;
    };

    this.getActivePageNumber = function getActivePageNumber() {
        return activePageNumber;
    };

    this.setPageList = function setDate(list) {
        pageList = list;
    };

    this.render = function render() {
        $parentElement.empty().append(pagesTemplate(pageList));
        $parentElement.find('li:contains(' + activePageNumber + ')').addClass('active');
    };
}

module.exports = PagesComponent;
