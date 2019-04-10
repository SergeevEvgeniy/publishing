package by.artezio.cloud.publishing.rest.controllers;

import by.artezio.cloud.publishing.domain.Article;
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
@RequestMapping(path = "/article")
public class ArticleRestController {

    private ArticleRestFacade articleFacade;

    /**
     * @param articleFacade {@link ArticleRestFacade}
     */
    public ArticleRestController(final ArticleRestFacade articleFacade) {
        this.articleFacade = articleFacade;
    }


    /**
     * @param articleId - id статьи
     * @return статья {@link Article}
     */
    @GetMapping(value = "/articleById/{articleId}")
    public Article getArticleById(@PathVariable("articleId") final int articleId) {
        return articleFacade.getArticleById(articleId);
    }

    /**
     *
     * @param topicId id рубрики
     * @param publishingId id журнала
     * @return список статей с указаной рубрикой в указаннном журнале
     */
    @GetMapping(value = "/articleByTopicAndPublishingId/{topicId}/{publishingId}")
    public List<Article> getArticleByTopicAndPublishingId(@PathVariable("topicId") final int topicId,
                                                          @PathVariable("publishingId") final int publishingId) {
        return articleFacade.getArticleByTopicAndPublishingId(topicId, publishingId);
    }
}
