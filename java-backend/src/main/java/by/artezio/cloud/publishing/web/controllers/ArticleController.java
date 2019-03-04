package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Контроллер, возвращающий jsp страницы.
 */
@Controller
public class ArticleController {

    private ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
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
     * @return String
     */
    @GetMapping(path = "/update")
    public final String updateArticle(final Model model, HttpServletRequest request) {
        ArticleForm data = service.getArticleForm(request);
        model.addAttribute("model", data);
        return "update_article";
    }
}
