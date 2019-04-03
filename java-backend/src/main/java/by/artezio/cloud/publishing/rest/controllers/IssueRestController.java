package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

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

    private IssueService issueService;

    /**
     * @param publishingService {@link PublishingService}
     * @param issueService {@link IssueService}
     */
    public IssueRestController(final PublishingService publishingService,
                               final IssueService issueService) {
        this.publishingService = publishingService;
        this.issueService = issueService;
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

    /**
     * @param id идентификатор {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    @DeleteMapping("/issue/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(@PathVariable("id") final int id) {
        issueService.deleteIssueById(id);
    }

    /**
     * @param id идентификатор {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    @PutMapping("/issue/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editIssue(@PathVariable("id") final int id) {
        /*Контролле для обработки запросов на обновления ресурса Issue*/
    }

}
