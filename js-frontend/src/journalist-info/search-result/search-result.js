var searchResultTemplate = require('./search-result.hbs');
/**
 * Компонент для отображения таблицы с результами поиска.
 * @param {jQuery} $element - контейнер родителя к которому добавится таблица результата
 */
function SearchResultComponent($element) {
    /**
     * Очищает контейнер родителя и добавляет шаблон таблицы в него
     */
    this.render = function render() {
        $element.empty().append(searchResultTemplate({
        }));
    };
}

module.exports = SearchResultComponent;
