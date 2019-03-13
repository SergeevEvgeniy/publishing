var $ = require('jquery');
<<<<<<< HEAD
var homeComponent = require('./home/home-component');
=======
var HomeComponent = require('./home/home-component');
>>>>>>> 09f3d8df735ebf7149064647f8273effb91eb252
var SearchComponent = require('./search-journalist/search-component');
var MagazineComponent = require('./publishing/magazine/magazine-component');
var navObj = {
    HomeComponent: HomeComponent,
    SearchComponent: SearchComponent,
    MagazineComponent: MagazineComponent,
};
var navPick;
var pick;
var $app;
var navbarSelector;

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
