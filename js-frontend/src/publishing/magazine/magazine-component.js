var magazineContainerTemplate = require('./magazine-container.hbs');

var $ = require('jquery');
var FilterComponent = require('../../publishing-filter/filter-component');
var FilterResultComponent = require('../../publishing-filter/filter-result-component');

function MagazineComponent($parentElement) {
    var filterComponent;
    var filterResultComponent;

    function onFilterSubmitListener(formData, next) {
        console.log('submit filter form');
        setTimeout(next(), 5000);
    }

    function onSelectMagazineIssueListener(issueId) {
        console.log('select magazine issue' + issueId);
    }

    this.render = function render() {
        $parentElement
            .empty()
            .append(magazineContainerTemplate());

        filterComponent = new FilterComponent($('.filter-container'), 'Журнал');
        filterComponent.setFilterSubmitListener(onFilterSubmitListener.bind(this));
        filterResultComponent = new FilterResultComponent($('.filter-result-container'));
        filterResultComponent.setShowIssueListener(onSelectMagazineIssueListener.bind(this));
    };

    this.render();
}

module.exports = MagazineComponent;
