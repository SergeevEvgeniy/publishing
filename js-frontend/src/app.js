var $ = require('jquery');
var HomeComponent = require('./page-home/page-home');
var SearchComponent = require('./page-search/page-search');
var MagazineComponent = require('./publishing/magazine/magazine-component');
var NewspaperComponent = require('./publishing/newspaper/newspaper-component');
var navObj = {
    HomeComponent: HomeComponent,
    SearchComponent: SearchComponent,
    MagazineComponent: MagazineComponent,
    NewspaperComponent: NewspaperComponent,
};

$(function onReady() {
    var $app = $('#app');
    var navbarSelector = '#navbar';
    $(navbarSelector).click(function navigate(event) {
        $app.empty();
        navObj[event.target.dataset.type]($app);
    });
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
require('./handlebars-helpers/if-equals');
require('./handlebars-helpers/increment');
