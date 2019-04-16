package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        Map<Integer, String> topics = issueFacade.getTopicMapByPublishingId(issueId);
        List<Article> articles =
            issueFacade.getArticlesForIssue(issueForm.getArticlesId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(issueForm);
        modelAndView.addObject("topics", topics);
        modelAndView.addObject("articles", articles);
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
            issueFacade.getArticlesForIssue(issueForm.getArticlesId());
        modelAndView.addObject(issueForm);
        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на удаление номера, рекламы и статей в номере.
     * @param issueId - id номера.
     * */
    @DeleteMapping("/issue/{issueId}")
    public void deleteIssue(@PathVariable("issueId") final int issueId) {
        issueFacade.deleteIssueById(issueId);
    }

    /**
     * Метод для обработки запроса на создание нового номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * */
    @PostMapping("/issue")
    public void createIssue(@ModelAttribute final IssueForm issueForm) {
        System.out.println(issueForm);
        /*Заглушка*/
    }

    /**
     * Метод для обработки запроса на редактирования номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * */
    @PutMapping("/issue/{issueId}")
    public void updateIssue(@ModelAttribute final IssueForm issueForm) {
        System.out.println(issueForm);
        /*Заглушка*/
    }

    /**
     * Получение {@link Map} тематикт выбранного журнала/газеты
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @return {@link Map}, где ключом является
     * id {@link by.artezio.cloud.publishing.domain.Topic}
     * значением является название {@link by.artezio.cloud.publishing.domain.Topic}
     * */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    @ResponseBody
    public Map<Integer, String> getTopicMap(@PathVariable("id") final int publishingId) {
        return issueFacade.getTopicMapByPublishingId(publishingId);
    }

    /**
     * Получение {@link Map} допущенных в публикацию авторов
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @return {@link Map}, где ключом является
     * id {@link by.artezio.cloud.publishing.domain.Employee}
     * значением является имя {@link by.artezio.cloud.publishing.domain.Employee}
     * */
    @GetMapping(value = "/publishingId/{pid}/topicId/{tid}", headers = {"Accept=application/json"})
    @ResponseBody
    public Map<Integer, String> getAuthorMap(@PathVariable("pid") final int publishingId,
                                      @PathVariable("tid") final int topicId) {
        return issueFacade.getApprovedAuthor(publishingId, topicId);
    }

    /**
     * Получение {@link Map} статей выбранного журнала/газеты,
     * для выбранной рубрики, выбранного автора,
     * предназначенная для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @param authorId - id {@link by.artezio.cloud.publishing.domain.Employee}.
     * @return {@link Map}, где ключом является
     * id {@link by.artezio.cloud.publishing.domain.Article}
     * значением является название {@link by.artezio.cloud.publishing.domain.Article}
     * */
    @GetMapping(value = "/publishingId/{pid}/topicId/{tid}/authorId/{aid}",
        headers = {"Accept=application/json"})
    @ResponseBody
    public Map<Integer, String> getArticleMap(@PathVariable("pid") final int publishingId,
                                             @PathVariable("tid") final int topicId,
                                             @PathVariable("aid") final int authorId) {
        return issueFacade.getApprovedArticles(publishingId, topicId, authorId);
    }

}
