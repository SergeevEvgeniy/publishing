package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.ArticleStatistics;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.rest.facade.ArticleRestFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-controller для обработки запросов, связанных со статьями.
 * Основное предназначение - обработка запросов updateArticle.jsp.
 */
@RestController
@RequestMapping(path = "/api")
public class ArticleRestController {

    private ArticleRestFacade articleFacade;

    /**
     * @param articleFacade {@link ArticleRestFacade}
     */
    public ArticleRestController(final ArticleRestFacade articleFacade) {
        this.articleFacade = articleFacade;
    }


    /**
     * @param articleId id статьи
     * @return статья {@link ArticleDto}
     */
    @GetMapping("/article/{articleId}")
    public ArticleDto getArticleDto(@PathVariable final int articleId) {
        return articleFacade.getArticleDto(articleId);
    }


    /**
     * Метод возвращает список {@link ArticleDto}.
     *
     * @param filter объект, в в котором поля будут устанавливаться из параметров get запроса
     * @return список {@link ArticleDto}
     */
    @GetMapping("/articles")
    public List<ArticleDto> findArticles(ArticleFilter filter) {
        return articleFacade.getArticleDtoList(filter);
    }


    /**
     * Получение статистики по id автора.
     * <p>
     * authorId
     *
     * @param authorId id автора, для которого нужно собрать статистику
     * @return {@link ArticleStatistics}
     */
    @GetMapping("/article/statistics/{authorId}")
    public ArticleStatistics getArticleStatistics(@PathVariable final int authorId) {
        return articleFacade.getArticleStatistics(authorId);
    }

    /**
     * publishingId
     * topicId
     *
     * @param filter объект, в в котором поля будут устанавливаться из параметров get запроса
     * @return список id авторов, у которых есть статья в указанном журнале, с указанной рубрикой
     */
    @GetMapping("/authorsIdList")
    public List<Integer> getAuthorsIdList(final AuthorFilter filter) {
        return articleFacade.getAuthorsIdList(filter);
    }

    /**
     * @param articleId id статьи
     * @return {@code true}, если статья опубликована
     */
    @GetMapping("/article/isPublished/{articleId}")
    public Boolean isPublished(@PathVariable final int articleId) {
        return articleFacade.isPublished(articleId);
    }
}
