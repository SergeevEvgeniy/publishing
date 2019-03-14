var selectPerPageTemplate = require('./select-per-page.hbs');
var $ = require('jquery');

/**
 * Создаёт компонент позволяющий выбрать количество элементов на странице
 * @constructor
 * @param {JQuery element} $parentElement - элемент-контейнер для размещения компонента
 */
function SelectPerPage($parentElement) {
    var $selectPerPageWrapper = $('<div />');
    var selectPerPageChangeListener = null;

    function onSelectPerPageChangeEvent(event) {
        if (!!selectPerPageChangeListener) {
            return;
        }
        selectPerPageChangeListener(+event.target.value);
    }

    /**
     * Установка метода обратного вызова на изменение количества записей на странице
     * @param {function} listener - вызывается, когда было изменено количество элементов на странице
     * Отправляет аргумент - количество элементов на странице
     */
    this.onSelectPerPageChange = function onSelectPerPageChange(listener) {
        selectPerPageChangeListener = listener;
    };

    $selectPerPageWrapper.on('change', 'ul', onSelectPerPageChangeEvent);

    /**
     * Отрисовка компонента
     */
    this.render = function render() {
        $selectPerPageWrapper
            .empty()
            .append(selectPerPageTemplate());
        $parentElement
            .empty()
            .append(selectPerPageWrapper);
    };
}

module.exports = SelectPerPage;
