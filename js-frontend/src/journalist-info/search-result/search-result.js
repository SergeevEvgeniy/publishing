var searchResultTemplate = require('./search-result.hbs');
/**
 * Компонент для отображения таблицы с результами поиска.
 * @param {jQuery} $element - контейнер родителя к которому добавится таблица результата
 */
function SearchResultComponent($element) {
    var componentData = {};
    /**
     * Очищает контейнер родителя и добавляет шаблон таблицы в него
     */
    function render() {
        $element.empty().append(searchResultTemplate({
            data: componentData
        }));
    }

    this.setData = function setData(data) {
        componentData = data;
        render();
    };
}

module.exports = SearchResultComponent;
