package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private PublishingService publishingService;

    @GetMapping
    public ModelAndView getManePageForIssue() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issues");
        modelAndView.addObject("issueFormDto" ,new IssueForm());
        modelAndView.addObject("publishing", publishingService.getPublishingList());
        return modelAndView;
    }


    @Autowired
    public void setPublishingService(PublishingService publishingService) {
        this.publishingService = publishingService;
    }

}
