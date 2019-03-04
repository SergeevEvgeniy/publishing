var pagination = require('./pagination.hbs');
var $ = require('jquery');
var PagesComponent = require('../journalist-pages/pages-component');
var $paginationBlock = $('<div>', {
    id: 'paginationBlock'
});
$paginationBlock.append(pagination());

/**
 *
 * @param $parentElement
 * @constructor
 */
function PaginationComponent($parentElement) {
    var pageChangeListener = null;

    var pageStartNumber = 1;
    var pageQuantity;
    var itemsQuantity;
    var itemsDisplayQuantity = 5;
    var pagesVisibleBeforeActive = 2;
    var pagesVisibleAftereActive = 2;

    var dropdownElementSelector = '.dropdown-item';
    var pageItemSelector = '.page';
    var prevPageItemSelector = '.page-prev';
    var nextPageItemSelector = '.page-next';

    var $pageQuantityElement = $paginationBlock.find('.page-quantity');
    var $currentDropdownElement = $paginationBlock.find('.d-none');
    var $pagesElement = $paginationBlock.find('#pages');

    var pagesComponent = new PagesComponent($pagesElement);

    function renderPages(pageNumber) {
        var activePageNumber = pageNumber || 1;
        var i = activePageNumber - pagesVisibleBeforeActive;
        var pageList = {
            pageList: []
        };
        if (pageChangeListener) {
            pageChangeListener(itemsDisplayQuantity, activePageNumber);
        }

        pageQuantity = Math.ceil(itemsQuantity / itemsDisplayQuantity);
        if (pageQuantity === 1) {
            $pagesElement.empty();
            return;
        }

        for (; i <= activePageNumber + pagesVisibleAftereActive; i++) {
            if (i >= pageStartNumber && i <= pageQuantity) {
                pageList.pageList.push({
                    pageNumber: i
                });
            }
        }
        pagesComponent.activePageNumber = activePageNumber;
        pagesComponent.setPageList(pageList);
        pagesComponent.render();

        if (activePageNumber === pageStartNumber) {
            $paginationBlock.find(prevPageItemSelector).addClass('disabled');
        } else if (activePageNumber === pageQuantity) {
            $paginationBlock.find(nextPageItemSelector).addClass('disabled');
        }
    }

    function onPageClickEvent(event) {
        var activePageNumber = +event.target.textContent;
        if (activePageNumber !== pagesComponent.activePageNumber) {
            renderPages(activePageNumber);
        }
    }

    function onPrevPageClickEvent() {
        var activePageNumber = pagesComponent.activePageNumber - 1;
        if (activePageNumber >= pageStartNumber) {
            renderPages(activePageNumber);
        }
    }

    function onNextPageClickEvent() {
        var activePageNumber = pagesComponent.activePageNumber + 1;
        if (activePageNumber <= pageQuantity) {
            renderPages(activePageNumber);
        }
    }

    function onDropdownItemClickEvent(event) {
        itemsDisplayQuantity = +event.target.textContent;
        renderPages();
        $currentDropdownElement.removeClass('d-none');
        $currentDropdownElement = $(event.target);
        $currentDropdownElement.addClass('d-none');
        $pageQuantityElement.text(itemsDisplayQuantity.toString());
    }

    $paginationBlock.on('click', pageItemSelector, onPageClickEvent);
    $paginationBlock.on('click', prevPageItemSelector, onPrevPageClickEvent);
    $paginationBlock.on('click', nextPageItemSelector, onNextPageClickEvent);
    $paginationBlock.on('click', dropdownElementSelector, onDropdownItemClickEvent);

    this.setItemsQuantity = function setItemsQuantity(quantity) {
        itemsQuantity = quantity;
    };

    /**
     * @param listener function that take two params itemsDisplayQuantity and activePageNumber
     */
    this.onPageChange = function onPageChange(listener) {
        pageChangeListener = listener;
    };

    this.render = function render() {
        $parentElement.append($paginationBlock);
        renderPages();
    };
}

module.exports = PaginationComponent;
