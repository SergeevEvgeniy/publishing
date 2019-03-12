
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
 * @param {JQueryElement} $element - родитель, к которому будет добавлен шаблон информации
 */

function JournalistStatComponent($element) {

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


    ($element)
        .on('click', navigationSelector, toggleTabs)
        .on('click', returnButtonSelector, returnToSearchForm);

    /**
     * Очищает контейнер родителя и добавляет новый шаблон с 
     * параметром data
     */
    function render() {
        $element.empty().append(journalistTemplate({
            data: componentData
        }));
    }

    /**
     * Метод для отображения вкладки 'Информация' - по умолчанию
     * @param {string} id - id журналиста
     */
    this.appendComponent = function (id) {
        console.log('Вызван appendComponent')
        render();
        journalistApi.getJournalistInfo(id)
        .then(function (journalistData) {
            componentData = journalistData;
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
     * Подписка на событие??
     * @param {function} callBack - функция обратного вызова
     */
    this.onReturnSearchForm = function (callBack) {
        returnCallBack = callBack;
    }
}

module.exports = JournalistStatComponent;

