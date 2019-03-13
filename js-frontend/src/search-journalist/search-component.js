var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');

var dataDefault = {
    publishing: {
        defaultText: 'Выберите издание',
        elementList: []
    },
    issue: {
        defaultText: 'Выбирите выпуск',
        elementList: [],
        isIssuesAdded: false
    },
    topic: {
        defaultText: 'Выберите рубрику',
        elementList: []
    },
    lastName: null,
    article: null,
    isLoading: false,
    isSubmitButtonActive: true
};

function SearchJournalistComponent($parentElement) {
    var data = Object.create(dataDefault);
    //dataDefault = [];

    function render() {
        console.log('data');
        console.log(data);
        console.log('dataDefault');
        console.log(dataDefault);
        $parentElement.empty().append(searchTemplate(data));
    }

    function onPublishingChangeEvent(event) {
        api.getIssueList(event.target.value).then(function renderIssueList(response) {
            data.issue.elementList = response;
            data.issue.isIssuesAdded = true;
            render();
        });
    }

    function onSearchSubmitEvent(event) {
        var formData = $parentElement.find('form').serializeArray();
        event.preventDefault();
        data.isLoading = true;
        data.isSubmitButtonActive = false;
        render();
        api.postSearchJournalistForm(formData).then(function renderJournalistList(response) {
            var journalistResult = new JournalistResultComponent($parentElement.find('#searchResult'));
            if (response.length !== 0) {
                journalistResult.render(response);
            } else {
                console.log('Отсутствуют результаты поиска');
            }
            data.isLoading = false;
            data.isSubmitButtonActive = true;
            render();
        });
    }

    function onSearchClearEvent() {
        //data = Object.assign({}, dataDefault);
        //console.log('clear');
        console.log(dataDefault);
        render();
    }

    function onInputKeyUpEvent(event) {
        console.log(event.target.name);
        data[event.target.name] = event.target.value;
    }

    $parentElement
        .on('change', '[name="publishing"]', onPublishingChangeEvent)
        .on('submit', 'form', onSearchSubmitEvent)
        .on('click', '#clearJournalistSearch', onSearchClearEvent)
        .on('keyup', 'input', onInputKeyUpEvent);

    api.getPublishingList().then(function renderPublishingList(response) {
        data.publishing.elementList = response;
        render();
    });

    api.getTopicList().then(function renderTopicList(response) {
        data.topic.elementList = response;
        render();
    });

    this.render = render;
}

require('./search.css');
module.exports = SearchJournalistComponent;
