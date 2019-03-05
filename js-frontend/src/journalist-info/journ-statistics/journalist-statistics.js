var statisticsTemplate = require('./journalist-statistics.hbs');


function StatisticsComponent($element) {
    var componentData = {};

    function render() {
        $element.empty().append(statisticsTemplate({
            data: componentData
        }));
    }

    this.setData = function (data) {
        componentData = data;
        render();
    };
}

module.exports = {
    StatisticsComponent: StatisticsComponent
};
