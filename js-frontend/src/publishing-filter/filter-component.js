var filterTemplate = require('./filter.hbs');

/**
 * Создаёт компонент позволяющий осуществять поиск номеров
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 * @param {String} publicationType - название вида публикации "Журнал" или "Газета"
 */
function Filter($parentElement, publicationType) {
    var publications = [];
    var onFilterSubmitListener = null;
    var loading = false;
    var inputs = {
        publicationTitle: '',
        numberDate: '',
        articleTitle: '',
    };

    /**
     * Отрисовка компонента
     */
    function render() {
        $parentElement
            .empty()
            .append(filterTemplate({
                publicationType: publicationType,
                publications: publications,
                loading: loading,
                inputs: inputs,
            }));
    }

    /**
     * Обработчик события на отправку формы фильтрации
     * @param {Object} event содержит свойства произошедшего события
     */
    function onFilterFormSubmitEvent(event) {
        event.preventDefault();
        loading = true;

        onFilterSubmitListener($(event.target).serializeArray(), function stopLoading() {
            loading = false;
            render();
        });
        render();
    }

    /**
     * Обработчик события на изменение полей формы
     * @param {Object} event содержит свойства произошедшего события
     */
    function onInputChangeEvent(event) {
        var $target = $(event.target);
        var inputName = $target.attr('name');
        inputs[inputName] = $target.val();
        publications.forEach(function enumerationTitles(publication, index) {
            publications[index].selected = inputs.publicationTitle === publication.title;
        });
    }

    /**
     * Установка метода обратного вызова на поиск номеров
     * @param {Function} listener - вызывается, когда нажата кнопка "Найти"
     */
    this.setFilterSubmitListener = function setFilterSubmitListener(listener) {
        onFilterSubmitListener = listener;
    };

    /**
     * Установка публикаций, для отображения в combobox
     * @param {Array.<{id: Number, title: String}>} titles - массив объектов публикаций с именем и id
     */
    this.setPublications = function setPublications(titles) {
        publications = titles;
        render();
    };

    $parentElement.on('submit', '#form-filter', onFilterFormSubmitEvent);
    $parentElement.on('change', '#publicationTitle', onInputChangeEvent);
    $parentElement.on('change', '#numberDate', onInputChangeEvent);
    $parentElement.on('change', '#articleTitle', onInputChangeEvent);

    render();
}

module.exports = Filter;
