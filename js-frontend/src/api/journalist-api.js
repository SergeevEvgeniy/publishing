function JournalistApiComponent() {
    this.getPublishingList = function getPublishingList() {
        return new Promise(function resolvePromise(resolve) {
            var publishingList = require('../../data/publishings.json').map(function convertToOptionFormat(publishing) {
                return {
                    value: publishing.publishingId,
                    text: publishing.publishing
                };
            });
            resolve(publishingList);
        });
    };
    this.getTopicList = function getTopicList() {
        return new Promise(function resolvePromise(resolve) {
            var topicList = require('../../data/topics.json').map(function convertToOptionFormat(topic) {
                return {
                    value: topic.topicId,
                    text: topic.topic
                };
            });
            resolve(topicList);
        });
    };
    this.getJournalistInfo = function getJournalistInfo(id) {
        console.log(`id журналиста ${id}`);
        return new Promise(function resolvePromise(resolve) {
            var journalistInfo = require('../../data/journalist-info-stat.json');
            resolve(journalistInfo);
        });
    };
    this.getIssueList = function getIssueList(id) {
        console.log(id);
        return new Promise(function resolvePromise(resolve) {
            var issueList = require('../../data/issues.json')[0].issues.map(function convertToOptionFormat(issue) {
                return {
                    value: issue.issueId,
                    text: issue.issue
                };
            });
            resolve(issueList);
        });
    };

    this.postSearchJournalistForm = function postSearchJournalistForm(formData) {
        console.log(formData);
        return new Promise(function resolvePromise(resolve) {
            setTimeout(function resolveRequest() {
                resolve(require('../../data/journalists.json'));
            }, 1000);
        });
    };
}

module.exports = new JournalistApiComponent();
