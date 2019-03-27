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
    var self = this;

    /**
     * Отрисовка компонента
     */
    function render() {
        $parentElement
            .empty()
            .append(paginationTemplate({
                pages: visiblePages,
                perPage: perPage,
            }));
    }

    /**
     * Обработчик события нажатия на страницу
     * @param {object} event объект описывающий произошедшее событие клика по старнице
     */
    function onPageChangeEvent(event) {
        var target = event.target;
        var clickedPage = $(target).data('page');

        if (clickedPage === 'next') {
            self.setCurrentPage(+currentPage + 1);
        } else if (clickedPage === 'prev') {
            self.setCurrentPage(+currentPage - 1);
        } else {
            self.setCurrentPage(+clickedPage);
        }
    }

    /**
     * Определение отображаемых страниц
     */
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
     */
    this.onPageChange = function onPageChange(listener) {
        pageChangeListener = listener;
    };

    /**
     * Установка количества элементов на странице
     * @param {number} newPerPage - количество элементов на странице
     */
    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        recount();
        render();
    };

    /**
     * Установка текущей страницы
     * @param {number} newCurrentPage - текущая страница
     */
    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        currentPage = Math.min(amountPages, newCurrentPage);
        if (newCurrentPage < 1) {
            currentPage = 1;
        }
        if (pageChangeListener) {
            pageChangeListener(currentPage);
        }
        recount();
        render();
    };

    /**
     * Установка количества записей
     * @param {number} newAmountRecord - количество элементов
     */
    this.setAmountRecord = function setAmountRecord(newAmountRecord) {
        if (newAmountRecord === 0) {
            return;
        }
        amountRecord = newAmountRecord;
        recount();
        render();
    };

    $parentElement.on('click', 'span', onPageChangeEvent);
    render();
}

require('./pagination.css');

module.exports = Pagination;
