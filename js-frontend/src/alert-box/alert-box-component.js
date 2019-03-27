var alertBoxTemplate = require('./alert-box.hbs');
var $ = require('jquery');

/**
 * Создаёт компонент для отображения уведомлений
 * @constructor
 */
function AlertBox() {
    var duration = 5000;
    var interval;
    var $body = $(document.body);

    /**
     * Обработчик закрытия оповещения
     */
    function removeAlert() {
        $('.alert-container').remove();
        clearTimeout(interval);
    }

    /**
     * Отрисовка компонета
     * @param {string} message сообщение для оповещения
     * @param {string} variant вариант отображения
     */
    function render(message, variant) {
        $body
            .append(alertBoxTemplate({
                variant: variant,
                message: message,
            }));
        interval = setTimeout(removeAlert, duration);
    }

    /**
     * Отображение оповещения об ошибке
     * @param {string} message сообщение для отображения
     */
    this.error = function error(message) {
        render(message, 'danger');
    };

    $body.on('click', '.close', removeAlert);
}

module.exports = AlertBox;
