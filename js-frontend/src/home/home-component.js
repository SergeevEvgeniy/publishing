var homeTemplate = require('./home.hbs');
var $ = require('jquery');

function homeComponent($parent) {
    this.render = function () {
        $parent.append(homeTemplate({ name: 'Люк Скайвокер' }));
    }
}

module.exports = {
    homeComponent: homeComponent
};
