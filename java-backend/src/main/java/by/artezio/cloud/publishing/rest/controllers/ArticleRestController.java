package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.rest.facade.ArticleRestFacade;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-controller для обработки запросов, связанных со статьями.
 * Основное предназначение - обработка запросов updateArticle.jsp.
 */
@RestController
@RequestMapping(path = "/article")
public class ArticleRestController {

    private ArticleRestFacade articleFacade;

    /**
     *
     * @param articleFacade {@link ArticleRestFacade}
     */
    public ArticleRestController(final ArticleRestFacade articleFacade) {
        this.articleFacade = articleFacade;
    }

    /**
     * @param publishingId id журнала
     * @return список рубрик для указанного журнала
     */
    @GetMapping(value = "/topicsByPublishing/{publishingId}")
    public List<Topic> getTopicsByPublishing(@PathVariable("publishingId") final int publishingId) {
        return articleFacade.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала
     * @return список сотрудников для указанного журнала
     */
    @GetMapping(value = "/employeesByPublishing/{publishingId}")
    public List<Employee> getEmployeeByPublishing(@PathVariable("publishingId") final int publishingId) {
        return articleFacade.getEmployeesByPublishingId(publishingId);
    }

    /**
     * @param articleId - id статьи
     * @return статья {@link Article}
     */
    @GetMapping(value = "/articleById/{articleId}")
    public Article getArticleById(@PathVariable("articleId") final int articleId) {
        return articleFacade.getArticleById(articleId);
    }
}
