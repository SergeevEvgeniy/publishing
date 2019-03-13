var paginationTemplate = require('./pagination.hbs');
var $ = require('jquery');

var amountVisiblePagesOnSide = 2;

/**
 * Создаёт компонент позволяющий перемещаться между страницами
 * @constructor
 * @param {JQuery element} $parentElement - элемент-контейнер для размещения компонента
 */
function Pagination($parentElement) {
    var $paginationWrapper = $('<div />');

    var amountRecord = 0;
    var perPage = 5;
    var currentPage = 1;
    var amountPages = 0;
    var visiblePages = [];
    var startPageIndex = 0;
    var endPageIndex = 0;
    var index = 0;
    var pageChangeListener = null;

    function onPageChangeEvent(event) {
        var target = event.target;
        var clickedPage = $(target).data('page');

        if (clickedPage === '...') {
            return;
        }
        if (clickedPage === 'next') {
            this.setCurrentPage(+currentPage + 1);
        } else if (clickedPage === 'prev') {
            this.setCurrentPage(+currentPage - 1);
        } else {
            this.setCurrentPage(+clickedPage);
        }
    }

    function recount() {
        if (amountRecord === 0) {
            return;
        }
        amountPages = Math.ceil(amountRecord / perPage);
        startPageIndex = Math.max(currentPage - amountVisiblePagesOnSide, 1);
        endPageIndex = Math.min(startPageIndex + amountVisiblePagesOnSide - 1, amountPages);
        visiblePages = [];
        for (index = startPageIndex; index <= endPageIndex; index++) {
            this.visiblePages.push({
                value: index,
                class: (currentPage === index) ? 'active' : '',
            });
        }
    }

    /**
     * Установка метода обратного вызова на изменение текущей страницы
     * @param {function} listener вызывается, когда была изменена текущая страница
     * Отправляет аргумент - новая страница
     */
    this.onPageChange = function onPageChange(listener) {
        pageChangeListener = listener;
    };

    /**
     * Установка количества элементов на странице
     * @param {Number} newPerPage - количество элементов на странице
     */
    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        this.recount();
        this.render();
    };

    /**
     * Установка текущей страницы
     * @param {Number} newCurrentPage - текущая страница
     */
    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        if (!!handleChangedPage) {
            return;
        }
        currentPage = (newCurrentPage < 1) ? 1 : newCurrentPage;
        currentPage = (newCurrentPage > amountPages) ? amountPages : newCurrentPage;
        pageChangeListener(currentPage);
        recount();
        this.render();
    };

    /**
     * Установка количества записей
     * @param {Number} newAmountRecord - количество элементов
     */
    this.setAmountRecord = function setAmountRecord(newAmountRecord) {
        if (amountRecord === 0) {
            return;
        }
        amountRecord = newAmountRecord;
        recount();
        this.render();
    };

    $paginationWrapper.on('click', '.pagination span', onPageChangeEvent);

    /**
     * Отрисовка компонента
     */
    this.render = function render() {
        $paginationWrapper
            .empty()
            .append(paginationTemplate({
                pages: visiblePages,
                perPage: perPage,
            }));
        $parentElement
            .empty()
            .append(paginationWrapper);
    };
}

require('./pagination.css');

module.exports = Pagination;
