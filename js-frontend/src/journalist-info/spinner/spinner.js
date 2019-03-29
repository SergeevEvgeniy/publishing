var spinnerTemplate = require('./spinner-body.hbs');

/**
 * Для добавления анимированного крутящегося объекта.
 */
function Spinner() {
    var spinnerBodySelector = '#spinnerBody';
    var currentParent = null;

    /**
     * Добавляет к parent тело спиннера.
     * @param {Object} parent - контейнер, к которому будет добавлено тело спиннера.
     */
    this.appendSpinner = function appendSpinner(parent) {
        currentParent = parent;
        currentParent.append(spinnerTemplate);
    };

    /**
     * Находит в родительском контейнере тело спиннера и удаляет его.
     */
    this.removeSpinner = function removeSpinner() {
        currentParent.find(spinnerBodySelector).remove();
    };
}

module.exports = Spinner;
