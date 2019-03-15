var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');

/**
 * Компонент поиска журналистов.
 * @constructor
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function SearchJournalistComponent($parentElement) {
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

    var $componentContainer = $('<div/>');
    $parentElement.append($componentContainer);

    function render() {
        $componentContainer.empty().append(searchTemplate(data));
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
                journalistResult.setJournalistList(response);
            } else {
                console.log('Отсутствуют результаты поиска');
            }
            data.isLoading = false;
            data.isSubmitButtonActive = true;
            render();
        });
    }

    function onSearchClearEvent() {
        data.lastName = null;
        data.article = null;
        data.issue.isIssuesAdded = false;
        data.publishing.elementList[data.publishing.prevOptionIndex].selected = false;
        data.topic.elementList[data.publishing.prevOptionIndex].selected = false;
        $parentElement.find('#journalistResult').empty();
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
    /**
     * @returns {function} Отрисовка компонента.
     */
    this.render = render;
}

require('./search.css');
module.exports = SearchJournalistComponent;
