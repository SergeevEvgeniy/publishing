var newspaperContainerTemplate = require('../publication-container.hbs');
var newspaperViewTemplate = require('./newspaper-view.hbs');

var $ = require('jquery');
var FilterComponent = require('../../publishing-filter/filter-component');
var FilterResultComponent = require('../../publishing-filter/filter-result-component');
var PaginationComponent = require('../../pagination/pagination-component');
var SelectPerPageComponent = require('../../select-per-page/select-per-page-component');
var PublicationViewComponent = require('../publication-view-component');
var AlertBoxComponent = require('../../alert-box/alert-box-component');

var PublicationService = require('../../services/publication-service');

/**
 * Создаёт компонент управления газетами, а именно поиск и отображение.
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function NewspaperComponent($parentElement) {
    var filterComponent;
    var filterResultComponent;
    var paginationComponent;
    var selectPerPageComponent;
    var publicationViewComponent;
    var loading = {
        status: true,
        stage: 'Загрузка наименований газет',
    };
    var newspaperTitle;
    var alert;

    /**
     * Функция обратного вызова выполняющая запрос по данным формы фильтрации
     * @param {Array.<{name: String, value: String}>} formData данные формы
     * @param {Function} next функция обратного вызова
     */
    function onFilterSubmitListener(formData, next) {
        PublicationService
            .getFilteredIssues(formData)
            .then(function handleResponse(response) {
                newspaperTitle = formData[0].value;
                filterResultComponent.setIssues(response);
                paginationComponent.setAmountRecord(response.length);
                selectPerPageComponent.setVisible(response.length > 0);
                next();
            })
            .catch(function handleError(error) {
                alert.alert({
                    variant: 'danger',
                    message: error,
                    duration: 5000,
                });
            });
    }

    /**
     * Функция обратного вызова устанавливающая выбранный номер в компонент отображения номера
     * @param {Array.<Object>} issue выбранный номер
     */
    function onSelectNewspaperIssueListener(issue) {
        publicationViewComponent.setPublicationIssue({
            publicationTitle: newspaperTitle,
            issueId: issue.id,
            issueDate: issue.date,
            issueNumber: issue.number,
            issueSubject: issue.subject,
        });
    }

    /**
     * Функция обратного вызова устанавливающая выбранную страницу
     * @param {Number} currentPage текущая страница
     */
    function onPageChangeListener(currentPage) {
        filterResultComponent.setCurrentPage(currentPage);
    }

    /**
     * Функция обратного вызова устанавливающая количество отображаемых элементов
     * @param {Number} perPage количество отображаемых элементов
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
            .append(newspaperContainerTemplate({
                loading: loading,
            }));
    }

    render();

    PublicationService
        .getNewspapersTitles()
        .then(function handleResponse(response) {
            loading.status = false;
            render();

            filterComponent = new FilterComponent($('.filter-container'), 'Газета');
            filterComponent.setFilterSubmitListener(onFilterSubmitListener);
            filterComponent.setPublications(response);

            filterResultComponent = new FilterResultComponent($('.filter-result-container'));
            filterResultComponent.setShowIssueListener(onSelectNewspaperIssueListener);

            paginationComponent = new PaginationComponent($('.pagination-container'));
            paginationComponent.onPageChange(onPageChangeListener);

            selectPerPageComponent = new SelectPerPageComponent($('.select-per-page-container'));
            selectPerPageComponent.onSelectPerPageChange(onSelectPerPageChangeListener);

            publicationViewComponent = new PublicationViewComponent($('.view-container'), newspaperViewTemplate);
            alert = new AlertBoxComponent($parentElement);
        })
        .catch(function handleError(error) {
            loading.stage = error;
            alert.alert({
                variant: 'danger',
                message: error,
                duration: 5000,
            });
            render();
        });
}

module.exports = NewspaperComponent;
