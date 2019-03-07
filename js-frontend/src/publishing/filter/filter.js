var filterTemplate = require('./filter.hbs');

var $ = require('jquery');

function Filter($rootElement, title) {
    this.$rootElement = $rootElement;

    this.title = title;
    this.productionTitles = [ ];
    this.filteredCallback = null;

    this.render();
}

Filter.prototype.setFilteredCallback = function (callBack) {
    this.filteredCallback = callBack;
};

Filter.prototype.componentDidMount = function () {
    $('#form-filter')
        .on('submit', event => {
            event.preventDefault();
            var form = event.currentTarget;

            var dateNumber = form.dateNumberFilter.value.split('/');
            var formData = {
                title: form.titleProductionFilter.value,
                date: dateNumber[0],
                number: dateNumber[1],
                article: form.articleTitleFilter.value,
            }

            // var formData = new FormData();
            // formData.append('title', form.titleProductionFilter.value);
            // formData.append('date', dateNumber[0]);
            // formData.append('number', dateNumber[1]);
            // formData.append('article', form.articleTitleFilter.value);
            
            // $(form)
            //  .find('input, select')
            //  .each(function() {
            //      formData[this.name] = $(this).val();
            //  });
            
            var $buttonElement = $(event.target)
                .find('.btn')
                .first()
                .val('Поиск')
                .addClass('disabled');

            var $spinner = $buttonElement
                .find('span')
                .first()
                .removeAttr('hidden');

            this.filteredCallback(formData, () => {
                $buttonElement
                    .val('Найти')
                    .removeClass('disabled');
                $spinner.attr('hidden', 'hidden');
            });
        });
};

Filter.prototype.setProductionTitles = function (titles) {
    this.productionTitles = titles;
    this.render();
};

Filter.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(filterTemplate({
            titleProduction: this.title,
            titles: this.productionTitles,
        }));
    this.componentDidMount();
};

module.exports = Filter;
