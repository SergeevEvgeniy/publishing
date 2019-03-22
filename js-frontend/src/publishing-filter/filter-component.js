var filterTemplate = require('./filter.hbs');

/**
 * Создаёт компонент позволяющий осуществять поиск номеров
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 * @param {String} publicationType - название вида публикации "Журнал" или "Газета"
 */
function Filter($parentElement, publicationType) {
    var publicationsTitles = [];
    var onFilterSubmitListener = null;
    var isSubmitting = false;

    /**
     * Отрисовка компонента
     */
    function render() {
        $parentElement
            .empty()
            .append(filterTemplate({
                typePublication: publicationType,
                publicationsTitles: publicationsTitles,
                isSubmitting: isSubmitting,
            }));
        console.log(isSubmitting);
    }

    /**
     * Обработчик события на отправку формы фильтрации
     * @param {Object} event содержит свойства произошедшего события
     */
    function onFilterFormSubmitEvent(event) {
        event.preventDefault();
        isSubmitting = true;

        onFilterSubmitListener($(event.target).serializeArray(), function foundIssue() {
            isSubmitting = false;
            render();
        });
        render();
    }

    /**
     * Установка метода обратного вызова на поиск номеров
     * @param {function} listener - вызывается, когда нажата кнопка "Найти"
     */
    this.setFilterSubmitListener = function setFilterSubmitListener(listener) {
        onFilterSubmitListener = listener;
    };

    /**
     * Установка наваний публикаций, для отображения в combobox
     * @param {Array.<{id: Number, title: String}>} titles - массив объектов публикаций с именем и id
     */
    this.setPublicationsTitles = function setPublicationsTitles(titles) {
        publicationsTitles = titles;
        render();
    };

    $parentElement.on('submit', '#form-filter', onFilterFormSubmitEvent);

    render();
}

module.exports = Filter;
