var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');

/**
 * Компонент поиска журналистов.
 * @constructor
 * @param  {JQuery} $parentElement Элемент-контейнер для размещения компонента.
 */
function SearchJournalistComponent($parentElement) {
    var data = {
        publishing: {
            prevOptionIndex: 0,
            elementList: []
        },
        issue: {
            prevOptionIndex: 0,
            elementList: [],
            isIssuesAdded: false
        },
        topic: {
            prevOptionIndex: 0,
            elementList: []
        },
        lastName: null,
        article: null,
        isLoading: false,
        isSubmitButtonActive: true
    };

    var journalistSearchListener = null;
    var clearSearchListener = null;

    function render() {
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
            if (journalistSearchListener) {
                journalistSearchListener(response);
            }
            data.isLoading = false;
            data.isSubmitButtonActive = true;
            render();
        });
    }

    function onSearchClearEvent() {
        if (clearSearchListener) {
            clearSearchListener();
        }
        data.lastName = null;
        data.article = null;
        data.issue.isIssuesAdded = false;
        data.publishing.elementList[data.publishing.prevOptionIndex].selected = false;
        data.topic.elementList[data.publishing.prevOptionIndex].selected = false;
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
     * Установка метода обратного вызова на поиск журналистов
     * @param {function} listener вызывается, когда была отравлена форма поиска журналиста
     * Отправляет аргумент - массив с информацией о журналистах
     */
    this.onSearchJournalist = function onSearchJournalist(listener) {
        journalistSearchListener = listener;
    };

    /**
     * Установка метода обратного вызова на очистку поиска
     * @param {function} listener вызывается, когда была нажата кнопка очистить
     * Отправляет аргумент - массив с информацией о журналистах
     */
    this.onClearSearch = function onClearSearch(listener) {
        clearSearchListener = listener;
    };

    /**
     * Отрисовка компонента.
     */
    this.render = render;
}

require('./search.css');
module.exports = SearchJournalistComponent;
