var picklistTemplate = require('./picklist.hbs');

/**
 * Компонент для отображения выпадающего списка
 * @param $parentElement jQuery element в котором расплагаеться выпадающий список
 * @param name Строка присваивается атрибуту name выпадающего списка
 * @param defaultText Строка представляет собой значение оп умолчанию для выпадающего списка
 * @constructor
 */
function PicklistComponent($parentElement, name, defaultText) {
    /**
     * Массив значений выпадающего списка
     * Каждое значение содержит два параметра
     * value уникальный идентификатор елемента
     * text название елемента
     * @type {Array}
     */
    var elementList = [];
    /**
     * Задает список элементов
     * @param list
     */
    this.setElementList = function setElementList(list) {
        elementList = list;
    };
    /**
     *Устанавливает значение выпадающего списка к значению по умолчанию
     */
    this.selectDefault = function selectDefault() {
        $parentElement.find('select').prop('selectedIndex', 0);
    };
    /**
     * Отображает выпадающий список на странице
     */
    this.render = function render() {
        $parentElement.empty().append(picklistTemplate({
            name: name,
            defaultText: defaultText,
            elementList: elementList
        }));
    };
}

module.exports = PicklistComponent;
