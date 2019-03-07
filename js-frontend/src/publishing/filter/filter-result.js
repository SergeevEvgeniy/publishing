var filterResultTemplate = require('./filter-result.hbs');

var $ = require('jquery');

function FilterResult(rootElement) {
    this.$rootElement = rootElement;

    this.issues = [ ];
    this.visibleIssues = [ ];

    this.perPage = 5;
    this.currentPage = 1;
    this.showIssue = null;

    this.render();
}

FilterResult.prototype.showIssueClicked = function (event) {
    var tr = $(event.target).parent('tr');
    tr.parent('tbody')
        .find('tr')
        .removeClass('table-active');
        
    tr.addClass('table-active');
    this.showIssue(tr.data('id'));
};

FilterResult.prototype.recount = function () {
    if (this.issues.length === 0) {
        this.visibleIssues = [ ];
        this.render();
        return;
    } 

    var startItemIndex = (this.currentPage - 1) * this.perPage;
    var endItemIndex = this.currentPage * this.perPage;
    endItemIndex = (endItemIndex > this.issues.length) ? this.issues.length : endItemIndex;

    this.visibleIssues = this.issues.slice(startItemIndex, endItemIndex);
    this.render();
};

FilterResult.prototype.setData = function (issues) {
    this.issues = issues;
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
            issues: this.visibleIssues,
        }));
    $('.filter-result-table').on('click', 'td', event => this.showIssueClicked);
};

module.exports = FilterResult;
