var ArticleApi = require('../api/article-api');

var ArticleService = {
    getJournalistInfo: function getJournalistInfo(id) {
        return new Promise(function resolvePromise(resolve, reject) {
            ArticleApi
                .getJournalistArticles(id)
                .then(function handleResponse(response) {
                    setTimeout(function delay() {
                        resolve(response);
                    }, 500);
                })
                .catch(function handleError() {
                    reject('Ошибка при загрузке статей журналиста!');
                });
        });
    },
};

module.exports = ArticleService;
