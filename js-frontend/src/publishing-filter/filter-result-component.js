var filterResultTemplate = require('./filter-result.hbs');

var $ = require('jquery');

/**
 * Создаёт компонент отображающий результаты поиска в виде таблицы.
 * Один столбец - название, кнопка - отображать вызывает переданную функцию обратного
 * вызова.
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function FilterResult($parentElement) {
    var issues = [];
    var visibleIssues = [];
    var perPage = 5;
    var currentPage = 1;
    var onShowIssueListener = null;

    var startItemIndex;
    var endItemIndex;

    /**
     * Отрисовка компонента
     */
    function render() {
        $parentElement
            .empty()
            .append(filterResultTemplate({
                issues: visibleIssues,
            }));
    }

    function onSelectIssueEvent(event) {
        var tr = $(event.target)
            .closest('tr')
            .data('id');
        onShowIssueListener(tr);
    }

    function recount() {
        if (issues.length === 0) {
            visibleIssues = [];
            render();
            return;
        }
        startItemIndex = (currentPage - 1) * perPage;
        endItemIndex = currentPage * perPage;
        endItemIndex = (endItemIndex > issues.length) ? issues.length : endItemIndex;

        visibleIssues = issues.slice(startItemIndex, endItemIndex);
        render();
    }

    /**
     * Установка номеров которые нужно отобразить
     * @param {Array.<{id: Number, title: String}>} newIssues - номера
     */
    this.setIssues = function setIssues(newIssues) {
        issues = newIssues;
        recount();
        render();
    };

    /**
     * Установка количества элементов на странице
     * @param {Number} newPerPage - количество элементов на странице
     */
    this.setPerPage = function setPerPage(newPerPage) {
        perPage = newPerPage;
        currentPage = 1;
        recount();
        render();
    };

    /**
     * Установка текущей страницы
     * @param {Number} newCurrentPage - текущая страница
     */
    this.setCurrentPage = function setCurrentPage(newCurrentPage) {
        currentPage = newCurrentPage;
        recount();
        render();
    };

    /**
     * Установка метода обратного вызова на выбор номера
     * @param {function} listener - вызывается, когда был выбран номер
     */
    this.setShowIssueListener = function setOnSelectIssueListener(listener) {
        onShowIssueListener = listener;
    };

    $parentElement.on('click', 'td button', onSelectIssueEvent.bind(this));

    render();
}

module.exports = FilterResult;
