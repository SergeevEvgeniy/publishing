var infoTemplate = require('./journalist-info.hbs');

/**
 * Компонент для отображения личной информации о журналисте
 * @constructor
 * @param {jQuery} $parentElement - родитель, к которому будет добавлен шаблон информации о журналисте
 */
function InfoComponent($parentElement) {
    /**
     * @type {JournalistInfo}
     */
    var componentData = {};

    /**
     * Фукнция для добавления шаблона articlesTemplate в родительский контейнер.
     */
    function render() {
        $parentElement.empty().append(infoTemplate({
            data: componentData
        }));
    }
    /**
     * Для сохранения информации о журналисте.
     * @param {JournalistInfo} data - данные, которые будут записаны в componentData.
     */
    this.setData = function setData(data) {
        componentData = data;
        render();
    };
}

module.exports = InfoComponent;

/**
 * Complete info for a Journalist.
 * @typedef {object} JournalistInfo
 * @property {string} firstName
 * @property {string} lastName
 * @property {string} middleName
 * @property {number} birthYear
 */
