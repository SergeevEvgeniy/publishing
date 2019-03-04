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
    var hiddenClass = 'd-none';
    var publishingElementSelector = '#publishing select';
    var searchButtonSelector = '#searchJournalist';
    var clearButtonSelector = '#clearJournalistSearch';
    var inputElementSelector = 'input';

    var $issueElement = $searchPage.find('#issue');
    var $publishingElement = $searchPage.find('#publishing');
    var $topicElement = $searchPage.find('#topic');
    var $articleInputElement = $searchPage.find('#article');
    var $lastNameInputElement = $searchPage.find('#lastName>input');
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
            if ($issuePanel.hasClass(hiddenClass)) {
                $issuePanel.removeClass(hiddenClass);
            }
            issueList.render(response);
        });
    }

    function onSearchSubmitEvent(event) {
        var formData = $searchFormElement.serializeArray();
        var searchButton = event.target;
        event.preventDefault();
        searchButton.disabled = true;
        $loadingElement.removeClass(hiddenClass);
        api.postSearchJournalistForm(formData).then(function renderJournalistList(response) {
            if (response.length !== 0) {
                journalistResult.render(response);
            } else {
                $searchResultElement.text('Отсутствуют результаты поиска.');
            }
            $loadingElement.addClass(hiddenClass);
            searchButton.disabled = false;
        });
    }

    function onSearchClearEvent(event) {
        event.preventDefault();
        $issueElement.closest('.input-block').addClass(hiddenClass);
        topicList.selectDefault();
        publishingList.selectDefault();
        $articleInputElement.val('');
        $lastNameInputElement.val('');
        $searchResultElement.empty();
    }

    function onInputBlurEvent(event) {
        var $inputElementParent = $(event.target.parentElement);
        var $validationMessage = $inputElementParent.find('small');
        var pattern = /[A-z0-9]/;
        var inputElementValue = event.target.value;
        console.log(pattern.test(event.target.textContent));
        if (pattern.test(inputElementValue)) {
            $validationMessage.removeClass(hiddenClass);
        } else {
            $validationMessage.addClass(hiddenClass);
        }
    }

    $searchPage.on('change', publishingElementSelector, onPublishingChangeEvent);
    $searchPage.on('click', searchButtonSelector, onSearchSubmitEvent);
    $searchPage.on('click', clearButtonSelector, onSearchClearEvent);
    $searchPage.on('blur', inputElementSelector, onInputBlurEvent);

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
