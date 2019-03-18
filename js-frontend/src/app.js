var $ = require('jquery');
var HomeComponent = require('./home/home-component');
var SearchComponent = require('./search-page/search-page-component');
var MagazineComponent = require('./publishing/magazine/magazine-component');
var navObj = {
    HomeComponent: HomeComponent,
    SearchComponent: SearchComponent,
    MagazineComponent: MagazineComponent,
};

$(function onReady() {
    var $app = $('#app');
    var navbarSelector = '#navbar';
    $(navbarSelector).click(function navigate(event) {
        $app.empty();
        new navObj[event.target.dataset.type]($app).render();
    });
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
require('./handlebars-helpers/handlebars_helpers_if_eq');
