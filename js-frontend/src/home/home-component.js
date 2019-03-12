var homeTemplate = require('./home.hbs');

function homeComponent($parent) {
    this.render = function render() {
        $parent.append(homeTemplate({ name: 'Люк Скайвокер' }));
    };
}

module.exports = {
    homeComponent: homeComponent
};
