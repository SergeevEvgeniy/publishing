var JournalistApi = require('../api/journalist-api');

var JournalistService = {
    getJournalistInfo: function getJournalistInfo(id) {
        return new Promise(function resolvePromise(resolve, reject) {
            JournalistApi
                .getJournalistInfo(id)
                .then(function handleResponse(response) {
                    setTimeout(function delay() {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Ошибка при загрузке информации о журналисте!');
                });
        });
    },
    getIssueList: function getIssueList() {
        return new Promise(function resolvePromise(resolve, reject) {
            JournalistApi
                .getIssueList()
                .then(function handleResponse(response) {
                    setTimeout(function delay() {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Ошибка при загрузке информации о изданиях');
                });
        });
    },
    getTopicList: function getTopicList() {
        return new Promise(function resolvePromise(resolve, reject) {
            JournalistApi
                .getTopicList()
                .then(function handleResponse(response) {
                    setTimeout(function delay() {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Ошибка при загрузке информации');
                });
        });
    },
    getPublishingList: function getPublishingList() {
        return new Promise(function resolvePromise(resolve, reject) {
            JournalistApi
                .getPublishingList()
                .then(function handleResponse(response) {
                    setTimeout(function delay() {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Ошибка при загрузке информации');
                });
        });
    }
};

module.exports = JournalistService;
