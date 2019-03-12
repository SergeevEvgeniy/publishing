
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
        while (target !== this) {
            if (target.tagName === 'LI') {
                var componentPick = target.dataset.type + 'Component';
                $(this).find('a').removeClass('active');
                $(target).children().first().addClass('active');
                var component = new componentObj[componentPick]($(elementSelector));
                component.setData(componentData);
                component.onActionInChildComponent(function () {
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
    this.appendComponent = function (id) {
        console.log('Вызван appendComponent')
        journalistApi.getJournalistInfo(id)
        .then(function (journalistData) {
            componentData = journalistData;
            render();
            console.log(journalistData)
            var infoComponent = new componentObj.InfoComponent($journalistPage);
            infoComponent.setData(componentData);
            $(navigationSelector).find('a').first().addClass('active');
        })
        .catch(function (error) {
            console.log(error);
        })
    }
    /**
     * Установка функции обратного вызова для закрытия вкладки
     * @param {function} listener - функция обратного вызова
     */
    this.onReturnSearchForm = function (listener) {
        returnCallBack = listener;
    };
}

module.exports = JournalistStatComponent;

