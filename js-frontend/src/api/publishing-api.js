var magazinesTitles = require('../../data/publications.json');


var PublishingAPI = {
    getMagazinesTitles: function getMagazinesTitles() {
        return Promise.resolve(magazinesTitles);
    },
    getNewspapersTitles: function getNewspapersTitles() {
        return Promise.resolve(magazinesTitles);
    },
};

module.exports = PublishingAPI;
