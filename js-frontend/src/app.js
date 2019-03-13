var $ = require('jquery');
var homeComponent = require('./home/home-component');
var SearchComponent = require('./search-journalist/search-component');
var MagazineComponent = require('./publishing/magazine/magazine-component');
var navObj = {
    homeComponent: homeComponent,
    SearchComponent: SearchComponent,
    MagazineComponent: MagazineComponent,
};
var navPick;
var pick;
var $app;
var navbarSelector;

function onNavClickEvent(event) {
    $app.empty();
    navPick = event.target.dataset.type;
    pick = new navObj[navPick]($app);
    pick.render();
}

$(function onReady() {
    $app = $('#app');
    navbarSelector = '#navbar';
    $(navbarSelector).click(onNavClickEvent);
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
require('./handlebars-helpers/handlebars_helpers_if_eq');
