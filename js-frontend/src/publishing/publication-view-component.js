var PublicationService = require('../services/publication-service');

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
        stage: '',
    };
    var articles;
    var groupedArticles;
    var articleGroupByTopic;
    var advertising;

    /**
     * Отрисовка омпонента
     */
    function render() {
        $parentElement
            .empty()
            .append(template({
                publication: {
                    title: publicationIssue.publicationTitle,
                    date: publicationIssue.issueDate,
                    number: publicationIssue.issueNumber,
                    subject: publicationIssue.issueSubject,
                    topics: groupedArticles,
                    advertising: advertising,
                },
                loading: loading,
            }));
    }

    /**
     * Обработчик события на нажатие по теме статей
     * @param {Object} event содержит свойства произошедшего события
     */
    function onTopicClickEvent(event) {
        var $li = $(event.target.closest('li'));
        groupedArticles.forEach(function clearClassTopics(item, index) {
            groupedArticles[index].class = '';
        });
        groupedArticles[+$li.data('index')].class = 'active';
        render();
    }

    /**
     * Установка номера публикации
     * @param {Object} newPublicationIssue описывает номер публикации, которую нужно показать
     */
    this.setPublicationIssue = function setPublicationIssue(newPublicationIssue) {
        publicationIssue = newPublicationIssue;
        loading.status = true;
        loading.stage = 'Загрузка статей номера...';
        render();
        groupedArticles = [];
        PublicationService
            .getArticlesByIssueId(newPublicationIssue.issueId)
            .then(function handleResponse(response) {
                articles = response;
                articles.sort(function sortArticleTopic(firstArticle, secondArticle) {
                    return firstArticle.topic > secondArticle.topic ? 1 : -1;
                });
                loading.stage = 'Загрузка редакторов и авторов...';
                render();

                return PublicationService.getEmployeeByArticlesIds(articles.map(function mapArticles(article) {
                    return article.id;
                }));
            })
            .then(function handleResponse(response) {
                response.forEach(function enumerationArticlesEmployees(articleEmployees, index) {
                    articles[index].author = articleEmployees.author;
                    articles[index].coauthors = articleEmployees.coauthors;
                    articles[index].editors = articleEmployees.editors;
                });
                articleGroupByTopic = {
                    title: articles[0].topic,
                    articles: [],
                    class: 'active',
                };
                articles.forEach(function enumerationArticles(article) {
                    if (article.topic !== articleGroupByTopic.title) {
                        groupedArticles.push(articleGroupByTopic);
                        articleGroupByTopic = {
                            title: article.topic,
                            articles: [],
                        };
                    }
                    articleGroupByTopic.articles.push({
                        title: article.title,
                        content: article.content,
                        author: article.author,
                        editors: article.editors,
                        coauthors: article.coauthors,
                    });
                });
                groupedArticles.push(articleGroupByTopic);

                loading.stage = 'Загрузка рекламы...';
                render();

                return PublicationService.getAdvertisingByIssueId(publicationIssue.id);
            })
            .then(function handleResponse(response) {
                advertising = response;
                loading.status = false;
                render();
            })
            .catch(function handleError(error) {
                console.log(error);
            });
    };

    $parentElement.on('click', '.tabs-topics', onTopicClickEvent);

    render();
}

module.exports = PublicationView;
