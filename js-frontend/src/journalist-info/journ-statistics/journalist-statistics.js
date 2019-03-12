var statisticsTemplate = require('./journalist-statistics.hbs');


function StatisticsComponent($parentElement) {
    var componentData = {};
    var updateCallBack = null;

    function render() {
        $parentElement.empty().append(statisticsTemplate({
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
