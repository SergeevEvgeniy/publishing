var $ = require('jquery');
var articlesTemplate = require('./journalist-articles.hbs');
var releaseTemplate = require('./release.hbs');
var SearchResultComponent = require('../search-result/search-result');


/**
 * Компонент для вкладки 'Поиск статей'
 * @constructor
 * @param {JQueryElement} $parentElement - родитель, к которому будет добавлен шаблон
 */
function ArticlesComponent($parentElement) {
    var componentData = {};
    var editionSelector = '#edition';
    var releaseBodySelector = '#releaseBody';
    var searchBodySelector = '#searchResultBody';
    var updateCallBack = null;


    function findArticles (event) {
        event.preventDefault();
        var searchResult = new SearchResultComponent($(searchBodySelector));
        searchResult.render();
    }

    function clearForm(event) {
        event.preventDefault();
        event.target.closest('form').reset();
        $(searchBodySelector).empty();
        $(releaseBodySelector).empty();
        if (typeof updateCallBack === 'function') {
            updateCallBack();
        }
    }
    
    function getIssue(event) {
        $(releaseBodySelector).empty();
        var release = [{
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
        componentData.heading = [{
            id: 100,
            name: 'meow'
        }];

        $parentElement.empty().append(articlesTemplate({
            data: componentData
        }));

    }

    this.setData = function (data) {
        render();
    };
    this.onActionInChildComponent = function (callBack) {
        updateCallBack = callBack;
    };
}

module.exports = ArticlesComponent;