var statisticsTemplate = require('./journalist-statistics.hbs');

/**
 * Компонент для отображения информации о рубриках и статьях журналиста
 * @constructor
 * @param {JQuery} $parentElement - родитель, к которому будет добавлен шаблон статистики
 */
function StatisticsComponent($parentElement) {
    var componentData = {};

    function render() {
        $parentElement.empty().append(statisticsTemplate({
            data: componentData
        }));
    }

    this.setData = function setData(data) {
        componentData = data;
        render();
    };
}

module.exports = StatisticsComponent;
