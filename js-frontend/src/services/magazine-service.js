var PublishingAPI = require('../api/publishing-api');
var IssueAPI = require('../api/issue-api');
var ArticleAPI = require('../api/article-api');
var EmployeeAPI = require('../api/employee-api');

var MagazineService = {
    getFilteredIssues: function getFilteredIssues(formData) {
        return new Promise(function resolvePromise(resolve, reject) {
            IssueAPI
                .getIssuesByFilter(JSON.stringify(formData))
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError(error) {
                    reject(error);
                });
        });
    },
    getMagazinesTitles: function getMagazinesTitles() {
        return new Promise(function resolvePromise(resolve, reject) {
            PublishingAPI
                .getMagazinesTitles()
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError(error) {
                    reject(error);
                });
        });
    },
    getArticlesByIssueId: function getArticlesByIssueId(id) {
        return new Promise(function resolvePromise(resolve, reject) {
            ArticleAPI
                .getArticlesByIssueId(id)
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError(error) {
                    reject(error);
                });
        });
    },
    getEmployeeByArticlesIds: function getEmployeeByArticlesIds(ids) {
        return new Promise(function resolvePromise(resolve, reject) {
            EmployeeAPI
                .getEmployeeByArticlesIds(ids)
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError(error) {
                    reject(error);
                });
        });
    },
    getAdvertisingByIssueId: function getAdvertisingByIssueId(id) {
        return new Promise(function resolvePromise(resolve, reject) {
            IssueAPI
                .getAdvertisingByIssueId(id)
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError(error) {
                    reject(error);
                });
        });
    }
};

module.exports = MagazineService;
