package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер, обрабатывающий запросы, связанные со статьями.
 *
 * @author Denis Shubin
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final SecurityService securityService;
    private final PublishingService publishingService;
    private final EmployeeService employeeService;


    /**
     * @param articleService ArticalService
     * @param securityService SecurityService
     * @param employeeService EmployeeService
     * @param publishingService PublishingService
     */
    @Autowired
    public ArticleController(final ArticleService articleService,
                             final SecurityService securityService,
                             final PublishingService publishingService,
                             final EmployeeService employeeService) {
        this.articleService = articleService;
        this.securityService = securityService;
        this.publishingService = publishingService;
        this.employeeService = employeeService;
    }

    /**
     * Возвращает пользователю страницу со списком статей.
     *
     * @param model Model
     * @return String
     */
    @GetMapping
    public final String articleList(final Model model) {
        User current = securityService.getCurrentUser();
        List<ArticleInfo> data = articleService.getArticleInfoList(current);
        model.addAttribute("data", data);
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
        ArticleForm data = articleService.getNewArticleForm();
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
    public final String updateArticle(@PathVariable final int articleId, final Model model) {
        ArticleForm form = articleService.getUpdateArticleFormByArticleId(articleId);
        model.addAttribute("model", form);
        return "updateArticle";
    }
}
