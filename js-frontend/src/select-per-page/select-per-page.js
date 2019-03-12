var selectPerPageTemplate = require('./select-per-page.hbs');
var $ = require('jquery');

/**
 * Создаёт компонент позволяющий выбрать количество элементов на странице
 * @constructor
 * @param {JQuery element} $parentElement - jqeury элемент-контейнер для размещения компонента
 */
function SelectPerPage($parentElement) {
    var $selectPerPageWrapper = $('<div />');
    var handleSelectedPerPageCallback = null;

    function handleSelectedPerPage(event) {
        if (!!handleSelectedPerPageCallback) {
            return;
        }
        handleSelectedPerPageCallback(+event.target.value);
    }

    /**
     * Метод устанавливающий callback, вызывающийся после изменения количества отображаемых
     * элементов на странице
     * @param {function} callback - callback принимающий в качестве параметра количество
     *                              элементов на странице
     */
    this.setSelectPerPageCallback = function setSelectPerPageCallback(callback) {
        this.selectPerPageCallback = callback;
    };

    $selectPerPageWrapper.on('change', 'ul', handleSelectedPerPage(event));

    /**
     * Метод отображающий компонент в переданном $parentElement контейнере
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
