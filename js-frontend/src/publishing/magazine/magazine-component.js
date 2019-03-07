// template hbs
const magazineContainerTemplate = require('./magazine-container.hbs');

// components
const $ = require('jquery');
const Filter = require('../filter/filter');
const FilterResult = require('../filter/filter-result');
const Pagination = require('../../pagination/pagination');
const SelectPerPage = require('../../select-per-page/select-per-page');
const MagazineView = require('../view/magazine-view');

// services
const magazineService = require('../../services/magazine-service');

function MagazineComponent($rootElement) {
    this.$element = $rootElement;
    this.render();
}

MagazineComponent.prototype.filterSubmitCallback = function (formData, next) {
    magazineService.getFilteredIssues(formData)
        .then(issues => {
            this.filterResult.setData(issues);
            this.pagination.setAmountRecord(issues.length);
            this.pagination.setCurrentPage(1);
            this.selectPerPage.setAmountRecord(issues.length);
            next();
        })
        .catch(err => {
            console.log(err);
        });
};

MagazineComponent.prototype.showMagazineCallback = function (issueId) {
    // magazineAPI.loadIssueMagazine(issueId)
    //     .then(res => {
    //         console.log(res);
    //     })
    //     .catch(err => {
    //         console.log(err);
    //     });
    // this.magazineView.setIssueMagazine(issueId);
};

MagazineComponent.prototype.selectPageCallback = function (currentPage) {
    this.filterResult.setCurrentPage(currentPage);
};

MagazineComponent.prototype.selectPerPageCallback = function (perPage) {
    this.filterResult.setPerPage(perPage);
    this.pagination.setPerPage(perPage);
};

MagazineComponent.prototype.loadTitles = function () {
    magazineService.getMagazinesTitles()
        .then(titles => {
            this.filter.setPublicationsTitles(titles);
        })
        .catch(err => {
            console.log(err);
        });
};

MagazineComponent.prototype.render = function () {
    this.$element
        .empty()
        .append(magazineContainerTemplate());
    
    this.filter = new Filter($('.filter-container'), 'Журнал');
    this.filter.setFilterSubmitCallback(this.filterSubmitCallback.bind(this));
    
    this.filterResult = new FilterResult($('.filter-result-container'));
    this.filterResult.setShowIssueCallback(this.showMagazineCallback.bind(this));

    this.pagination = new Pagination($('.pagination-container'));
    this.pagination.setSelectPageCallback(this.selectPageCallback.bind(this));

    this.selectPerPage = new SelectPerPage($('.select-per-page-container'));
    this.selectPerPage.setSelectPerPageCallback(this.selectPerPageCallback.bind(this));

    // this.magazineView = new MagazineView($('.view-container'));
    
    this.loadTitles();
}

module.exports = MagazineComponent;