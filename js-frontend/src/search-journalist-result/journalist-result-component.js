var resultTemplate = require('./journalist-result.hbs');

/**
 * Компонент поиска журналистов.
 * @constructor
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function JournalistResultComponent($parentElement) {
    var data = {
        journalistList: []
    };
    var onJournalistInfoClickEventListener = null;

    function render() {
        $parentElement.empty().append(resultTemplate(data));
    }

    $parentElement.on('click', '.journalist-info', function onJournalistInfoClickEvent(event) {
        var journalistName = event.target.closest('tr').firstElementChild.textContent;
        onJournalistInfoClickEventListener(journalistName);
    });

    /**
    * Установка функции обратного вызова для просмотра информации о журналисте
    * @param {function} listener -вызывается, когда нажата кнопка просмотра информации о журналисте
     * Отправляет аргумент - полное имя жкрналиста
    */
    this.onJournalistInfoButtonClick = function onJournalistInfoButtonClick(listener) {
        onJournalistInfoClickEventListener = listener;
    };

    /**
     * Установка данных о журналистах.
     * @param  {Array} journalistList Данные с информацией о журналистах.
     */
    this.setJournalistList = function setJournalistList(journalistList) {
        data.journalistList = journalistList;
        render();
    };

    /**
     * Отрисовка компонента.
     */
    this.render = render;
}

module.exports = JournalistResultComponent;
