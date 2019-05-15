package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.IssueOperationResult;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueView;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.web.facade.IssueWebFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private void initFormForErrorMode(final Model model, final IssueForm issueForm) {
        model.addAttribute("publishing", issueFacade.getPublishingList());
        Integer publishingId = issueForm.getPublishingId();
        if (publishingId != null) {
            model.addAttribute("topics", issueFacade.getTopicListByPublishingId(publishingId));
        }
        if (issueForm.getArticlesId() != null) {
            List<ArticleDto> articles = issueFacade.getArticlesByArticlesIdList(issueForm.getArticlesId());
            model.addAttribute("articles", articles);
        }
    }

    /**
     * @param model - модель данных {@link Model}.
     * @return ModelAndView - содержит данные о всех номерах,
     * а также имя главной страницы раздела номера
     * */
    @GetMapping
    public String getIssuesPage(final Model model) {
        List<IssueInfo> issueInfoList = issueFacade.getIssueInfoList();
        model.addAttribute("issueInfoList", issueInfoList);
        return "issues";
    }

    /**
     * @return ModelAndView - содержит данные используемые
     * в режиме создания номера, а также имя страницы jsp содержащая
     * форму для создания нового номера
     */
    @GetMapping("new")
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
    @GetMapping("edit/{id}")
    public ModelAndView openFormInEditingMode(@PathVariable("id") final Integer issueId) {
        IssueForm issueForm = issueFacade.getIssueFormByIssueId(issueId);
        List<PublishingDTO> publishing = issueFacade.getPublishingList();
        List<TopicShortInfo> topics =
            issueFacade.getTopicListByPublishingId(issueForm.getPublishingId());
        List<ArticleDto> articles = issueFacade.getArticlesByArticlesIdList(issueForm.getArticlesId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issueId", issueId);
        modelAndView.addObject("publishing", publishing);
        modelAndView.addObject("topics", topics);
        modelAndView.addObject("articles", articles);
        modelAndView.addObject(issueForm);
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
    @GetMapping("view/{id}")
    public ModelAndView openFormInViewingMode(@PathVariable("id") final Integer issueId) {
        IssueView issueView = issueFacade.getIssueViewByIssueId(issueId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(issueView);
        modelAndView.setViewName("issueView");
        return modelAndView;
    }

    /**
     * Метод обработки запроса на удаление номера, рекламы и статей в номере.
     * @param issueId - id номера.
     * @param redirectAttributes - {@link RedirectAttributes}.
     * @return - адрес перенаправления запроса.
     * */
    @PostMapping("delete")
    public String deleteIssue(@RequestParam("issueId") final Integer issueId,
                              final RedirectAttributes redirectAttributes) {
        IssueOperationResult operationResult = issueFacade.deleteIssueById(issueId);
        redirectAttributes.addFlashAttribute("result", operationResult);
        return "redirect:/issues";
    }

    /**
     * Метод для обработки запроса на создание нового номера.
     * @param issueForm - dto для формы создания и редактирования,
     * данный объект привязан к полям формы.
     * @param result - {@link BindingResult}.
     * @param model - {@link Model}.
     * @param redirectAttributes - {@link RedirectAttributes}.
     * @return - логическое имя представления.
     * */
    @PostMapping("new")
    public String createIssue(@Valid @ModelAttribute final IssueForm issueForm,
                              final BindingResult result,
                              final Model model, final RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            IssueOperationResult operationResult = issueFacade.createNewIssue(issueForm);
            redirectAttributes.addFlashAttribute("result", operationResult);
            return "redirect:/issues";
        }
        initFormForErrorMode(model, issueForm);
        model.addAttribute(issueForm);
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
     * @param redirectAttributes - {@link RedirectAttributes}.
     * @return - логическое имя представления.
     * */
    @PostMapping("edit/{id}")
    public String updateIssue(@PathVariable("id") final Integer issueId,
                              @Valid @ModelAttribute final IssueForm issueForm,
                              final BindingResult result, final Model model,
                              final RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            IssueOperationResult operationResult = issueFacade.updateIssue(issueId, issueForm);
            redirectAttributes.addFlashAttribute("result", operationResult);
            return "redirect:/issues";
        }
        initFormForErrorMode(model, issueForm);
        model.addAttribute("issueId", issueId);
        model.addAttribute(issueForm);
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
    @GetMapping(value = "topics", headers = {"Accept=application/json"})
    @ResponseBody
    public List<TopicShortInfo> getTopicList(@RequestParam("publishingId") final int publishingId) {
        return issueFacade.getTopicListByPublishingId(publishingId);
    }

    /**
     * Получение списка допущенных в публикацию авторов
     * для выподающего списка на форме добавления.
     * @param authorFilter - {@link AuthorFilter}.
     * @return список {@link Employee}.
     * */
    @GetMapping(value = "authors", headers = {"Accept=application/json"})
    @ResponseBody
    public List<Employee> getApprovedAuthors(final AuthorFilter authorFilter) {
        return issueFacade.getApprovedAuthors(authorFilter);
    }

    /**
     * Получение списка статей выбранного журнала/газеты,
     * для выбранной рубрики, выбранного автора,
     * предназначенная для выподающего списка на форме добавления.
     * @param articleFilter - {@link ArticleFilter}.
     * @return список {@link ArticleDto}.
     * */
    @GetMapping(value = "articles", headers = {"Accept=application/json"})
    @ResponseBody
    public List<ArticleDto> getApprovedArticles(final ArticleFilter articleFilter) {
        return issueFacade.getApprovedArticles(articleFilter);
    }

}
