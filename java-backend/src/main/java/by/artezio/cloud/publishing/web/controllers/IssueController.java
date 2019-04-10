package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
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
        IssueInfo issueInfo = issueFacade.getIssueInfoByIssueId(issueId);
        List<Article> articles =
            issueFacade.getArticleListByIssueId(issueId);
        List<Advertising> advertising =
            issueFacade.getAdvertisingListByIssueId(issueId);
        modelAndView.addObject(issueInfo);
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("advertising", advertising);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * Получение {@link Map} журналов/газет для выподающего списка
     * на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.domain.Publishing}.
     * @return {@link Map}, где ключом является
     * id {@link by.artezio.cloud.publishing.domain.Publishing}
     * значением является название {@link by.artezio.cloud.publishing.domain.Publishing}
     * */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    @ResponseBody
    public Map<Integer, String> getTopicMap(@PathVariable("id") final int publishingId) {
        return issueFacade.getTopicMapByPublishingId(publishingId);
    }

    /**
     * Получение {@link Map} тематикт выбранного журнала/газеты
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.domain.Publishing}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @return {@link Map}, где ключом является
     * id {@link by.artezio.cloud.publishing.domain.Topic}
     * значением является название {@link by.artezio.cloud.publishing.domain.Topic}
     * */
    @GetMapping(value = "/publishingId/{pid}/topicId/{tid}", headers = {"Accept=application/json"})
    @ResponseBody
    public Map<Integer, String> getAuthorMap(@PathVariable("pid") final int publishingId,
                                      @PathVariable("tid") final int topicId) {
        /*Заглушка*/
        return null;
    }

}
