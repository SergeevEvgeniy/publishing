package by.artezio.cloud.publishing.web.rest;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/issues/**", headers = {"Accept=application/json"})
public class IssueRestController {

    private PublishingService publishingService;

    @GetMapping("/publishing")
    public List<Topic> getPublishingTopics(@RequestParam("id") int publishingId ) {
        return publishingService.getPublishingTopics(publishingId);
    }

    @Autowired
    public void setPublishingService(PublishingService publishingService) {
        this.publishingService = publishingService;
    }

}
