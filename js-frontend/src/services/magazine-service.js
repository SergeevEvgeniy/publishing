const PublishingAPI = require('../api/publishing-api');

const MagazineService = {
    getMagazinesTitles: function () {
        return new Promise((resolve, reject) => {
            PublishingAPI.getMagazinesTitles()
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });        
    },
};

module.exports = MagazineService;
