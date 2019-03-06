const ArticleAPI = {
    getArticleByIds: function (ids) {
        return fetch('http://localhost:3000/api/article/id', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: ids,
            mode: 'cors',
        });
    },
};

module.exports = ArticleAPI;
