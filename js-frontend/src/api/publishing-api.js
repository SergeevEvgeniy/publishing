const PublishingAPI = {
    getMagazinesTitles: () => {
        return fetch('http://localhost:3010/api/publishing/magazine/title/all', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            },
            mode: 'cors',
        });
    },
    getNewspapersTitles: () => {
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
