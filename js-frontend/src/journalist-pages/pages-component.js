var pagesTemplate = require('./pages.hbs');

function PagesComponent($parentElement) {
    var pageList;
    this.activePageNumber = 1;
    this.setPageList = function setDate(list) {
        pageList = list;
    };

    this.render = function render() {
        $parentElement.empty().append(pagesTemplate(pageList));
        $parentElement.find('li:contains(' + this.activePageNumber + ')').addClass('active');
    };
}

module.exports = PagesComponent;
