var searchTemplate = require('./search.hbs');
var api = require('../api/journalist-api');
var $ = require('jquery');
var PickListComponent = require('../picklist/picklist-component');
var JournalistResultComponent = require('../search-journalist-result/journalist-result-component');
var FormControlComponent = require('../form-control/form-control-component');
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
    var $searchButtonElement = $searchPage.find(searchButtonSelector);
    var $searchResultElement = $searchPage.find('#searchResult');
    var $searchFormElement = $searchPage.find('form');
    var $loadingElement = $searchPage.find('.spinner-border');
    var $formControlElement = $searchPage.find('#formControl');
    

    var issueList = new PickListComponent($issueElement, 'issue', 'Выбирите выпуск');
    var publishingList = new PickListComponent($publishingElement, 'publishing', 'Выберите издание');
    var topicList = new PickListComponent($topicElement, 'topic', 'Выберите рубрику');
    var journalistResult = new JournalistResultComponent($searchResultElement);
    var formControl = new FormControlComponent($formControlElement);

    function onPublishingChangeEvent(event) {
        api.getIssueList(event.target.value).then(function renderIssueList(response) {
            var $issuePanel = $issueElement.closest('.input-block');
            if ($issuePanel.hasClass(hiddenClass)) {
                $issuePanel.removeClass(hiddenClass);
            }
            issueList.setElementList(response);
            issueList.render();
        });
    }

    function onSearchSubmitEvent(event) {
        var formData = $searchFormElement.serializeArray();
        event.preventDefault();
        $searchButtonElement.prop('disabled', true);
        $loadingElement.removeClass(hiddenClass);
        api.postSearchJournalistForm(formData).then(function renderJournalistList(response) {
            if (response.length !== 0) {
                journalistResult.render(response);
            } else {
                $searchResultElement.text('Отсутствуют результаты поиска.');
            }
            $loadingElement.addClass(hiddenClass);
            $searchButtonElement.prop('disabled', false);
        });
    }

    function onSearchClearEvent() {
        $issueElement.closest('.input-block').addClass(hiddenClass);
        topicList.selectDefault();
        publishingList.selectDefault();
        $articleInputElement.val('');
        $lastNameInputElement.val('');
        $searchResultElement.empty();
    }

    function onInputKeyUpEvent(event) {
        var inputElement = event.target;
        var pattern = new RegExp(inputElement.getAttribute('pattern'));
        var inputElementValue = event.target.value;
        if (!pattern.test(inputElementValue)) {
            inputElement.setCustomValidity(inputElement.dataset.message);
        } else {
            inputElement.setCustomValidity('');
        }
    }

    $searchPage
        .on('change', publishingElementSelector, onPublishingChangeEvent)
        // .on('submit', 'form', onSearchSubmitEvent)
        // .on('click', clearButtonSelector, onSearchClearEvent)
        .on('keyup', inputElementSelector, onInputKeyUpEvent);

    this.render = function render() {
        $parentElement.append($searchPage);
        formControl.render();
        api.getPublishingList().then(function handleResponse(response) {
            publishingList.setElementList(response);
            publishingList.render();
        });
        api.getTopicList().then(function handleResponse(response) {
            topicList.setElementList(response);
            topicList.render();
        });
    };
}

require('./search.css');
module.exports = SearchJournalistComponent;
