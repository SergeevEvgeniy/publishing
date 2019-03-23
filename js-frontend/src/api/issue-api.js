var filteredIssues = require('../../data/filtered-issues.json');

var IssueAPI = {
    // eslint-disable-next-line no-unused-vars
    getIssuesByFilter: function getIssuesByFilter(formData) {
        return Promise.resolve(filteredIssues);
    },
};

module.exports = IssueAPI;
