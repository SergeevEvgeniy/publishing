/**
 * Создаёт компонент отображающий номер публикации
 * @constructor
 * @param {JQuery} $parentElement - элемент-контейнер для размещения компонента
 * @param {Function} template - шаблон handlebars
 */
function PublicationView($parentElement, template) {
    var publicationIssue = {};
    var loading = {
        status: false,
        stage: 'Загрузка наименований журналов',
    };

    /**
     * Отрисовка омпонента
     */
    function render() {
        $parentElement
            .empty()
            .append(template({
                magazine: {
                    title: publicationIssue.publicationTitle,
                    date: publicationIssue.issueDate,
                    number: publicationIssue.issueNumber,
                    subject: publicationIssue.issueSubject,
                },
                loading: loading,
            }));
    }

    /**
     * Установка номера публикации
     * @param {Object} newPublicationIssue описывает номер публикации, которую нужно показать
     */
    this.setPublicationIssue = function setPublicationIssue(newPublicationIssue) {
        console.log(newPublicationIssue);
        publicationIssue = newPublicationIssue;
        render();
    };

    render();
}

module.exports = PublicationView;
