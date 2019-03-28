var journalistTemplate = require('./journalist.hbs');
var $ = require('jquery');
var InfoComponent = require('./journ-info/journalist-info');
var StatisticsComponent = require('./journ-statistics/journalist-statistics');
var ArticlesComponent = require('./journ-articles/journalist-articles');
var JournalistService = require('../services/journalist-service');
var AlertBoxComponent = require('../alert-box/alert-box-component');
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
    var $componentsBody = {};
    var components = {};
    var $parentComponentBody;
    var returnButtonClickEventListener = null;
    var alert = new AlertBoxComponent();
    /**
     * Событие на переключение вкладок меню 'Информация, Статистика, Статьи'
     * @param {Event} event - объект события.
     */
    function toggleTabs(event) {
        var parent = event.currentTarget;
        var target = event.target;
        var pick;
        var bodyPick;
        var data;
        while (parent !== target) {
            if (target.tagName === 'LI') {
                data = target.dataset.type;
                bodyPick = $componentsBody[data];
                componentPick = data + 'Component';
                $(parent).find('a').removeClass('active');
                $(target).children().first().addClass('active');
                $parentComponentBody.children().hide();
                pick = components[componentPick];
                pick.setData(componentData);
                bodyPick.show();
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
        if (returnButtonClickEventListener) {
            returnButtonClickEventListener();
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
        $parentComponentBody = $parentElement.find('.components');
        $componentsBody.info = $parentElement.find('.journalist-info-content');
        $componentsBody.articles = $parentElement.find('.journalist-statistics-content');
        $componentsBody.statistics = $parentElement.find('.journalist-statistics-content');
        components.infoComponent = new InfoComponent($componentsBody.info);
        components.statisticsComponent = new StatisticsComponent($componentsBody.articles);
        components.articlesComponent = new ArticlesComponent($componentsBody.statistics);
    }

    /**
     * Установка функции обратного вызова для просмотра формы поиска журналистов и результатов поиска
     * @param {function} listener -вызывается, нажата кнопка вернуться к поиску журналиста
    */
    this.onReturnButtonClick = function onReturnButtonClick(listener) {
        returnButtonClickEventListener = listener;
    };

    /**
     * Метод для добавления в родительский контейнер и установки
     * влкдаки 'Информация' по умолчанию.
     * @param {string} id - id журналиста.
     */
    this.appendComponent = function appendComponent(id) {
        JournalistService
            .getJournalistInfo(id)
            .then(function handleResponse(journalistData) {
                componentData = journalistData;
                render();
                components.infoComponent.setData(componentData);
                $componentsBody.info.show();
            })
            .catch(function errorResponse(error) {
                alert.error(error);
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
