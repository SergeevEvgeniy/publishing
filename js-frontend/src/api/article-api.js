var articles = require('../../data/articles.json');
var articlesSearch = require('../../data/articlesSearch.json');


var ArticleAPI = {
    // eslint-disable-next-line no-unused-vars
    getArticlesByIssueId: function getArticlesByIssueId(id) {
        return Promise.resolve(articles);
    },
    getJournalistArticles: function getJournalistArticles() {
        return Promise.resolve(articlesSearch);
    }
};

module.exports = ArticleAPI;
