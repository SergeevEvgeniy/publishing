var $ = require('jquery');
var searchResultTemplate = require('./search-result.hbs');
var { PaginationComponent } = require('../pagination/pagination');

function SearchResultComponent($element) {

    var componentData = {};
    var tbodySelector = '#searchResults';
    var paginationListSelector = '.pagination';
    var togglerListSelector = '.toggler';
    function render() {
        $element.empty().append(searchResultTemplate({
            data: componentData
        }));
    }
    this.setData = function (data) {
        componentData = data;
        render();
        var paginationComponent = new PaginationComponent($(tbodySelector), $(paginationListSelector), $(togglerListSelector));
        paginationComponent.renderTable(componentData);
    };
}

module.exports = {
    SearchResultComponent: SearchResultComponent
};