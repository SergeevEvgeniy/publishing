var resultTemplate = require('./journalist-result.hbs');
var JournalistStatComponent = require('../journalist-info/journalist-component');
var data = {
    componentId: 'journalistResult',
    journalistList: []
};
var Pagination = require('../pagination/pagination-component');
/**
 * Компонент поиска журналистов.
 * @constructor
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function JournalistResultComponent($parentElement) {
    var $componentContainer = $('<div/>');
    var $paginationContainer = $('<div/>');
    $parentElement.append($componentContainer);
    $parentElement.append($paginationContainer);

    function render() {
        $componentContainer.empty().append(resultTemplate(data));
    }

    $componentContainer.on('click', '.journalist-info', function onJournalistInfoClickEvent(event) {
        var journalistName = event.target.closest('tr').firstElementChild.textContent;
        var journalistStatComponent = new JournalistStatComponent($parentElement);
        journalistStatComponent.appendComponent(journalistName);
    });
    /**
     * Установка данных о журналистах.
     * @param  {Array} journalistList Данные с информацией о журналистах.
     */
    this.setJournalistList = function setJournalistList(journalistList) {
        var pagination = new Pagination($paginationContainer);
        data.journalistList = journalistList.slice(0, 5);
        render();
        pagination.setAmountRecord(journalistList.length);
        pagination.setCurrentPage(1);
        pagination.setPerPage(5);
        pagination.onPageChange(function onPageChange(newCurrentPage) {
            console.log(newCurrentPage);
            data.journalistList = journalistList.slice(newCurrentPage * 5, newCurrentPage * 5 + 5);
            render();
        });
    };
    /**
     * @returns {function} Отрисовка компонента.
     */
    this.render = render;
}

module.exports = JournalistResultComponent;
