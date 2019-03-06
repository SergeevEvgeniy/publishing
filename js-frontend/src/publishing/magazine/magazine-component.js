//template hbs
const magazineContainerTemplate = require('./magazine-container.hbs');

//components
const $ = require('jquery');
const Filter = require('../filter/filter');
const FilterResult = require('../filter/filter-result');
const Pagination = require('../filter/pagination/pagination');
const SelectPerPage = require('../filter/pagination/select-per-page');
const MagazineView = require('../view/magazine-view');

//api
// const magazineAPI = require('../../api/magazine-api');

function MagazineComponent($rootElement) {
    this.$element = $rootElement;
    this.render();
}

MagazineComponent.prototype.filteredCallback = function (foundedMagazines, callback) {
    // magazineAPI.loadMagazinesFilter(foundedMagazines)
    //     .then(res => {
    //         return res.json();
    //     })
    //     .then(magazines => {
    //         this.filterResult.setData(magazines);
    //         this.pagination.setAmountRecord(magazines.length);
    //         this.pagination.setCurrentPage(1);
    //         this.selectPerPage.setAmountRecord(magazines.length);
    //         callback();
    //     })
    //     .catch(err => {
    //         console.log(err);
    //     });
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

MagazineComponent.prototype.componentDidMount = function () {
    // magazineAPI.loadMagazineTitle()
    //     .then(res => {
    //         return res.json();
    //     })
    //     .then(magazineTitle => {
    //         this.filter.setProductionTitles(magazineTitle);
    //     })
    //     .catch(err => {
    //         console.log(err);
    //     });
};

MagazineComponent.prototype.render = function () {
    this.$element
        .empty()
        .append(magazineContainerTemplate());
    
    this.filter = new Filter($('.filter-container'), 'Журнал');
    this.filter.setFilteredCallback(this.filteredCallback.bind(this));
    
    this.filterResult = new FilterResult($('.filter-result-container'));
    this.filterResult.setShowIssueCallback(this.showMagazineCallback.bind(this));

    this.pagination = new Pagination($('.pagination-container'), 0);
    this.pagination.setSelectPageCallback(this.selectPageCallback.bind(this));

    this.selectPerPage = new SelectPerPage($('.select-per-page-container'));
    this.selectPerPage.setSelectPerPageCallback(this.selectPerPageCallback.bind(this));

    this.magazineView = new MagazineView($('.view-container'));
    
    this.componentDidMount();
}

module.exports = MagazineComponent;