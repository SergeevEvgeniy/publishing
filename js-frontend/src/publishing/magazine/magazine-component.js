var magazineContainerTemplate = require('../publication-container.hbs');
var magazineViewTemplate = require('./magazine-view.hbs');

var $ = require('jquery');
var FilterComponent = require('../../publishing-filter/filter-component');
var FilterResultComponent = require('../../publishing-filter/filter-result-component');
var PaginationComponent = require('../../pagination/pagination-component');
var SelectPerPageComponent = require('../../select-per-page/select-per-page-component');
var PublicationViewComponent = require('../publication-view-component');
var AlertBoxComponent = require('../../alert-box/alert-box-component');

var PublicationService = require('../../services/publication-service');

/**
 * Создаёт компонент управления журналами, а именно поиск и отображение.
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
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
    var alert = new AlertBoxComponent();

    /**
     * Функция обратного вызова выполняющая запрос по данным формы фильтрации
     * @param {Array<{name: string, value: string}>} formData данные формы
     * @param {function} next функция обратного вызова
     */
    function onFilterSubmitListener(formData, next) {
        PublicationService
            .getFilteredIssues(formData)
            .then(function handleResponse(response) {
                magazineTitle = formData[0].value;
                filterResultComponent.setIssues(response);
                paginationComponent.setAmountRecord(response.length);
                selectPerPageComponent.setVisible(response.length > 0);
                next();
            })
            .catch(function handleError(error) {
                alert.error(error);
                next();
            });
    }

    /**
     * Функция обратного вызова устанавливающая выбранный номер в компонент отображения номера
     * @param {Array.<object>} issue выбранный номер
     */
    function onSelectMagazineIssueListener(issue) {
        publicationViewComponent.setPublicationIssue({
            publicationTitle: magazineTitle,
            issueId: issue.id,
            issueDate: issue.date,
            issueNumber: issue.number,
            issueSubject: issue.subject,
        });
    }

    /**
     * Функция обратного вызова устанавливающая выбранную страницу
     * @param {number} currentPage текущая страница
     */
    function onPageChangeListener(currentPage) {
        filterResultComponent.setCurrentPage(currentPage);
    }

    /**
     * Функция обратного вызова устанавливающая количество отображаемых элементов
     * @param {number} perPage количество отображаемых элементов
     */
    function onSelectPerPageChangeListener(perPage) {
        paginationComponent.setPerPage(perPage);
        filterResultComponent.setPerPage(perPage);
    }

    /**
     * Отображение компонета
     */
    function render() {
        $parentElement
            .empty()
            .append(magazineContainerTemplate({
                loading: loading,
            }));
    }

    render();

    PublicationService
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
            alert.error(error);
            render();
        });
}

module.exports = MagazineComponent;
