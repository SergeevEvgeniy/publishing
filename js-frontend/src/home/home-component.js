var homeTemplate = require('./home.hbs');

<<<<<<< HEAD
function homeComponent($parent) {
=======
function HomeComponent($parent) {
>>>>>>> 09f3d8df735ebf7149064647f8273effb91eb252
    this.render = function render() {
        $parent.append(homeTemplate({ name: 'Люк Скайвокер' }));
    };
}

<<<<<<< HEAD
module.exports = homeComponent;
=======
module.exports = HomeComponent;
>>>>>>> 09f3d8df735ebf7149064647f8273effb91eb252
