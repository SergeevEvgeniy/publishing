var homeTemplate = require('./home.hbs');
var $ = require('jquery');

function render() {
    $('#app').append(homeTemplate({ name: 'Люк Скайвокер' }));
}

module.exports = {
    render: render
};
