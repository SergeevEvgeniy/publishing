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
    var searchJournalistComponent = new SearchJournalistComponent($componentContainer.find('#searchJournalistForm'));
    var journalistResultComponent = new JournalistResultComponent($componentContainer.find('#searchJournalistResult'));
    var journalistStatComponent = new JournalistStatComponent($componentContainer.find('#journalistInfoTab'));
    var journalistPagination = new Pagination($componentContainer.find('#journalistPagination'));

    searchJournalistComponent.onSearchJournalist(function setJournalistList(journalistList) {
        journalistResultComponent.setJournalistList(journalistList.slice(0, 5));
        journalistPagination.setAmountRecord(journalistList.length);
        journalistPagination.setCurrentPage(1);
        journalistPagination.setPerPage(5);
        journalistPagination.onPageChange(function onPageChange(newCurrentPage) {
            journalistResultComponent.setJournalistList(journalistList.slice(newCurrentPage * 5 - 5, newCurrentPage * 5));
        });
    });

    journalistResultComponent.onJournalistInfoButtonClick(function openJournalistInfo(journalistName) {
        $componentContainer.find('#searchJournalist').empty();
        journalistStatComponent.appendComponent(journalistName);
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
