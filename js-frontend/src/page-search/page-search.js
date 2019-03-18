var searchTemplate = require('./search.hbs');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');
var SearchJournalistComponent = require('../search-journalist/search-component');
var JournalistStatComponent = require('../journalist-info/journalist-component');
var Pagination = require('../pagination/pagination-component');

/**
 * Компонент поиска.
 * @constructor
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function SearchPageComponent($parentElement) {
    var $componentContainer = $('<div />').append(searchTemplate());
    var $searchJournalistContainer = $componentContainer.find('#searchJournalistForm');
    var $journalistResultContainer = $componentContainer.find('#searchJournalistResult');
    var $journalistStatContainer = $componentContainer.find('#journalistInfoTab');
    var $journalistPaginationContainer = $componentContainer.find('#journalistPagination');

    var searchJournalistComponent = new SearchJournalistComponent($searchJournalistContainer);
    var journalistResultComponent = new JournalistResultComponent($journalistResultContainer);
    var journalistStatComponent = new JournalistStatComponent($journalistStatContainer);
    var journalistPaginationComponent = new Pagination($journalistPaginationContainer);

    searchJournalistComponent.onSearchJournalist(function setJournalistList(journalistList) {
        var visibleItemCount = 5;
        if (journalistList.length === 0) {
            $journalistResultContainer.append(document.createTextNode('Результаты поиска не найдены.'));
            return;
        }
        journalistResultComponent.setJournalistList(journalistList.slice(0, visibleItemCount));
        journalistPaginationComponent.setAmountRecord(journalistList.length);
        journalistPaginationComponent.setPerPage(visibleItemCount);
        journalistPaginationComponent.onPageChange(function onPageChange(newCurrentPage) {
            var firstItemIndex = newCurrentPage * visibleItemCount - visibleItemCount;
            var lastItemIndex = newCurrentPage * visibleItemCount;
            journalistResultComponent.setJournalistList(journalistList.slice(firstItemIndex, lastItemIndex));
        });
    });

    searchJournalistComponent.onClearSearch(function onClearSearch() {
        $journalistResultContainer.empty();
        $journalistPaginationContainer.empty();
    });

    journalistResultComponent.onJournalistInfoButtonClick(function openJournalistInfo(journalistName) {
        $searchJournalistContainer.empty();
        $journalistResultContainer.empty();
        $journalistPaginationContainer.empty();
        journalistStatComponent.appendComponent(journalistName);
    });

    journalistStatComponent.onReturnButtonClick(function returnToJournalistSearchForm() {
        $journalistStatContainer.empty();
        searchJournalistComponent.render();
        journalistResultComponent.render();
        journalistPaginationComponent.render();
    });

    $parentElement.empty().append($componentContainer);
    searchJournalistComponent.render();
}

module.exports = SearchPageComponent;
