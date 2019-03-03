function JournalistApiComponent() {
    var URL = 'http://localhost:3010';
    this.getPublishingList = function getPublishingList() {
        return new Promise(function resolvePromise(resolve) {
            $.get(URL + '/api/publishings', function getResponse(response) {
                var publishingList = response.map(function convertToOptionFormat(publishing) {
                    return {
                        value: publishing.publishingId,
                        text: publishing.publishing
                    };
                });
                resolve(publishingList);
            });
        });
    };
    this.getTopicList = function getTopicList() {
        return new Promise(function resolvePromise(resolve) {
            $.get(URL + '/api/topics', function getResponse(response) {
                var topicList = response.map(function convertToOptionFormat(topic) {
                    return {
                        value: topic.topicId,
                        text: topic.topic
                    };
                });
                resolve(topicList);
            });
        });
    };

    this.getIssueList = function getIssueList(id) {
        return new Promise(function resolvePromise(resolve) {
            $.get(URL + '/api/issues?id=' + id, function getResponse(response) {
                resolve(response);
            });
        });
    };

    this.postSearchJournalistForm = function postSearchJournalistForm(formData) {
        return new Promise(function resolvePromise(resolve) {
            $.post(URL + '/api/journalists', formData).done(function getResponse(journalistList) {
                resolve(journalistList);
            });
        });
    };
}

module.exports = new JournalistApiComponent();
