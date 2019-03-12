var statisticsTemplate = require('./journalist-statistics.hbs');


function StatisticsComponent($element) {
    var componentData = {};
    var updateCallBack = null;

    function render() {
        $element.empty().append(statisticsTemplate({
            data: componentData
        }));
    }

    this.setData = function (data) {
        componentData = data;
        render();
    };
    this.onActionInChildComponent = function (callBack) {
        updateCallBack = callBack;
    };

}

module.exports = StatisticsComponent;
