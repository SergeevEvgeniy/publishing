var filterTemplate = require('./filter.hbs');

var $ = require('jquery');

function Filter($rootElement, typePublication) {
    this.$rootElement = $rootElement;

    this.typePublication = typePublication;
    this.publicationsTitles = [ ];
    this.filterSubmitCallback = null;

    this.render();
}

Filter.prototype.setFilterSubmitCallback = function (callBack) {
    this.filterSubmitCallback = callBack;
};

Filter.prototype.submitFromFilter = function (event) {
    event.preventDefault();
    var form = event.currentTarget;

    var dateNumber = form.dateNumberFilter.value.split('/');
    var formData = {
        publicationTitle: form.titlePublicationFilter.value,
        date: dateNumber[0],
        number: dateNumber[1] || '',
        articleTitle: form.articleTitleFilter.value,
    }

    // var formData = new FormData();
    // formData.append('title', form.titlePublicationFilter.value);
    // formData.append('date', dateNumber[0]);
    // formData.append('number', dateNumber[1]);
    // formData.append('article', form.articleTitleFilter.value);
    
    // $(form)
    //  .find('input, select')
    //  .each(function() {
    //      formData[this.name] = $(this).val();
    //  });
    
    var $buttonElement = $(event.target)
        .find('.btn-find')
        .addClass('disabled');

    var $spinner = $buttonElement
        .find('.spinner')
        .removeAttr('hidden');

    var $faSearch = $('.btn-find .fa-search')
        .attr('hidden', 'hidden');

    this.filterSubmitCallback(formData, () => {
        $buttonElement.removeClass('disabled');
        $spinner.attr('hidden', 'hidden');
        $faSearch.removeAttr('hidden', 'hidden');
    });
};

Filter.prototype.setPublicationsTitles = function (titles) {
    this.publicationTitles = titles;
    this.render();
};

Filter.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(filterTemplate({
            typePublication: this.typePublication,
            titles: this.publicationTitles,
        }));
   
    $('#form-filter').on('submit', event => this.submitFromFilter(event));
};

module.exports = Filter;
