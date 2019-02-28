package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.ArticleListDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер, возвращающий jsp страницы.
 */
@Controller
public class ArticleListController {

    /**
     * Возвращает пользователю страницу со списком статей.
     * @param model Model
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @GetMapping(path = "/article_list")
    public final ModelAndView articleList(final Model model, final HttpServletRequest request) {

        List<ArticleListDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            ArticleListDTO dto = new ArticleListDTO();
            dto.setAuthorFullName("author " + i);
            dto.setPublishing("mishutka");
            dto.setTitle("title " + i);
            dto.setTopic("horror");
            List<String> coauthors = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                coauthors.add("coauthor " + j);
            }
            dto.setCoauthors(coauthors);
            list.add(dto);
        }


        model.addAttribute("list", list);
        ModelAndView mav = new ModelAndView("article_list", "list", list);
        return mav;
    }
}
