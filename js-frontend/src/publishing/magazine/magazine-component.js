var magazineContainerTemplate = require('./magazine-container.hbs');
var magazineViewTemplate = require('./magazine-view.hbs');

var $ = require('jquery');
var FilterComponent = require('../../publishing-filter/filter-component');
var FilterResultComponent = require('../../publishing-filter/filter-result-component');
var PaginationComponent = require('../../pagination/pagination-component');
var SelectPerPageComponent = require('../../select-per-page/select-per-page-component');
var PublicationViewComponent = require('../publication-view-component');

var MagazineService = require('../../services/magazine-service');

function MagazineComponent($parentElement) {
    var filterComponent;
    var filterResultComponent;
    var paginationComponent;
    var selectPerPageComponent;
    var publicationViewComponent;
    var loading = {
        status: true,
        stage: 'Загрузка наименований журналов',
    };
    var magazineTitle;

    function onFilterSubmitListener(formData, next) {
        MagazineService
            .getFilteredIssues(formData)
            .then(function handleResponse(response) {
                magazineTitle = formData[0].value;
                filterResultComponent.setIssues(response);
                paginationComponent.setAmountRecord(response.length);
                selectPerPageComponent.setVisible(response.length > 0);
                next();
            })
            .catch(function handleError(error) {
                console.log(error);
            });
    }

    function onSelectMagazineIssueListener(issue) {
        publicationViewComponent.setPublicationIssue({
            publicationTitle: magazineTitle,
            issueId: issue.id,
            issueDate: issue.date,
            issueNumber: issue.number,
            issueSubject: issue.subject,
        });
    }

    function onPageChangeListener(currentPage) {
        filterResultComponent.setCurrentPage(currentPage);
    }

    function onSelectPerPageChangeListener(perPage) {
        paginationComponent.setPerPage(perPage);
        filterResultComponent.setPerPage(perPage);
    }

    function render() {
        $parentElement
            .empty()
            .append(magazineContainerTemplate({
                loading: loading,
            }));
    }

    render();

    MagazineService
        .getMagazinesTitles()
        .then(function handleResponse(response) {
            loading.status = false;
            render();

            filterComponent = new FilterComponent($('.filter-container'), 'Журнал');
            filterComponent.setFilterSubmitListener(onFilterSubmitListener);
            filterComponent.setPublications(response);

            filterResultComponent = new FilterResultComponent($('.filter-result-container'));
            filterResultComponent.setShowIssueListener(onSelectMagazineIssueListener);

            paginationComponent = new PaginationComponent($('.pagination-container'));
            paginationComponent.onPageChange(onPageChangeListener);

            selectPerPageComponent = new SelectPerPageComponent($('.select-per-page-container'));
            selectPerPageComponent.onSelectPerPageChange(onSelectPerPageChangeListener);

            publicationViewComponent = new PublicationViewComponent($('.view-container'), magazineViewTemplate);
        })
        .catch(function handleError(error) {
            loading.stage = error;
            render();
        });
}

module.exports = MagazineComponent;
