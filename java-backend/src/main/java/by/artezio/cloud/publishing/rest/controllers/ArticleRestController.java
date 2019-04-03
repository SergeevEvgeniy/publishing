package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * REST-controller для обработки запросов, связанных со статьями.
 * Основное предназначение - обработка запросов updateArticle.jsp.
 */
@RestController
@RequestMapping(path = "/article")
public class ArticleRestController {

    private PublishingService publishingService;
    private EmployeeService employeeService;
    private ArticleService articleService;

    /**
     * @param publishingService {@link PublishingService}
     * @param employeeService   {@link EmployeeService}
     * @param articleService {@link ArticleService}
     */
    public ArticleRestController(final PublishingService publishingService,
                                 final EmployeeService employeeService,
                                 final ArticleService articleService) {
        this.publishingService = publishingService;
        this.employeeService = employeeService;
        this.articleService = articleService;
    }

    /**
     * @param publishingId id журнала
     * @return список рубрик для указанного журнала
     */
    @GetMapping(value = "/topicsByPublishing/{publishingId}")
    public List<Topic> getTopicsByPublishing(@PathVariable("publishingId") final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала
     * @return список сотрудников для указанного журнала
     */
    @GetMapping(value = "/employeesByPublishing/{publishingId}")
    public Set<Employee> getEmployeeByPublishing(@PathVariable("publishingId") final int publishingId) {
        return employeeService.getEmployeesByPublishingId(publishingId);
    }

    /**
     * @param articleId - id статьи
     * @return статья {@link Article}
     * */
    @GetMapping(value = "/articleById/{articleId}")
    public Article getArticleById(@PathVariable("articleId") final int articleId) {
        return articleService.getArticleById(articleId);
    }
}
