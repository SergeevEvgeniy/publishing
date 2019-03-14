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
 * Rest-controller для номеров.
 * @author Igor Kuzmin
 */
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private PublishingService publishingService;
    private EmployeeService employeeService;

    /**
     * @param publishingService {@link PublishingService}
     * @param employeeService {@link EmployeeService}
     */
    public IssueRestController(final PublishingService publishingService,
                               final EmployeeService employeeService) {
        this.publishingService = publishingService;
        this.employeeService = employeeService;
    }

    /**
     * @param publishingId id журнала/газеты
     * @return список всех тем журнала/газеты
     */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    public List<Topic> getTopicsByPublishingId(@PathVariable("id") final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала/газеты
     * @param topicId id рубрики
     * @return множество всех авторов у которых есть статьи
     * в данном журнале, в данной рубрике,
     * отмеченные как "в публикацию"
     * */
    @GetMapping(value = "/publishingId/{pId}/topicId/{tId}", headers = {"Accept=application/json"})
    public Set<Employee> getAuthorByPublishingAndTopicId(@PathVariable("pId") final int publishingId,
                                                 @PathVariable("tId") final int topicId) {

        return null;
    }

}
