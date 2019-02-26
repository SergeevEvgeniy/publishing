var $ = require('jquery');
//var homeComponent = require('./home/home-component');
var SearchJournalistComponent = require('./search-journalist/search-component');
$(function onReady() {
    var $app = $('#app');
    var searchComponent = new SearchJournalistComponent($app);
    searchComponent.render();
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
