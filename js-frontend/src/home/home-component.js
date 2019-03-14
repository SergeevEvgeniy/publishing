var homeTemplate = require('./home.hbs');

/**
 * Компонент главной страницы.
 * @param  {Object} $parentElement Элемент-контейнер для размещения компонента.
 * @returns {void}
 */
function HomeComponent($parentElement) {
    /**
     * @returns {function} Отрисовка компонента
     */
    this.render = function render() {
        $parentElement.append(homeTemplate({
            name: 'Люк Скайвокер'
        }));
    };
}

module.exports = HomeComponent;
