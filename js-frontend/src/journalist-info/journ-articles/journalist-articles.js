var $ = require('jquery');
var articlesTemplate = require('./journalist-articles.hbs');
var releaseTemplate = require('./release.hbs');
var LoaderData = require('../data-loader/data-loader');
var SearchResultComponent = require('../search-result/search-result');

function ArticlesComponent($element) {
    var componentData = {};
    var editionSelector = '#edition';
    var formId = '#searchArticles';
    var releaseBodySelector = '#releaseBody';
    var searchBodySelector = '#searchResultBody';
    var loaderData = new LoaderData();
    var updateCallBack = null;


    function findArticles (event) {
        event.preventDefault();
        var formData = new FormData(document.getElementById(formId));
        loaderData.recieveSingleData('http://127.0.0.1:3000/array')
        .then(function (item) {
            var searchResult = new SearchResultComponent($(searchBodySelector));
            searchResult.setData(item.data);
        })
        .catch(function (error) {
            console.log(error);
        });
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

    ($element).off().on('click', '#findArticle', findArticles)
    .on('click', '#clearFields', clearForm)
    .on('change', editionSelector, getIssue);

    function render() {
        loaderData.recieveSingleData('http://127.0.0.1:3000/array')
        .then(function (item) {
            console.log('получили данные о Изданиях и выпусках');
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

            $element.empty().append(articlesTemplate({
                data: componentData
            }));
        });
    }

    this.setData = function (data) {
        render();
    };
    this.onActionInChildComponent = function (callBack) {
        updateCallBack = callBack;
    };
}

module.exports = ArticlesComponent;