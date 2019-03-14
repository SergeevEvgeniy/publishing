var resultTemplate = require('./journalist-result.hbs');
var JournalistStatComponent = require('../journalist-info/journalist-component');
var data = {
    componentId: 'journalistResult',
    journalistList: []
};
/**
 * Компонент поиска журналистов.
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 * @returns {void}
 */
function JournalistResultComponent($parentElement) {
    $parentElement.append($('<div>', {
        id: data.componentId
    }));
    function render() {
        $parentElement.find('#' + data.componentId).empty().append(resultTemplate(data));
    }

    $parentElement.on('click', '.journalist-info', function onJournalistInfoClickEvent(event) {
        var target = event.target;
        var journalistName = target.closest('tr').firstElementChild.textContent;
        var journalistStatComponent = new JournalistStatComponent($parentElement);
        journalistStatComponent.appendComponent(journalistName);
    });
    /**
     * Установка данных о журналистах.
     * @param  {Array} journalistList Данные с информацией о журналистах.
     * @returns {function} journalistList Отрисовка компонента
     */
    this.setJournalistList = function setJournalistList(journalistList) {
        data.journalistList = journalistList;
        render();
    };
    /**
     * @returns {function} Отрисовка компонента
     */
    this.render = render;
}

module.exports = JournalistResultComponent;
