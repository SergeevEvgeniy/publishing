var pages = require('./pages.hbs');
var $ = require('jquery');
var $pagesBlock = $('<div>', {
    id: 'paginationBlock'
});
$pagesBlock.append(pages());
function PagesComponent($parentElement) {
    var pageItemSelector = '.page';
    var prevPageItemSelector = '.page-prev';
    var nextPageItemSelector = '.page-next';
    var $currentPageElement = $pagesBlock.find('.active');
    function onPageItemClick(event) {
        var newCurrentPage = event.target.closest(pageItemSelector);
        $currentPageElement.removeClass('active');
        $currentPageElement = $(newCurrentPage);
        $currentPageElement.addClass('active');
        console.log(event.target);
        ///document.getElementById('gf').closest(pageItemSelector);
        $(event.target.closest(pageItemSelector)).addClass('active');
    }

    function onNextPageItemClick() {
        $currentPageElement.removeClass('active');
        $currentPageElement = $currentPageElement.next();
        $currentPageElement.addClass('active');
    }
    function onPrevPageItemClick() {
        $currentPageElement.removeClass('active');
        $currentPageElement = $currentPageElement.prev();
        $currentPageElement.addClass('active');
    }

    $pagesBlock.on('click', pageItemSelector, onPageItemClick);
    $pagesBlock.on('click', prevPageItemSelector, onPrevPageItemClick);
    $pagesBlock.on('click', nextPageItemSelector, onNextPageItemClick);
    this.render = function render() {
        $parentElement.append($pagesBlock);
    };
}

module.exports = PagesComponent;
