var magazinesTitles = require('../../data/publications.json');

var PublishingAPI = {
    getMagazinesTitles: function getMagazinesTitles() {
        return Promise.resolve(magazinesTitles);
    },
    getNewspapersTitles: function getMagazinesTitles() {
        return fetch('http://localhost:3010/api/publishing/newspaper/title/all', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            },
            mode: 'cors',
        });
    },
};

module.exports = PublishingAPI;
