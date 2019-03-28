var $ = require('jquery');
var articlesTemplate = require('./journalist-articles.hbs');
var releaseTemplate = require('./release.hbs');
var SearchResultComponent = require('../search-result/search-result');
var JournalistService = require('../../services/journalist-service');
var AlertBoxComponent = require('../../alert-box/alert-box-component');
/**
 * Компонент для вкладки 'Поиск статей'.
 * @constructor
 * @param {jQuery} $parentElement - родитель, к которому будет добавлен шаблон.
 */
function ArticlesComponent($parentElement) {
    var componentData = {};
    var editionSelector = '#edition';
    var releaseBodySelector = '#releaseBody';
    var searchBodySelector = '#searchResultBody';
    var updateCallBack = null;
    var alert = new AlertBoxComponent();
    /**
     * Метод для обработки события при нажатии кнопки 'Найти'.
     * Добавляет к родительскому компоненту шаблон результатов поиска статей.
     * @param {Event} event - объект события
     */
    function findArticles(event) {
        var searchResult;
        event.preventDefault();
        searchResult = new SearchResultComponent($(searchBodySelector));
        searchResult.render();
    }

    /**
     * Метод для обработки события при нажатии кнопки 'Очистить'
     * Очищает форму, удаляет выпадающий список 'Выпуск',
     * шаблон результатов поиска.
     * @param {Event} event - объект события.
     */
    function clearForm(event) {
        event.preventDefault();
        event.target.closest('form').reset();
        $(searchBodySelector).empty();
        $(releaseBodySelector).empty();
        if (typeof updateCallBack === 'function') {
            updateCallBack();
        }
    }
    /**
     * Метод для добавления шаблона выпадающего списка на форму.
     */
    function getIssue() {
        JournalistService
            .getIssueList()
            .then(function handleResponse(response) {
                $(releaseBodySelector).append(releaseTemplate({
                    data: response
                }));
            })
            .catch(function handleError(error) {
                alert.error(error);
            });
    }

    ($parentElement).off().on('click', '#findArticle', findArticles)
        .on('click', '#clearFields', clearForm)
        .on('change', editionSelector, getIssue);

    /**
     * Фукнция для добавления шаблона articlesTemplate в родительский контейнер.
     */
    function render() {
        JournalistService
            .getTopicList()
            .then(function handleResponse(response) {
                componentData.edition = response;
                return JournalistService.getPublishingList();
            })
            .then(function handleResponse(response) {
                componentData.heading = response;
                $parentElement.empty().append(articlesTemplate({
                    data: componentData
                }));
            })
            .catch(function handleError(error) {
                alert.error(error);
            });
    }

    /**
     * @param {Object} data - входные данные, которые будут записаны в componentData
     */
    this.setData = function setData(data) {
        componentData = data;
        render();
    };

    /**
     * Установка функции обратного вызова.
     * @param {function} listener - функция обратного вызова.
     */
    this.onActionInChildComponent = function onActionInChildComponent(listener) {
        updateCallBack = listener;
    };
}

module.exports = ArticlesComponent;
