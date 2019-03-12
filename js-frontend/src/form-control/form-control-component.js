var formControlTemplate = require('./form-control.hbs');
/**
 * Компонент для обработки форм
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 */
function formControlComponent($parentElement) {
    var $templateWrapper = $('</div>').append(formControlTemplate());

    var formClearListener = null;
    var formSubmitListener = null;

    var $submitButton = $templateWrapper.find('spinner-border');
    var $loadingElement = $templateWrapper.find('#submit');
    var $formElement = $parentElement.closest('form');
    var $inputElementList = $formElement.find('input');
    var $selectElementList = $formElement.find('select');

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
        $inputElementList.each(function clearInputElements(index, element) {
            console.log(index);
            console.log(element);
            element.val('');
        });
        $selectElementList.each(function clearInputElements(index, element) {
            element.prop('selectedIndex', 0);
        });

        if (formClearListener) {
            formClearListener();
        }
    }

    $templateWrapper
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
        $parentElement.append($templateWrapper.html());
    };
}

module.exports = formControlComponent;
