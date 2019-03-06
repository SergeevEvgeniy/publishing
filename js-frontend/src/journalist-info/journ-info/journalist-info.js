var infoTemplate = require('./journalist-info.hbs');

function InfoComponent($element) {

    var componentData = {};
    var updateCallBack = null;
    function render() {
        $element.empty().append(infoTemplate({
            data: componentData
        }));
    }

    render();

    this.setData = function (data) {
        componentData = data;
        render();
    };
    this.onActionInChildComponent = function (callBack) {
        updateCallBack = callBack;
    };

}

module.exports = {
    InfoComponent: InfoComponent
};
