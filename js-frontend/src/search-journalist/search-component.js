var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');

var data = {
    componentId: 'searchJournalist',
    publishing: {
        prevOptionIndex: 0,
        defaultText: 'Выберите издание',
        elementList: [],
        value: null
    },
    issue: {
        prevOptionIndex: 0,
        defaultText: 'Выбирите выпуск',
        elementList: [],
        isIssuesAdded: false
    },
    topic: {
        prevOptionIndex: 0,
        defaultText: 'Выберите рубрику',
        elementList: [],
        value: null
    },
    lastName: null,
    article: null,
    isLoading: false,
    isSubmitButtonActive: true
};

function SearchJournalistComponent($parentElement) {
    $parentElement.append($('<div>', {
        id: data.componentId
    }));

    function render() {
        $parentElement.find('#' + data.componentId).empty().append(searchTemplate(data));
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
            var journalistResult = new JournalistResultComponent($parentElement);
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
        render();
    }

    function onInputKeyUpEvent(event) {
        var target = event.target;
        var dataProperty = target.name;
        data[dataProperty] = target.value;
    }

    function onSelectChangeEvent(event) {
        var target = event.target;
        var dataProperty = target.name;
        var optionIndex = target.selectedIndex - 1;
        var selectElementData = data[dataProperty];
        selectElementData.elementList[selectElementData.prevOptionIndex].selected = false;
        selectElementData.elementList[optionIndex].selected = true;
        selectElementData.prevOptionIndex = optionIndex;
    }

    $parentElement
        .on('change', '[name="publishing"]', onPublishingChangeEvent)
        .on('submit', 'form', onSearchSubmitEvent)
        .on('click', '#clearJournalistSearch', onSearchClearEvent)
        .on('keyup', 'input', onInputKeyUpEvent)
        .on('change', 'select', onSelectChangeEvent);

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
