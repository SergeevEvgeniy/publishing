const IssueAPI = {
    getIssuesByFilter: formData => {
        return fetch(`http://localhost:3010/api/issue/filter`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: formData,
            mode: 'cors',
        });
    },
};

module.exports = IssueAPI;
