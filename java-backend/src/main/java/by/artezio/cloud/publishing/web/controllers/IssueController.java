package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
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

    private IssueWebFacade issueFacade;

    /**
     * Конструктор с параметрами.
     * @param issueFacade {@link IssueWebFacade}
     * */
    public IssueController(final IssueWebFacade issueFacade) {
        this.issueFacade = issueFacade;
    }

    /**
     * @return ModelAndView - содержит данные о всех номерах,
     * а также имя главной страницы раздела номера
     * */
    @GetMapping
    public ModelAndView getIssuesPage() {
        List<IssueInfo> issueInfoList = issueFacade.getIssueInfoList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issueInfoList", issueInfoList);
        modelAndView.setViewName("issues");
        return modelAndView;
    }

    /**
     * @return ModelAndView - содержит данные используемые
     * в режиме создания номера, а также имя страницы jsp содержащая
     * форму для создания нового номера
     */
    @GetMapping(params = "mode=create")
    public ModelAndView openFormInCreationMode() {
        List<Publishing> publishingList = issueFacade.getPublishingList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishing", publishingList);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме редактирования номера, а также имя страницы jsp содержащая
     * форму для редактирования номера
     */
    @GetMapping(params = "mode=edit")
    public ModelAndView openFormInEditingMode(@RequestParam("id") final int issueId) {
        IssueInfo issueInfo = issueFacade.getIssueInfoByIssueId(issueId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(issueInfo);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме просмотра номера, а также имя страницы jsp содержащая
     * форму для просмотра номера
     */
    @GetMapping(params = "mode=view")
    public ModelAndView openFormInViewingMode(@RequestParam("id") final int issueId) {
        ModelAndView modelAndView = new ModelAndView();
        IssueInfo issueInfo = issueFacade.getIssueInfoByIssueId(issueId);
        modelAndView.addObject(issueInfo);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

}
