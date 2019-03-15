var filterTemplate = require('./filter.hbs');

function Filter($parentElement, publicationType) {
    var publicationsTitles = [{
        title: '1',
        id: 1,
    },
    {
        title: '2',
        id: 2,
    },
    {
        title: '3',
        id: 3,
    },
    {
        title: '4',
        id: 4,
    }];
    var onFilterSubmitListener = null;
    var formData = null;

    var inputs = {
        publicationTitle: '3',
        dateNumber: '',
        articleTitle: '',
    };

    var isSubmiting = false;

    function onFilterFormSubmitEvent(event) {
        event.preventDefault();
        formData = new FormData();
        formData.append('publicationTitle', event.currentTarget.publicationTitleFilter.value);
        formData.append('dateNumber', event.currentTarget.dateNumberFilter.value);
        formData.append('article', event.currentTarget.articleTitleFilter.value);

        onFilterSubmitListener(formData);
        event.currentTarget.render();
    }

    function onInputChangeEvent(prop) {
        return function onChangeEvent(event) {
            inputs[prop] = $(event.target).val();
        };
    }

    this.setFilterSubmitListener = function setFilterSubmitListener(listener) {
        onFilterSubmitListener = listener;
    };

    this.setPublicationsTitles = function setPublicationsTitles(titles) {
        publicationsTitles = titles;
        this.render();
    };

    $parentElement.on('submit', '#form-filter', onFilterFormSubmitEvent.bind(this));
    $parentElement.on('change', '#publicationTitleFilter', onInputChangeEvent('publication'));
    $parentElement.on('change', '#dateNumberFilter', onInputChangeEvent('dateNumber'));
    $parentElement.on('change', '#articleTitleFilter', onInputChangeEvent('article'));

    this.render = function render() {
        $parentElement
            .empty()
            .append(filterTemplate({
                typePublication: publicationType,
                publicationsTitles: publicationsTitles,
                inputs: inputs,
                isSubmiting: isSubmiting,
            }));
    };

    this.render();
}

module.exports = Filter;
