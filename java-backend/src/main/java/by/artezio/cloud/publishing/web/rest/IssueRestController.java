package by.artezio.cloud.publishing.web.rest;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Rest-controller для номеров.
 * @author Igor Kuzmin
 */
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private PublishingService publishingService;

    /**
     * @param publishingService publishingService
     */
    public IssueRestController(final PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    /**
     * @param publishingId id журнала/газеты
     * @return список всех тем журнала/газеты
     */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    public List<Topic> getPublishingTopics(@PathVariable("id") final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

}
