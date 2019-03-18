package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
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

    private ModelMapper modelMapper;

    /**
     * Конструктор с параметрами.
     * @param publishingService {@link PublishingService}
     * @param issueService {@link IssueService}
     * @param modelMapper мапер объектов DTO и Entity
     * */
    public IssueController(final PublishingService publishingService,
                           final IssueService issueService,
                           final ModelMapper modelMapper) {
        this.publishingService = publishingService;
        this.issueService = issueService;
        this.modelMapper = modelMapper;
    }

    /**
     * @return ModelAndView - содержит данные о всех номерах,
     * а также имя главной страницы раздела номера
     * */
    @GetMapping
    public ModelAndView getIssuesPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Issue> issueList = issueService.getListOfAllIssues();
        List<IssueInfo> issueInfoList = convertIssueListToIssueInfoList(issueList);
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


    /**
     * Метод конвертации сущности Issue в DTO IssueInfo.
     * @param issue {@link Issue}
     * @return {@link IssueInfo}
     * */
    private IssueInfo convertIssueToIssueInfo(final Issue issue) {
        IssueInfo issueInfo = new IssueInfo();
        modelMapper.map(issue, issueInfo);
        Publishing publishing = publishingService.getPublishingById(issue.getId());
        issueInfo.setPublishingTitle(publishing.getTitle());
        return issueInfo;
    }

    /**
     * Метод конвертации списка сущностей Issue в список DTO IssueInfo.
     * @param issueList список сущностей {@link Issue}
     * @return список {@link IssueInfo}
     * */
    private List<IssueInfo> convertIssueListToIssueInfoList(final List<Issue> issueList) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (int i = 0; i < issueList.size(); i++) {
            IssueInfo issueInfo = convertIssueToIssueInfo(issueList.get(i));
            issueInfoList.add(issueInfo);
        }
        return issueInfoList;
    }

}
