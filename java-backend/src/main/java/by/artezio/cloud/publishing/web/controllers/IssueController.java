package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;


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
        List<IssueForm> issueFormList = issueFacade.getIssueFormList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issueFormList", issueFormList);
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
        Map<Integer, String> publishingMap = issueFacade.getPublishingMap();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishing", publishingMap);
        modelAndView.addObject(new IssueForm());
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
        IssueForm issueForm = issueFacade.getIssueFormByIssueId(issueId);
        Map<Integer, String> publishingMap = issueFacade.getPublishingMap();
        List<Article> articles =
            issueFacade.getArticleListByIssueId(issueId);
        List<Advertising> advertising =
            issueFacade.getAdvertisingListByIssueId(issueId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishing", publishingMap);
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("advertising", advertising);
        modelAndView.addObject(issueForm);
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
        IssueForm issueForm = issueFacade.getIssueFormByIssueId(issueId);
        List<Article> articles =
            issueFacade.getArticleListByIssueId(issueId);
        List<Advertising> advertising =
            issueFacade.getAdvertisingListByIssueId(issueId);
        modelAndView.addObject(issueForm);
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("advertising", advertising);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

}
