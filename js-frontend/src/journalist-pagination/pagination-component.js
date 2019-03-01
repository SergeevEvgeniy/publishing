var pagination = require('./pagination.hbs');
var $ = require('jquery');
var PagesComponent = require('../journalist-pages/pages-component');
var $paginationBlock = $('<div>', {
    id: 'paginationBlock'
});
$paginationBlock.append(pagination());
function PaginationComponent($parentElement) {
    var dropdownElementSelector = '.dropdown-item';
    var $pageQuantityElement = $paginationBlock.find('.page-quantity');
    var $currentDropdownElement = $paginationBlock.find('.d-none');
    var $pagesElement = $paginationBlock.find('#pages');
    function onDropdownItemClick(event) {
        $currentDropdownElement.removeClass('d-none');
        $currentDropdownElement = $(event.target);
        $currentDropdownElement.addClass('d-none');
        $pageQuantityElement.text($currentDropdownElement.text());
    }

    $paginationBlock.on('click', dropdownElementSelector, onDropdownItemClick);

    this.render = function render() {
        var pagesComponent = new PagesComponent($pagesElement);
        pagesComponent.render();
        $parentElement.append($paginationBlock);
    };
}

module.exports = PaginationComponent;
