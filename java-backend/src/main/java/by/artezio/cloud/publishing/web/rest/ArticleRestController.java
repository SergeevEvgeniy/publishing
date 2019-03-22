package by.artezio.cloud.publishing.web.rest;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
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

    /**
     * @param publishingService {@link PublishingService}
     * @param employeeService {@link EmployeeService}
     */
    public ArticleRestController(final PublishingService publishingService,
                                 final EmployeeService employeeService) {
        this.publishingService = publishingService;
        this.employeeService = employeeService;
    }

    /**
     * @param publishingId id журнала
     * @return список рубрик для указанного журнала
     */
    @GetMapping(value = "/topicsByPublishing/{publishingId}")
    public List<Topic> getTopicsById(@PathVariable("publishingId") final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала
     * @return список сотрудников для указанного журнала
     */
    @GetMapping(value = "/employeesByPublishing/{publishingId}")
    public Set<Employee> getEmployeeByPublishingId(@PathVariable("publishingId") final int publishingId) {
        return employeeService.getEmployeesByPublishingId(publishingId);
    }

}
