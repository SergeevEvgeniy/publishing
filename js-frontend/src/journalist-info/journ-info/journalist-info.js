var infoTemplate = require('./journalist-info.hbs');

function InfoComponent($element) {

    var componentData = {};

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
}

module.exports = {
    InfoComponent: InfoComponent
};
