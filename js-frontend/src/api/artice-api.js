const ArticleAPI = {
    getArticleById: id => {
        return fetch('http://localhost:3000/api/article/id', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: id,
            mode: 'cors',
        });
    },
};

module.exports = ArticleAPI;
