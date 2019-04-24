package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
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
        List<PublishingDTO> publishingList = issueFacade.getPublishingList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishing", publishingList);
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
        List<Topic> topics = issueFacade.getTopicListByPublishingId(issueId);
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
     * @param result - {@link BindingResult}.
     * @return - логическое имя представления.
     * */
    @PostMapping("/issue")
    public String createIssue(@Valid @ModelAttribute final IssueForm issueForm,
                              final BindingResult result) {
        if (result.hasErrors()) {
            return "issueForm";
        }
        return "redirect:/issues";
    }

    /**
     * Метод для обработки запроса на редактирования номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * @param result - {@link BindingResult}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @return - логическое имя представления.
     * */
    @PostMapping("/issue/{issueId}")
    public String updateIssue(@PathVariable("issueId") final int issueId,
                                    @Valid @ModelAttribute final IssueForm issueForm,
                                    final BindingResult result) {
        if (result.hasErrors()) {
            return "issueForm";
        }
        return "redirect:/issues";
    }

    /**
     * Получение списка тематикт {@link Topic} выбранного журнала/газеты
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @return список {@link Topic}.
     * */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    @ResponseBody
    public List<Topic> getTopicList(@PathVariable("id") final int publishingId) {
        return issueFacade.getTopicListByPublishingId(publishingId);
    }

    /**
     * Получение списка допущенных в публикацию авторов
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @return список {@link Employee}.
     * */
    @GetMapping(value = "/publishingId/{pid}/topicId/{tid}", headers = {"Accept=application/json"})
    @ResponseBody
    public List<Employee> getApprovedJournalist(@PathVariable("pid") final int publishingId,
                                                @PathVariable("tid") final int topicId) {
        return issueFacade.getApprovedJournalist(publishingId, topicId);
    }

    /**
     * Получение списка статей выбранного журнала/газеты,
     * для выбранной рубрики, выбранного автора,
     * предназначенная для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @param authorId - id {@link by.artezio.cloud.publishing.domain.Employee}.
     * @return список {@link Article}.
     * */
    @GetMapping(value = "/publishingId/{pid}/topicId/{tid}/authorId/{aid}",
        headers = {"Accept=application/json"})
    @ResponseBody
    public List<Article> getApprovedArticles(@PathVariable("pid") final int publishingId,
                                             @PathVariable("tid") final int topicId,
                                             @PathVariable("aid") final int authorId) {
        return issueFacade.getApprovedArticles(publishingId, topicId, authorId);
    }

}
