var alertBoxTemplate = require('./alert-box.hbs');
var $ = require('jquery');

/**
 * Создаёт компонент показывать уведомления
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 * По умолчанию:
 * variant = 'success',
 * duration = 5 сек,
 * message = ''
 */
function AlertBox($parentElement) {
    var variant = 'success';
    var message = '';
    var duration = 5000;
    var interval;

    /**
     * Обработчик закрытия оповещения
     */
    function removeAlert() {
        $('.alert-container').remove();
        clearTimeout(interval);
    }

    /**
     * Отрисовка компонета
     */
    function render() {
        $parentElement
            .append(alertBoxTemplate({
                variant: variant,
                message: message,
            }));
        interval = setTimeout(removeAlert, duration);
    }

    /**
     * Отображение оповещения
     * @param {{variant: String, message: String, duration: Number}} options параметры оповещения
     */
    this.alert = function alert(options) {
        variant = options.variant || 'success';
        message = options.message || '';
        duration = options.duration || 5000;
        render();
    };

    $parentElement.on('click', '.close', removeAlert);
}

module.exports = AlertBox;
