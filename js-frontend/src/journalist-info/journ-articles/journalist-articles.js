var $ = require('jquery');
var articlesTemplate = require('./journalist-articles.hbs');
var releaseTemplate = require('./release.hbs');
var { LoaderData } = require('../data-loader/data-loader');
var { SearchResultComponent } = require('../search-result/search-result');

function ArticlesComponent($element) {
    var componentData = {};
    var editionSelector = '#edition';
    var formId = '#searchArticles';
    var releaseBodySelector = '#releaseBody';
    var searchBodySelector = '#searchResultBody';
    var loaderData = new LoaderData();

    ($element).on('click', '#findArticle', function (event) {
        event.preventDefault();
        console.log('hel')
        var formData = new FormData(document.getElementById(formId));
        //отправить эти данные с запросом, url сейчас левый
        loaderData.recieveSingleData('http://127.0.0.1:3000/array')
        .then(function (item) {
            var searchResult = new SearchResultComponent($(searchBodySelector));
            searchResult.setData(item.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    }).on('click', '#clearFields', function (event) {
        event.preventDefault();
        event.target.closest('form').reset();
        $(searchBodySelector).empty();
        $(releaseBodySelector).empty();
    }).on('change', editionSelector, function (event) {
        $(releaseBodySelector).empty();
        //var value = event.target.value;
        //отправили запрос и получили все выпуски издания
        //loaderData.recieveSingleData('url', value)
        //.then(function () {})
        //для теста
        var release = [{
            id: 500,
            name: '24124'
        }];
        $(releaseBodySelector).append(releaseTemplate({
            data: release
        }));
    })

    function render() {
        // при загрузке мне нужно получить список всех изданий и рубрик
        loaderData.recieveSingleData('http://127.0.0.1:3000/array')
        .then(function (item) {
            //для теста
            console.log('получили данные о Изданиях и выпусках');
            //componentData = item
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
        //componentData = data;
        render();
    };
}

module.exports = {
    ArticlesComponent: ArticlesComponent
};