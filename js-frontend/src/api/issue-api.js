const IssueAPI = {
    getIssuesByFilter: function (formData) {
        return fetch('http://localhost:3000/api/issue/filter', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            },
            body: formData,
            mode: 'cors',
        });
    },
};

module.exports = IssueAPI;
