var $ = require('jquery');
var HomeComponent = require('./home/home-component');
var SearchComponent = require('./search-journalist/search-component');
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
        var navPick = event.target.dataset.type;
        var pick = new navObj[navPick]($app);
        $app.empty();
        pick.render();
    });
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
