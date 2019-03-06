var $ = require('jquery');
var { homeComponent } = require('./home/home-component');
var SearchComponent = require('./search-journalist/search-component');
var navObj = {
    homeComponent: homeComponent,
    SearchComponent: SearchComponent
};

$(function onReady() {
    var $app = $('#app');
    var navbarSelector = '#navbar';
    $(navbarSelector).click(function (event) {
        $app.empty();
        var navPick = event.target.dataset.type;
        var pick = new navObj[navPick]($app);
        pick.render();
    })

});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');
