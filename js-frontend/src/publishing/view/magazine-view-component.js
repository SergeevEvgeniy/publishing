var magazineView = require('./magazine-view.hbs');

function MagazineView($rootElement) {
    this.$rootElement = $rootElement;

    this.render();
}

MagazineView.prototype.setIssueMagazine = function (issueMagazine) {
    console.log(issueMagazine);
    //this.render();
};

MagazineView.prototype.render = function () {
    this.$rootElement
        .empty()
        .append(magazineView({
            magazine: this.magazine,
        }));
};

module.exports = MagazineView;
