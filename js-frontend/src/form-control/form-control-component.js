var formControlTemplate = require('./form-control.hbs');
/**
 * Компонент для обработки форм
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function FormControlComponent($parentElement) {
    var $templateWrapper = $('<div />').append(formControlTemplate());

    var formClearListener = null;
    var formSubmitListener = null;

    var $submitButton = $templateWrapper.find('spinner-border');
    var $loadingElement = $templateWrapper.find('#submit');
    
    var $formElement;
    var $inputElementList;
    var $selectElementList;

    var submitButtonSelector = '#submit';
    var clearButtonSelector = '#clear';

    function onSubmitClickEvent(event) {
        var formData = $searchFormElement.serializeArray();
        event.preventDefault();
        $submitButton.prop('disabled', true);
        $loadingElement.removeClass(hiddenClass);
        if (formSubmitListener) {
            formSubmitListener(formData);
            $loadingElement.addClass(hiddenClass);
            $submitButton.prop('disabled', false);
        }
    }

    function onClearClickEvent() {
        var selectElementList = $parentElement.closest('form').find('select');
        $inputElementList.each(function clearInputElements(index, element) {
            $(element).val('');
        });
        selectElementList.each(function clearSelectElements(index, element) {
            console.log(element.selectedIndex);
        });

        if (formClearListener) {
            formClearListener();
        }
    }

    $parentElement
        .on('click', submitButtonSelector, onSubmitClickEvent)
        .on('click', clearButtonSelector, onClearClickEvent);

    /**
     * Установка функции обратного вызова на очищение формы
     * @param {function} listener вызывается, когда была нажата кнопка очистить.
     * Не чего не отправляет
     */
    this.onFormClear = function onFormClear(listener) {
        formClearListener = listener;
    };
    /**
     * Установка функции обратного вызова на очищение формы
     * @param {function} listener вызывается, когда была нажата кнопка очистить.
     * Не чего не отправляет
     */
    this.onFormSubmit = function onFormSubmit(listener) {
        formSubmitListener = listener;
    };
    /**
     * Отрисовка компонента
     */
    this.render = function render() {
        console.log($templateWrapper);
        $parentElement.append($templateWrapper.html());
        $formElement = $parentElement.closest('form');
        $inputElementList = $formElement.find('input');
        $selectElementList = $formElement.find('select');
    };
}

module.exports = FormControlComponent;
