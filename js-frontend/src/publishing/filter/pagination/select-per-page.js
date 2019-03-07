//template hbs
var selectPerPageTemplate = require('./select-per-page.hbs');

var $ = require('jquery');

function SelectPerPage($rootElement) {
    this.$rootElement = $rootElement;
    this.selectPerPageCallback = null;
}

SelectPerPage.prototype.selectedPerPage = function (event) {
    var target = event.target;
    this.selectPerPageCallback(target.value);
};

SelectPerPage.prototype.setAmountRecord = function (amountRecord) {
    if (amountRecord === 0) {
        return;
    }
    this.render();
};

SelectPerPage.prototype.setSelectPerPageCallback = function (callback) {
    this.selectPerPageCallback = callback;
};  

SelectPerPage.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(selectPerPageTemplate());

    $('.select-per-page').change(event => this.selectedPerPage(event));
};

module.exports = SelectPerPage;