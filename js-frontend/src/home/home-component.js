var homeTemplate = require('./home.hbs');

function HomeComponent($parent) {
    this.render = function render() {
        $parent.append(homeTemplate({ name: 'Люк Скайвокер' }));
    };
}

module.exports = HomeComponent;
