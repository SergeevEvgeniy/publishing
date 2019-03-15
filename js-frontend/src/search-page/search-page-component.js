var searchTemplate = require('./search-page.hbs');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');
var SearchJournalistComponent = require('../search-journalist/search-component');
var JournalistStatComponent = require('../journalist-info/journalist-component');
var Pagination = require('../pagination/pagination-component');

/**
 * Компонент поиска журналистов.
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
        journalistResultComponent.setJournalistList(journalistList.slice(0, 5));
        journalistPaginationComponent.setAmountRecord(journalistList.length);
        journalistPaginationComponent.setCurrentPage(1);
        journalistPaginationComponent.setPerPage(5);
        journalistPaginationComponent.onPageChange(function onPageChange(newCurrentPage) {
            journalistResultComponent.setJournalistList(journalistList.slice(newCurrentPage * 5 - 5, newCurrentPage * 5));
        });
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

    function render() {
        $parentElement.empty().append($componentContainer);
        searchJournalistComponent.render();
    }
    /**
     * Отрисовка компонента.
     */
    this.render = render;
}

module.exports = SearchPageComponent;
