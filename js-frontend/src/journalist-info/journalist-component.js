var journalistTemplate = require('./journalist.hbs');
var $ = require('jquery');
var journalistApi = require('../api/journalist-api');
var InfoComponent = require('./journ-info/journalist-info');
var ArticlesComponent = require('./journ-articles/journalist-articles');
var StatisticsComponent = require('./journ-statistics/journalist-statistics');
var componentObj = {
    InfoComponent: InfoComponent,
    ArticlesComponent: ArticlesComponent,
    StatisticsComponent: StatisticsComponent
};


/**
 * Компонент для управлением отображением инфомрации и
 * статистики журналиста.
 * @constructor
 * @param {JQueryElement} $parentElement - родитель, к которому будет добавлен шаблон информации
 */
function JournalistStatComponent($parentElement) {

    var componentData = {};
    var elementSelector = '#journalistInfo';
    var navigationSelector = '.nav-tabs';
    var returnButtonSelector = '#returnToSearchForm';
    var returnCallBack = null;


    /**
     * Событие на переключение вкладок меню 'Информация, Статистика, Статьи'
     * @param {Event} event
     */
    function toggleTabs(event) {
        var target = event.target;
        var componentPick;
        var component;
        while (target !== this) {
            if (target.tagName === 'LI') {
                componentPick = target.dataset.type + 'Component';
                $(this).find('a').removeClass('active');
                $(target).children().first().addClass('active');
                component = new componentObj[componentPick]($(elementSelector));
                component.setData(componentData);
                component.onActionInChildComponent(function onActionInChildComponent() {
                    console.log('В дочернем элементе произошло событие.');
                });
                return;
            }
            target = target.parentNode;
        }
    }

    /**
     * Событие на кнопку "Вернуться".
     * @param {Event} event
     */
    function returnToSearchForm(event) {
        event.preventDefault();
        if (typeof returnCallBack === 'function') {
            returnCallBack();
        }
    }

    ($parentElement)
        .on('click', navigationSelector, toggleTabs)
        .on('click', returnButtonSelector, returnToSearchForm);

    /**
     * Очищает контейнер родителя и добавляет шаблон journalistTemplate
     */
    function render() {
        $parentElement.empty().append(journalistTemplate({
            data: componentData
        }));
    }

    /**
     * Метод для добавления в родительский контейнер и установки
     * влкдаки 'Информация' по умолчанию
     * @param {string} id - id журналиста
     */
    this.appendComponent = function appendComponent(id) {
        var infoComponent;
        journalistApi.getJournalistInfo(id)
            .then(function response(journalistData) {
                componentData = journalistData;
                render();
                infoComponent = new componentObj.InfoComponent($journalistPage);
                infoComponent.setData(componentData);
                $(navigationSelector).find('a').first().addClass('active');
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
}

module.exports = JournalistStatComponent;

