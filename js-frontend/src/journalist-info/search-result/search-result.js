var $ = require('jquery');
var searchResultTemplate = require('./search-result.hbs');
var paginationComponent = require('../../pagination/pagination');
/**
 * Компонент для отображения таблицы с результами поиска.
 * @param {JQueryElement} $element - контейнер родителя к которому добавится таблица результата
 */
function SearchResultComponent($element) {

    var componentData = {};

    /**
     * Очищает контейнер родителя и добавляет шаблон таблицы в него
     */
    this.render = function () {
        $element.empty().append(searchResultTemplate({
        }));
    };
}

module.exports = SearchResultComponent;