var magazineContainerTemplate = require('./magazine-container.hbs');

var $ = require('jquery');
var FilterComponent = require('../../publishing-filter/filter-component');
var FilterResultComponent = require('../../publishing-filter/filter-result-component');
var PaginationComponent = require('../../pagination/pagination-component');
var SelectPerPageComponent = require('../../select-per-page/select-per-page-component');

function MagazineComponent($parentElement) {
    var filterComponent;
    var filterResultComponent;
    var paginationComponent;
    var selectPerPageComponent;
    var publishingViewComponent;

    function onFilterSubmitListener(formData, next) {
        console.log('submit filter form');
        console.log(formData);
        setTimeout(next(), 5000);
    }

    function onSelectMagazineIssueListener(issueId) {
        console.log('select magazine issue ' + issueId);
        publishingViewComponent.setIssue(issueId);
    }

    function onPageChangeListener(currentPage) {
        console.log('change page' + currentPage);
    }

    function onSelectPerPageChangeListener(perPage) {
        console.log('change per page ' + perPage);
        paginationComponent.setPerPage(perPage);
    }

    function render() {
        $parentElement
            .empty()
            .append(magazineContainerTemplate());

        filterComponent = new FilterComponent($('.filter-container'), 'Журнал');
        filterComponent.setFilterSubmitListener(onFilterSubmitListener);
        filterResultComponent = new FilterResultComponent($('.filter-result-container'));
        filterResultComponent.setShowIssueListener(onSelectMagazineIssueListener);

        filterResultComponent.setIssues([
            {
                id: 1,
                title: 'Issue 1',
            },
            {
                id: 2,
                title: 'Issue 2',
            },
            {
                id: 3,
                title: 'Issue 3',
            },
            {
                id: 4,
                title: 'Issue 4',
            },
            {
                id: 5,
                title: 'Issue 5',
            },
            {
                id: 6,
                title: 'Issue 6',
            },
            {
                id: 7,
                title: 'Issue 7',
            },
        ]);

        paginationComponent = new PaginationComponent($('.pagination-container'));
        paginationComponent.onPageChange(onPageChangeListener);

        paginationComponent.setAmountRecord(100);
        paginationComponent.setPerPage(10);
        paginationComponent.setCurrentPage(2);

        selectPerPageComponent = new SelectPerPageComponent($('.select-per-page-container'));
        selectPerPageComponent.onSelectPerPageChange(onSelectPerPageChangeListener);
    }

    render();
}

module.exports = MagazineComponent;
