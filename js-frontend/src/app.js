var $ = require('jquery');
var homeComponent = require('./home/home-component');

$(function onReady() {
    homeComponent.render();
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');