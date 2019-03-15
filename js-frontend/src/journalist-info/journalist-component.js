var journalistTemplate = require('./journalist.hbs');
var $ = require('jquery');
var journalistApi = require('../api/journalist-api');
var InfoComponent = require('./journ-info/journalist-info');

/**
 * Компонент для управлением отображением инфомрации и
 * статистики журналиста.
 * @constructor
 * @param {jQuery} $parentElement - родитель, к которому будет добавлен шаблон информации
 */
function JournalistStatComponent($parentElement) {
    var componentData = {};
    var navigationSelector = '.nav-tabs';
    var returnButtonSelector = '#returnToSearchForm';
    var infoComponent;
    var returnCallBack = null;

    /**
     * Событие на переключение вкладок меню 'Информация, Статистика, Статьи'
     * @param {Event} event - объект события.
     */
    function toggleTabs(event) {
        var parent = event.currentTarget;
        var target = event.target;
        while (parent !== target) {
            if (target.tagName === 'LI') {
                componentPick = target.dataset.type;
                $(parent).find('a').removeClass('active');
                $(target).children().first().addClass('active');
                console.log(componentPick);
                return;
            }
            target = target.parentNode;
        }
    }

    /**
     * Событие на кнопку "Вернуться".
     * @param {Event} event - объект события.
     */
    function returnToSearchForm(event) {
        event.preventDefault();
        if (typeof returnCallBack === 'function') {
            returnCallBack();
        }
    }
    $parentElement
        .on('click', navigationSelector, toggleTabs)
        .on('click', returnButtonSelector, returnToSearchForm);

    /**
     * Очищает контейнер родителя и добавляет шаблон journalistTemplate
     */
    function render() {
        $parentElement.empty().append(journalistTemplate({
            data: componentData
        }));
        infoComponent = new InfoComponent($parentElement.find('.journalist-info-content'));
    }

    /**
     * Метод для добавления в родительский контейнер и установки
     * влкдаки 'Информация' по умолчанию.
     * @param {string} id - id журналиста.
     */
    this.appendComponent = function appendComponent(id) {
        journalistApi.getJournalistInfo(id)
            .then(function response(journalistData) {
                componentData = journalistData;
                render();
                infoComponent.setData(componentData);
            })
            .catch(function errorResponse(error) {
                console.log(error);
            });
    };

    /**
     * Установка функции обратного вызова для закрытия вкладки
     * @param {function} listener - функция обратного вызова
     */
    this.onReturnSearchForm = function onReturnSearchForm(listener) {
        returnCallBack = listener;
    };

    /**
    * Отрисовка компонента.
    */
    this.render = render;
}

module.exports = JournalistStatComponent;
