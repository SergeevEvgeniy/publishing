package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.dto.IssueView;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @GetMapping("creationForm")
    public ModelAndView openFormInCreationMode() {
        List<PublishingDTO> publishingList = issueFacade.getPublishingList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishing", publishingList);
        modelAndView.addObject(new IssueForm());
        modelAndView.setViewName("issueForm");
        modelAndView.addObject("mode", "create");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме редактирования номера, а также имя страницы jsp содержащая
     * форму для редактирования номера
     */
    @GetMapping("editionForm/issue/{id}")
    public ModelAndView openFormInEditingMode(@PathVariable("id") final int issueId) {
        IssueView issueView = issueFacade.getIssueViewByIssueId(issueId);
        List<TopicShortInfo> topics =
            issueFacade.getTopicListByPublishingId(issueView.getPublishingId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issueId", issueId);
        modelAndView.addObject(issueView);
        modelAndView.addObject("topics", topics);
        modelAndView.addObject(new IssueForm());
        modelAndView.addObject("mode", "edit");
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @param issueId id номера
     * @return ModelAndView - содержит данные используемые
     * в режиме просмотра номера, а также имя страницы jsp содержащая
     * форму для просмотра номера
     */
    @GetMapping("viewingForm/issue/{id}")
    public ModelAndView openFormInViewingMode(@PathVariable("id") final int issueId) {
        ModelAndView modelAndView = new ModelAndView();
        IssueView issueView = issueFacade.getIssueViewByIssueId(issueId);
        modelAndView.addObject(issueView);
        modelAndView.setViewName("issueView");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на удаление номера, рекламы и статей в номере.
     * @param issueId - id номера.
     * */
    @DeleteMapping("/issue/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(@PathVariable("id") final int issueId) {
        issueFacade.deleteIssueById(issueId);
    }

    /**
     * Метод для обработки запроса на создание нового номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * @param result - {@link BindingResult}.
     * @param model - {@link Model}.
     * @return - логическое имя представления.
     * */
    @PostMapping("/issue")
    public String createIssue(@Valid @ModelAttribute final IssueForm issueForm,
                              final BindingResult result,
                              final Model model) {
        if (!result.hasErrors()) {
            issueFacade.createNewIssue(issueForm);
            return "redirect:/issues";
        }
        Integer publishingId = issueForm.getPublishingId();
        model.addAttribute("publishing", issueFacade.getPublishingList());
        if (publishingId != null) {
            model.addAttribute("topics", issueFacade.getTopicListByPublishingId(publishingId));
        }
        model.addAttribute(issueForm);
        IssueView issueView = issueFacade.mapIssueFormToIssueView(issueForm);
        model.addAttribute(issueView);
        model.addAttribute("mode", "create");
        return "issueForm";
    }

    /**
     * Метод для обработки запроса на редактирования номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * @param result - {@link BindingResult}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param model - {@link Model}.
     * @return - логическое имя представления.
     * */
    @PostMapping("/issue/{issueId}")
    public String updateIssue(@PathVariable("issueId") final int issueId,
                              @Valid @ModelAttribute final IssueForm issueForm,
                              final BindingResult result, final Model model) {
        if (!result.hasErrors()) {
            issueFacade.updateIssue(issueId, issueForm);
            return "redirect:/issues";
        }
        IssueView issueView = issueFacade.mapIssueFormToIssueView(issueForm);
        List<TopicShortInfo> topics =
            issueFacade.getTopicListByPublishingId(issueView.getPublishingId());
        model.addAttribute("topics", topics);
        model.addAttribute("issueId", issueId);
        model.addAttribute(issueForm);
        model.addAttribute(issueView);
        model.addAttribute("mode", "edit");
        return "issueForm";
    }

    /**
     * Получение списка тематикт {@link by.artezio.cloud.publishing.domain.Topic}
     * выбранного журнала/газеты.
     * для выподающего списка на форме добавления.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @return список {@link by.artezio.cloud.publishing.domain.Topic}.
     * */
    @GetMapping(value = "/publishingId/{id}", headers = {"Accept=application/json"})
    @ResponseBody
    public List<TopicShortInfo> getTopicList(@PathVariable("id") final int publishingId) {
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
