var PublishingAPI = require('../api/publishing-api');
var IssueAPI = require('../api/issue-api');

function getFilteredIssues(formData) {
    return new Promise(function (resolve, reject) {
        IssueAPI.getIssuesByFilter(JSON.stringify(formData))
            .then(function (response) {
                resolve(response.json());
            })
            .catch(function (error) {
                reject(error);
            });
    });
}

function getMagazinesTitles() {
    return new Promise(function (resolve, reject) {
        PublishingAPI.getMagazinesTitles()
            .then(function (response) {
                resolve(response.json());
            })
            .catch(function (error) {
                reject(error);
            });
    });
}


module.exports = {
    getMagazinesTitles: getMagazinesTitles,
    getFilteredIssues: getFilteredIssues,
};
