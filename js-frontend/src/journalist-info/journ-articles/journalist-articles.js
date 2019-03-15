var $ = require('jquery');
var articlesTemplate = require('./journalist-articles.hbs');
var releaseTemplate = require('./release.hbs');
var SearchResultComponent = require('../search-result/search-result');


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
        var release;
        $(releaseBodySelector).empty();
        release = [{
            id: 500,
            name: '24124'
        }];
        $(releaseBodySelector).append(releaseTemplate({
            data: release
        }));
    }

    ($parentElement).off().on('click', '#findArticle', findArticles)
        .on('click', '#clearFields', clearForm)
        .on('change', editionSelector, getIssue);

    /**
     * Фукнция для добавления шаблона articlesTemplate в родительский контейнер.
     */
    function render() {
        componentData.edition = [
            {
                id: 1,
                name: 'name'
            },
            {
                id: 2,
                name: 'name2'
            },
        ];
        componentData.heading = [
            {
                id: 100,
                name: 'meow'
            }
        ];

        $parentElement.empty().append(articlesTemplate({
            data: componentData
        }));
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
