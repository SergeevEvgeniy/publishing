package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Issue контроллер.
 *
 * @author rezerv
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    private PublishingService publishingService;

    /**
     *
     * @return ModelAndView
     */
    @GetMapping
    public ModelAndView getManePageForIssue() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issues");
        modelAndView.addObject("issueFormDto", new IssueForm());
        modelAndView.addObject("publishing", publishingService.getPublishingList());
        return modelAndView;
    }

    /**
     *
     * @param issueForm issueForm
     * @return название вьюшки
     */
    @PostMapping
    public String getIssueFormDto(final IssueForm issueForm) {
        System.out.println(issueForm);
        List<Topic> tl = publishingService.getTopicsByPublishingId(issueForm.getPublishingId());
        return "home";
    }

    /**
     *
     * @param publishingService publishingService
     */
    @Autowired
    public void setPublishingService(final PublishingService publishingService) {
        this.publishingService = publishingService;
    }

}
