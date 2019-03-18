/* eslint-disable no-invalid-this */
var paginationTemplate = require('./pagination.hbs');
var $ = require('jquery');

var amountVisiblePagesOnSide = 2;

/**
 * Создаёт компонент позволяющий перемещаться между страницами.
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function Pagination($parentElement) {
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
        endPageIndex = Math.min(currentPage + amountVisiblePagesOnSide, amountPages);
        visiblePages = [];
        for (index = startPageIndex; index <= endPageIndex; index++) {
            visiblePages.push({
                value: index,
                class: (currentPage === index) ? 'active' : '',
            });
        }
    }

    /**
     * Установка метода обратного вызова на изменение текущей страницы
     * @param {function} listener вызывается, когда была изменена текущая страница
     * Отправляет аргумент - новая страница
     * @returns {void}
     */
    this.onPageChange = function onPageChange(listener) {
        pageChangeListener = listener;
    };

    /**
     * Установка количества элементов на странице
     * @param {Number} newPerPage - количество элементов на странице
     * @returns {void}
     */
    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        recount();
        this.render();
    };

    /**
     * Установка текущей страницы
     * @param {Number} newCurrentPage - текущая страница
     * @returns {void}
     */
    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        if (!pageChangeListener) {
            return;
        }
        if (newCurrentPage > amountPages || newCurrentPage < 1) {
            return;
        }
        if (newCurrentPage < 1) {
            currentPage = 1;
        } else if (newCurrentPage > amountPages) {
            currentPage = amountPages;
        } else {
            currentPage = newCurrentPage;
        }
        pageChangeListener(currentPage);
        recount();
        this.render();
    };

    /**
     * Установка количества записей
     * @param {Number} newAmountRecord - количество элементов
     * @returns {void}
     */
    this.setAmountRecord = function setAmountRecord(newAmountRecord) {
        if (newAmountRecord === 0) {
            return;
        }
        amountRecord = newAmountRecord;
        recount();
        this.render();
    };

    $parentElement.on('click', 'span', onPageChangeEvent.bind(this));

    /**
     * Отрисовка компонента
     * @returns {void}
     */
    this.render = function render() {
        $parentElement
            .empty()
            .append(paginationTemplate({
                pages: visiblePages,
                perPage: perPage,
            }));
    };
}

require('./pagination.css');

module.exports = Pagination;
