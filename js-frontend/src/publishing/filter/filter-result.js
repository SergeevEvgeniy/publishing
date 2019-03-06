var filterResultTemplate = require('./filter-result.hbs');

var $ = require('jquery');

function FilterResult(rootElement) {
    this.$rootElement = rootElement;

    this.data = [ ];
    this.visibleData = [ ];

    this.perPage = 5;
    this.currentPage = 1;
    this.showIssue = null;

    this.render();
}

FilterResult.prototype.componentDidMount = function () {
    $('.filter-result-table')
        .on('click', 'td', event => {
            var tr = $(event.target).parent('tr');
            tr.parent('tbody')
                .find('tr')
                .removeClass('table-active');
                
            tr.addClass('table-active');
            this.showIssue(tr.data('id'));
        });
};

FilterResult.prototype.recount = function () {
    if (this.data.length !== 0) {
        var startItemIndex = (this.currentPage - 1) * this.perPage;
        var endItemIndex = this.currentPage * this.perPage;
        endItemIndex = (endItemIndex > this.data.length) ? this.data.length : endItemIndex;

        this.visibleData = this.data.slice(startItemIndex, endItemIndex);
        this.render();
    } else {
        this.visibleData = [ ];
        this.render();
    }
};

FilterResult.prototype.setData = function (data) {
    this.data = data;
    this.recount();
    this.render();
};

FilterResult.prototype.setPerPage = function (perPage) {
    this.perPage = perPage;
    this.currentPage = 1;
    this.recount();
    this.render();
};

FilterResult.prototype.setCurrentPage = function (currentPage) {
    this.currentPage = currentPage;
    this.recount();
    this.render();
};

FilterResult.prototype.setShowIssueCallback = function (callback) {
    this.showIssue = callback;
};

FilterResult.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(filterResultTemplate({
            result: this.visibleData,
            pages: this.pages,
        }));
    this.componentDidMount();
};

module.exports = FilterResult;
