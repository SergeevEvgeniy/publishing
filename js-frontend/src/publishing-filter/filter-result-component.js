var filterResultTemplate = require('./filter-result.hbs');

var $ = require('jquery');

function FilterResult($parentElement) {
    var $filterResultWrapper = $('<div />');
    var issues = [];
    var visibleIssues = [];
    var perPage = 5;
    var currentPage = 1;
    var onShowIssueListener = null;

    var startItemIndex;
    var endItemIndex;

    function onSelectIssueEvent(event) {
        var tr = $(event.target).parent('tr');
        tr.parent('tbody')
            .find('tr')
            .removeClass('table-active');

        tr.addClass('table-active');
        onShowIssueListener(tr.data('id'));
    }

    function recount() {
        if (issues.length === 0) {
            visibleIssues = [];
            render();
            return;
        }
        startItemIndex = (currentPage - 1) * perPage;
        endItemIndex = currentPage * perPage;
        endItemIndex = (endItemIndex > issues.length) ? issues.length : endItemIndex;

        visibleIssues = issues.slice(startItemIndex, endItemIndex);
        render();
    }

    this.setIssues = function setIssues(newIssues) {
        issues = newIssues;
        recount();
        render();
    };

    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        recount();
        this.render();
    };

    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        currentPage = newCurrentPage;
        recount();
        render();
    };

    this.setShowIssueCallback = function setOnSelectIssueListener(listener) {
        onShowIssueListener = listener;
    };

    $filterResultWrapper.on('click', 'td', onSelectIssueEvent);

    this.render = function render() {
        $filterResultWrapper
            .empty()
            .append(filterResultTemplate({
                issues: visibleIssues,
            }));
        $parentElement
            .empty()
            .append($filterResultWrapper);
    };

    render();
}

module.exports = FilterResult;
