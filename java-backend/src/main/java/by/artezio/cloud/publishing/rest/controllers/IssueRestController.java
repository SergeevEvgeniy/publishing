package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.service.IssueService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Rest-controller для номеров.
 * @author Igor Kuzmin
 */
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private IssueService issueService;

    /**
     * @param issueService {@link IssueService}
     */
    public IssueRestController(final IssueService issueService) {
        this.issueService = issueService;
    }

}
