const PublishingAPI = {
    getMagazinesTitles: function () {
        return fetch('http://localhost:3000/api/publishing/magazine/title/all', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            },
            mode: 'cors',
        });
    },
    getNewspapersTitles: function () {
        return fetch('http://localhost:3000/api/publishing/newspaper/title/all', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            },
            mode: 'cors',
        });
    },
};

module.exports = PublishingAPI;
