var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');
var $ = require('jquery');
var PickListComponent = require('../picklist/picklist-component');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');
var $searchPage = $('<div>', {
    id: 'searchJournalist'
});
$searchPage.append(searchTemplate());

function SearchJournalistComponent($parentElement) {
    var publishingElementSelector = '#publishing select';
    var searchButtonSelector = '#searchJournalist';
    var clearButtonSelector = '#clearJournalistSearch';

    var $issueElement = $searchPage.find('#issue');
    var $publishingElement = $searchPage.find('#publishing');
    var $topicElement = $searchPage.find('#topic');
    var $articleInputElement = $searchPage.find('#article');
    var $lastNameInputElement = $searchPage.find('#lastName');
    var $searchResultElement = $searchPage.find('#searchResult');
    var $searchFormElement = $searchPage.find('form');
    var $loadingElement = $searchPage.find('.spinner-border');

    var issueList = new PickListComponent($issueElement, 'issue', 'Выбирите выпуск');
    var publishingList = new PickListComponent($publishingElement, 'publishing', 'Выберите издание');
    var topicList = new PickListComponent($topicElement, 'topic', 'Выберите рубрику');
    var journalistResult = new JournalistResultComponent($searchResultElement);

    function onPublishingChangeEvent(event) {
        api.getIssueList(event.target.value).then(function renderIssueList(response) {
            var $issuePanel = $issueElement.closest('.input-block');
            if ($issuePanel.hasClass('d-none')) {
                $issuePanel.removeClass('d-none');
            }
            issueList.render(response);
        });
    }

    function onSearchSubmitEvent(event) {
        var formData = $searchFormElement.serializeArray();
        var searchButton = event.target;
        event.preventDefault();
        searchButton.disabled = true;
        $loadingElement.removeClass('d-none');
        api.postSearchJournalistForm(formData).then(function renderJournalistList(response) {
            if (response.length !== 0) {
                journalistResult.render(response);
            } else {
                $searchResultElement.text('Отсутствуют результаты поиска.');
            }
            $loadingElement.addClass('d-none');
            searchButton.disabled = false;
        });
    }

    function onSearchClearEvent(event) {
        event.preventDefault();
        $issueElement.closest('.input-block').addClass('d-none');
        topicList.selectDefault();
        publishingList.selectDefault();
        $articleInputElement.val('');
        $lastNameInputElement.val('');
        $searchResultElement.empty();
    }

    $searchPage.on('change', publishingElementSelector, onPublishingChangeEvent);
    $searchPage.on('click', searchButtonSelector, onSearchSubmitEvent);
    $searchPage.on('click', clearButtonSelector, onSearchClearEvent);

    this.render = function render() {
        $parentElement.append($searchPage);
        api.getPublishingList().then(function handleResponse(response) {
            publishingList.render(response);
        });
        api.getTopicList().then(function handleResponse(response) {
            topicList.render(response);
        });
    };
}

require('./search.css');
module.exports = SearchJournalistComponent;
