package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.web.facade.ArticleWebFacade;
import by.artezio.cloud.publishing.web.security.SecurityService;
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

    private final ArticleWebFacade articleFacade;
    private final SecurityService securityService;


    /**
     * @param articleFacade {@link ArticleWebFacade}
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
     * @return String
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
    public final String updateArticle(@PathVariable final int articleId, final Model model) {
        ArticleForm form = articleFacade.getUpdateArticleFormByArticleId(articleId);
        model.addAttribute("model", form);
        return "updateArticle";
    }
}
