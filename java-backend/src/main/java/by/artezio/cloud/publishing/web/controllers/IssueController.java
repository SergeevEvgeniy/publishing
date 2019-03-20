package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


/**
 * Контроллер для страницы номеров.
 * @author Igor Kuzmin
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    private PublishingService publishingService;

    private IssueService issueService;

    /**
     * Конструктор с параметрами.
     * @param publishingService {@link PublishingService}
     * @param issueService {@link IssueService}
     * */
    public IssueController(final PublishingService publishingService,
                           final IssueService issueService) {
        this.publishingService = publishingService;
        this.issueService = issueService;
    }

    /**
     * @return ModelAndView - содержит данные о всех номерах,
     * а также имя главной страницы раздела номера
     * */
    @GetMapping
    public ModelAndView getIssuesPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<IssueInfo> issueInfoList = issueService.getListOfAllIssueInfo();
        modelAndView.addObject("issueInfoList", issueInfoList);
        modelAndView.setViewName("issues");
        return modelAndView;
    }

    /**
     * @return ModelAndView - содержит данные используемые
     * в режиме создания номера, а также имя страницы jsp содержащая
     * форму для создания нового номера
     */
    @GetMapping(params = "new")
    public ModelAndView openFormInCreationMode() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("method", "POST");
        modelAndView.addObject("publishing", publishingService.getPublishingList());
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме редактирования номера, а также имя страницы jsp содержащая
     * форму для редактирования номера
     */
    @GetMapping(params = "editableIssue")
    public ModelAndView openFormInEditingMode(@RequestParam("editableIssue") final int issueId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме просмотра номера, а также имя страницы jsp содержащая
     * форму для просмотра номера
     */
    @GetMapping(params = "viewedIssue")
    public ModelAndView openFormInViewingMode(@RequestParam("viewedIssue") final int issueId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

}
