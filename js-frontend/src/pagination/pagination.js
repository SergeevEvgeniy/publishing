var paginationTemplate = require('./pagination.hbs');
var $ = require('jquery');

var amountVisiblePages = 5;
var amountVisiblePagesOnSide = 2;

/**
 * Создаёт компонент позволяющий перемещаться между страницами
 * @constructor
 * @param {JQuery element} $parentElement - jqeury элемент-контейнер для размещения компонента
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
    var handleChangedPageCallback = null;

    function handlePageClicked(event) {
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
        endPageIndex = Math.min(startPageIndex + amountVisiblePages - 1, amountPages);
        visiblePages = [];
        for (index = startPageIndex; index <= endPageIndex; index++) {
            this.visiblePages.push({
                value: index,
                class: (currentPage === index) ? 'active' : '',
            });
        }
    }

    /**
     * Метод устанавливающий callback, вызывающийся после изменения текущей страцниы
     * @param {function} callback - callback принимающий в качестве параметра номер новой страницы
     */
    this.setHandleChangedPageCallback = function setHandleChangedPageCallback(callback) {
        handleChangedPageCallback = callback;
    };

    /**
     * Метод, который устаравливает количество элементов на странице
     * @param {Number} newPerPage - количество элементов на странице
     */
    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        this.recount();
        this.render();
    };

    /**
     * Метод устанавливающий текущую страницу
     * @param {Number} newCurrentPage - количество элементов на странице
     */
    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        if (!!handleChangedPageCallback) {
            return;
        }
        currentPage = (newCurrentPage < 1) ? 1 : newCurrentPage;
        currentPage = (newCurrentPage > amountPages) ? amountPages : newCurrentPage;
        handleChangedPageCallback(currentPage);
        this.recount();
        this.render();
    };

    /**
     * Метод устанавливающий текущую страницу
     * @param {Number} newCurrentPage - количество элементов на странице
     */
    this.setAmountRecord = function setAmountRecord(inputAmountRecord) {
        if (amountRecord === 0) {
            return;
        }
        amountRecord = inputAmountRecord;
        recount();
        this.render();
    };

    $paginationWrapper.on('click', '.pagination span', handlePageClicked(event));

    /**
     * Метод отображающий компонент в переданном $parentElement контейнере
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
