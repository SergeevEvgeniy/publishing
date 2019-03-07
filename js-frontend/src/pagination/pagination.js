//template hbs
const paginationTemplate = require('./pagination.hbs');

const $ = require('jquery');

//const
const amountVisiblePages = 5;
const amountVisiblePagesOnSide = 2;

function Pagination($rootElement) {
    this.$rootElement = $rootElement;
    this.amountRecord = 0;

    this.perPage = 5;
    this.currentPage = 1;
    this.amountPages = 0;

    this.visiblePages = [ ];
    this.selectPageCallback = null;
}

Pagination.prototype.pageClicked = function (event) {
    let target = event.target;
    let clickedPage = $(target).data('page');

    if (clickedPage === '...') { 
        return;
    }
    if (clickedPage === 'next') {
        this.setCurrentPage(this.currentPage + 1);
    } else if (clickedPage === 'prev') {
        this.setCurrentPage(this.currentPage - 1);
    } else  {
        this.setCurrentPage(+clickedPage);
    }
};

Pagination.prototype.setSelectPageCallback = function (callback) {
    this.selectPageCallback = callback;
};  

Pagination.prototype.setPerPage = function (perPage) {
    this.perPage = perPage;
    this.currentPage = 1;
    this.recount();
    this.render();
};  

Pagination.prototype.setCurrentPage = function (currentPage) {
    this.currentPage = (currentPage < 1) ? 1 : currentPage; 
    this.currentPage = (this.currentPage > this.amountPages) ? this.amountPages : this.currentPage; 
    this.selectPageCallback(this.currentPage);
    this.recount();
    this.render();
};

Pagination.prototype.setAmountRecord = function (amountRecord) {
    if (amountRecord === 0) {
        return;
    }
    this.amountRecord = amountRecord;
    this.recount();
    this.render();
};

Pagination.prototype.recount = function () {
    if (this.amountRecord === 0) {
        return;
    }
    this.amountPages = Math.ceil(this.amountRecord / this.perPage);
    var startPage, endPage;

    if (this.currentPage - amountVisiblePagesOnSide > 1) {
        if (this.currentPage + amountVisiblePagesOnSide > this.amountPages) {
            if (this.currentPage !== this.amountPages) {
                startPage = 2 * this.currentPage - this.amountPages - amountVisiblePagesOnSide;
            } else {
                startPage = Math.max(this.currentPage - 2 * amountVisiblePagesOnSide, 1);
            }
        } else {
            startPage = this.currentPage - amountVisiblePagesOnSide;
        }
    } else {
        startPage = 1;
    }

    endPage = Math.min(startPage + amountVisiblePages - 1, this.amountPages);

    this.visiblePages = [ ];
    for (let index = startPage; index < endPage + 1; index++) {
        this.visiblePages.push({
            value: index,
            class: (this.currentPage === index) ? 'active' : '',
        });
    }

    if (startPage <= this.currentPage - amountVisiblePagesOnSide &&
        startPage !== 1) {
        this.visiblePages.unshift({
            value: '...',
            class: '',
        });
    }
    if (this.amountPages > endPage) {
        this.visiblePages.push({
            value: '...',
            class: '',
        });
    }
}; 

Pagination.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(paginationTemplate({
            pages: this.visiblePages,
            perPage: this.perPage,
        }));

    $('.pagination').on('click', 'span', event => this.pageClicked(event));
};

module.exports = Pagination;

require('./pagination.css');