package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Контроллер, возвращающий jsp страницы.
 */
@Controller
public class ArticleController {

    private final ArticleService service;

    /**
     * @param service ArticalService
     */
    @Autowired
    public ArticleController(final ArticleService service) {
        this.service = service;
    }

    /**
     * Возвращает пользователю страницу со списком статей.
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return String
     */
    @GetMapping(path = "/article_list")
    public final String articleList(final Model model, final HttpServletRequest request) {

        List<ArticleInfo> data = service.getArticleInfoList(request);
        model.addAttribute("list", data);
        return "article_list";
    }

    /**
     * Возвращает пользователю страницу для создания/редактирования статьи.
     *
     * @param model Model
     * @return String назвавинее jsp страницы
     */
    @GetMapping(path = "/update")
    public final String createArticle(final Model model) {
        ArticleForm data = service.getNewArticleForm();
        model.addAttribute("model", data);
        return "update_article";
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
        ArticleForm form = service.getUpdateArticleFormByArticleId(articleId);
        model.addAttribute("model", form);
        return "update_article";
    }
}
