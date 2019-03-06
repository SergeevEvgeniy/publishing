var $ = require('jquery');
var homeComponent = require('./home/home-component');
var { JournalistStatComponent } = require('./journalist-info/journalist-component');


$(function onReady() {
    var journalistComponent = new JournalistStatComponent($('#app'));
    journalistComponent.appendComponent();
});

require('bootstrap/dist/css/bootstrap.css');
require('bootstrap/dist/js/bootstrap');
require('@fortawesome/fontawesome-free/css/all.css');