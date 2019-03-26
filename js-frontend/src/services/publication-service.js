var PublishingAPI = require('../api/publishing-api');
var IssueAPI = require('../api/issue-api');
var ArticleAPI = require('../api/article-api');
var EmployeeAPI = require('../api/employee-api');

var PublicationService = {
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
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке наименований журналов! Повторите попытку позже!');
                });
        });
    },
    getNewspapersTitles: function getNewspapersTitles() {
        return new Promise(function resolvePromise(resolve, reject) {
            PublishingAPI
                .getNewspapersTitles()
                .then(function handleResponse(response) {
                    // eslint-disable-next-line func-names
                    setTimeout(function () {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке наименований газет! Повторите попытку позже!');
                });
        });
    },
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
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке номеров! Повторите попытку позже!');
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
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке статей! Повторите попытку позже!');
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
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке редакторов и авторов! Повторите попытку позже!');
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
                .catch(function handleError() {
                    reject('Произошла ошибка при загрузке рекламы! Повторите попытку позже!');
                });
        });
    }
};

module.exports = PublicationService;
