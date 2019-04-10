package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.web.facade.ArticleWebFacade;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Контроллер, обрабатывающий запросы, связанные со статьями.
 *
 * @author Denis Shubin
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleWebFacade articleFacade;
    private final SecurityService securityService;


    /**
     * @param articleFacade   {@link ArticleWebFacade}
     * @param securityService {@link SecurityService}
     */
    public ArticleController(final ArticleWebFacade articleFacade,
                             final SecurityService securityService) {
        this.articleFacade = articleFacade;
        this.securityService = securityService;
    }

    /**
     * Возвращает пользователю страницу со списком статей.
     *
     * @param model Model
     * @return имя представления со списком статей
     */
    @GetMapping
    public final String articleList(final Model model) {
        boolean isJournalist = articleFacade.isJournalist();
        List<ArticleInfo> data = articleFacade.getArticleInfoList();
        model.addAttribute("data", data);
        model.addAttribute("isJournalist", isJournalist);
        return "articleList";
    }

    /**
     * Возвращает пользователю страницу для создания/редактирования статьи.
     *
     * @param model Model
     * @return String название jsp страницы
     */
    @GetMapping(path = "/new")
    public final String createArticle(final Model model) {
        ArticleForm data = articleFacade.getNewArticleForm();
        model.addAttribute("model", data);
        return "updateArticle";
    }

    /**
     * Получение формы для редактирования статьи.
     *
     * @param articleId идентификатор статьи (берётся из URI)
     * @param model     модель, в которую будет положена форма.
     * @return имя представления
     */
    @GetMapping(path = "/update/{articleId}")
    public final String updateArticle(@PathVariable("articleId") final int articleId, final Model model) {
        ArticleForm form = articleFacade.getUpdateArticleFormByArticleId(articleId);
        model.addAttribute("articleForm", form);
        model.addAttribute("isEditMode", true);
        return "updateArticle";
    }

    /**
     * Сохранение статьи и открытие списка статей.
     *
     * @param articleForm   {@link ArticleForm}
     * @param bindingResult {@link BindingResult}
     * @return страница со списком статей
     */
    @PostMapping(path = "/new")
    public final String saveArticle(@ModelAttribute("articleForm") final ArticleForm articleForm,
                                    final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            System.out.println(articleForm);
        }
        return "articleList";

    }

    /**
     * Удаление статьи по её идентификатору.
     *
     * @param articleId id статьи
     * @param model     {@link Model}
     * @return страница со списком статей
     */
    @DeleteMapping(path = "/delete/{articleId}")
    public final String deleteArticle(@PathVariable("articleId") final int articleId, final Model model) {
        Article deleted = articleFacade.deleteArticleById(articleId);
        model.addAttribute("article", deleted);
        return "articleList";
    }

    /**
     * @param publishingId id журнала
     * @return список рубрик для указанного журнала
     */
    @GetMapping(value = "/topicsByPublishing/{publishingId}")
    @ResponseBody
    public List<Topic> getTopicsByPublishing(@PathVariable("publishingId") final int publishingId) {
        return articleFacade.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала
     * @return список сотрудников для указанного журнала
     */
    @GetMapping(value = "/employeesByPublishing/{publishingId}")
    @ResponseBody
    public List<Employee> getEmployeeByPublishing(@PathVariable("publishingId") final int publishingId) {
        List<Employee> employees = articleFacade.getEmployeesByPublishingId(publishingId);
        for (int i = 0; i < employees.size(); i++) {
            Employee empl = employees.get(i);
            if (!empl.getType().equals('J')) {
                employees.remove(empl);
                i--;
            }
        }
        Employee current = articleFacade.getEmployeeById(securityService.getCurrentUser().getId());
        employees.remove(current);
        return employees;
    }
}
