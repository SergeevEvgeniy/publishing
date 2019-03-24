var articles = require('../../data/articles.json');

var ArticleAPI = {
    // eslint-disable-next-line no-unused-vars
    getArticlesByIssueId: function getArticlesByIssueId(id) {
        return Promise.resolve(articles);
    },
};

module.exports = ArticleAPI;
