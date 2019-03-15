var statisticsTemplate = require('./journalist-statistics.hbs');

/**
 * Компонент для отображения информации о рубриках и статьях журналиста
 * @constructor
 * @param {jQuery} $parentElement - родитель, к которому будет добавлен шаблон статистики
 */
function StatisticsComponent($parentElement) {
    var componentData = {};

    /**
     * Добавляет в контейнер родителя шаблон статистики журналиста по его статьям.
     */
    function render() {
        $parentElement.empty().append(statisticsTemplate({
            data: componentData
        }));
    }

    /**
     * Для сохранения данных о статьях журналиста.
     * @param {Object} data - данные о статьях журналиста.
     */
    this.setData = function setData(data) {
        componentData = data;
        render();
    };
}

module.exports = StatisticsComponent;
