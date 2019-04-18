package by.artezio.cloud.publishing.rest.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleStatistics;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Denis Shubin
 */
@Component
public class ArticleRestFacade {

    private TopicDao topicDao;
    private EmployeeService employeeService;
    private ArticleService articleService;

    /**
     * @param topicDao        {@link TopicDao}
     * @param employeeService {@link by.artezio.cloud.publishing.dao.EmployeeDao}
     * @param articleService  {@link ArticleService}
     */
    public ArticleRestFacade(final TopicDao topicDao,
                             final EmployeeService employeeService,
                             final ArticleService articleService) {
        this.topicDao = topicDao;
        this.employeeService = employeeService;
        this.articleService = articleService;
    }

    /**
     * @param articleId id статьи
     * @return статья
     */
    public Article getArticleById(final int articleId) {
        return articleService.getArticleById(articleId);
    }

    /**
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    public List<Article> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
        return articleService.getArticleByTopicAndPublishingId(topicId, publishingId);
    }

    /**
     * Получение статистики по статьям.
     *
     * @param authorId id автора
     * @return {@link ArticleStatistics}
     */
    public ArticleStatistics getArticleStatistics(final int authorId) {
        ArticleStatistics statistics = new ArticleStatistics();
        statistics.setAuthorId(authorId);

        int articleCount = articleService.getArticleCountByAuthorId(authorId);
        statistics.setArticleCount(articleCount);

        Map<String, Integer> countByPublishing = articleService.getArticleCountByPublishingMap(authorId);
        statistics.setArticleCountByPublishing(countByPublishing);

        Map<String, Integer> countByTopic = articleService.getArticleCountByTopicMap(authorId);
        statistics.setArticleCountByTopics(countByTopic);

        return statistics;
    }

    /**
     * Получить список неопубликованных статей.
     *
     * @param publishingId id журнала
     * @param topicId      id рубрики
     * @param authorId     id автора
     * @return Список {@link ArticleDto}
     */
    public List<ArticleDto> getUnpublishedArticles(final int publishingId,
                                                   final int topicId,
                                                   final int authorId) {
        return articleService.getUnpublishedArticles(publishingId, topicId, authorId);
    }
}
