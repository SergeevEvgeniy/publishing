var $ = require('jquery');
//var homeComponent = require('./home/home-component');
var SearchJournalistComponent = require('./search-journalist/search-component');
// var api = require('./api/journalist-api');
// var JournalistResultComponent = require('./search-journalist-result/journalist-result-component');
$(function onReady() {
    var $app = $('#app');
    var searchComponent = new SearchJournalistComponent($app);
    searchComponent.render();
    // var journalistResult = new JournalistResultComponent($app);
    // api.postSearchJournalistForm({}).then(function renderJournalistList(response) {
    //     if (response.length === 0) {
    //         console.log('отсутствуют результаты поиска');
    //     } else {
    //         journalistResult.render(response);
    //     }
    // });
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
