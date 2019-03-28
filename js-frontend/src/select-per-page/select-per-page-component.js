var selectPerPageTemplate = require('./select-per-page.hbs');

/**
 * Создаёт компонент позволяющий выбрать количество элементов на странице
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function SelectPerPage($parentElement) {
    var isShown = false;
    var currentPerPage = 5;
    var perPages = [
        {
            class: 'd-none',
            value: 5,
        },
        {
            class: '',
            value: 10,
        },
        {
            class: '',
            value: 25,
        },
        {
            class: '',
            value: 50,
        },
    ];
    var selectPerPageChangeListener = null;
    var indexPerPage;
    var target;

    /**
     * Отрисовка компонента
     */
    function render() {
        $parentElement
            .empty()
            .append(selectPerPageTemplate({
                perPage: currentPerPage,
                perPages: perPages,
                isShown: isShown,
            }));
    }

    /**
     * Обработчик события на изменения количества записей на странице
     * @param {object} event содержит свойства произошедшего события
     */
    function onSelectPerPageChangeEvent(event) {
        if (!selectPerPageChangeListener) {
            return;
        }
        target = $(event.target);
        currentPerPage = target.data('per-page');
        indexPerPage = target.data('per-page-index');
        perPages.forEach(function clearClassPerPage(item, index) {
            perPages[index].class = '';
        });
        perPages[indexPerPage].class = 'd-none';
        selectPerPageChangeListener(+currentPerPage);
        render();
    }

    /**
     * Установка метода обратного вызова на изменение количества записей на странице
     * @param {function} listener - вызывается, когда было изменено количество элементов на странице
     * Отправляет аргумент - количество элементов на странице
     */
    this.onSelectPerPageChange = function onSelectPerPageChange(listener) {
        selectPerPageChangeListener = listener;
    };

    /**
     * Установка видимости компонента
     * @param {boolean} status видимость компонента
     */
    this.setVisible = function setVisible(status) {
        isShown = status;
        render();
    };

    $parentElement.on('click', 'li a', onSelectPerPageChangeEvent);
    render();
}

module.exports = SelectPerPage;
