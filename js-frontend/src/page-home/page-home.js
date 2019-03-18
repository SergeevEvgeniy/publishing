var homeTemplate = require('./home.hbs');

/**
 * Компонент главной страницы.
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function HomeComponent($parentElement) {
    $parentElement.append(homeTemplate({
        name: 'Люк Скайвокер'
    }));
}

module.exports = HomeComponent;
