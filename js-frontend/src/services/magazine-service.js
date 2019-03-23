var PublishingAPI = require('../api/publishing-api');
var IssueAPI = require('../api/issue-api');

function getFilteredIssues(formData) {
    return new Promise(function resolvePromise(resolve, reject) {
        IssueAPI.getIssuesByFilter(JSON.stringify(formData))
            .then(function handleResponse(response) {
                resolve(response);
            })
            .catch(function handleError(error) {
                reject(error);
            });
    });
}

function getMagazinesTitles() {
    return new Promise(function resolvePromise(resolve, reject) {
        PublishingAPI.getMagazinesTitles()
            .then(function handleResponse(response) {
                resolve(response);
            })
            .catch(function handleError(error) {
                reject(error);
            });
    });
}

module.exports = {
    getMagazinesTitles: getMagazinesTitles,
    getFilteredIssues: getFilteredIssues,
};
