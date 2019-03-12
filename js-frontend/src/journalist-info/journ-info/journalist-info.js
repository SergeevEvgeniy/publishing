var infoTemplate = require('./journalist-info.hbs');

/**
 * Компонент для отображения личной информации о журналисте
 * @constructor
 * @param {JQuery} $parentElement 
 */
function InfoComponent($parentElement) {

    var componentData = {};
    var updateListener = null;

    function render() {
        $parentElement.empty().append(infoTemplate({
            data: componentData
        }));
    }

    render();

    this.setData = function (data) {
        componentData = data;
        render();
    };
}

module.exports = InfoComponent;
