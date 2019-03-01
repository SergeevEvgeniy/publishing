package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleListDTO;
import by.artezio.cloud.publishing.dto.UpdateArticleDTO;
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

    @Autowired
    ArticleService service;

    /**
     * Возвращает пользователю страницу со списком статей.
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return String
     */
    @GetMapping(path = "/article_list")
    public final String articleList(final Model model, final HttpServletRequest request) {

        List<ArticleListDTO> data = service.getArticleListDto(request);
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
        UpdateArticleDTO data = service.getUpdateArticleDto(request);
        model.addAttribute("model", data);
        return "update_article";
    }
}
