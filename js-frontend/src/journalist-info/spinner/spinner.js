var spinnerTemplate = require('./spinner-body.hbs');

/**
 * Для добавления анимированного крутящегося объекта.
 */
function Spinner() {
    var spinnerBodySelector = '#spinnerBody';
    var currentParent = null;

    /**
     * Добавляет к parent тело спиннера.
     * @param {Object} parent
     */
    this.appendSpinner = function (parent) {
        currentParent = parent;
        currentParent.append(spinnerTemplate);
    };
    this.removeSpinner = function () {
        currentParent.find(spinnerBodySelector).remove();
    };
}

module.exports = {
    Spinner: Spinner
};
