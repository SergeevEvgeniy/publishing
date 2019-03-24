var filteredIssues = require('../../data/filtered-issues.json');
var advertising = require('../../data/advertising.json');

var IssueAPI = {
    // eslint-disable-next-line no-unused-vars
    getIssuesByFilter: function getIssuesByFilter(formData) {
        return Promise.resolve(filteredIssues);
    },
    // eslint-disable-next-line no-unused-vars
    getAdvertisingByIssueId: function getAdvertising(id) {
        return Promise.resolve(advertising);
    }
};

module.exports = IssueAPI;
