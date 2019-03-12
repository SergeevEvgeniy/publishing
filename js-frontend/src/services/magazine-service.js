var PublishingAPI = require('../api/publishing-api');
var IssueAPI = require('../api/issue-api');

var MagazineService = {
    getMagazinesTitles: () => {
        return new Promise((resolve, reject) => {
            PublishingAPI.getMagazinesTitles()
                .then(response => {
                    resolve(response.json());
                })
                .catch(error => {
                    reject(error);
                });
        });        
    },
    getFilteredIssues: (formData) => {
        return new Promise((resolve, reject) => {
            IssueAPI.getIssuesByFilter(JSON.stringify(formData))
                .then(response => {
                    resolve(response.json());
                })
                .catch(error => {
                    reject(error);
                });
        });
    }
};

module.exports = MagazineService;
