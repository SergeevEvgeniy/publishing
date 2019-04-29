package by.artezio.cloud.publishing.rest.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.ArticleStatistics;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.IssueService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * @author Denis Shubin
 */
@Component
public class ArticleRestFacade {

    private TopicDao topicDao;
    private EmployeeService employeeService;
    private ArticleService articleService;
    private IssueService issueService;

    /**
     * @param topicDao        {@link TopicDao}
     * @param employeeService {@link by.artezio.cloud.publishing.dao.EmployeeDao}
     * @param articleService  {@link ArticleService}
     * @param issueService    {@link IssueService}
     */
    public ArticleRestFacade(final TopicDao topicDao,
                             final EmployeeService employeeService,
                             final ArticleService articleService,
                             final IssueService issueService) {
        this.topicDao = topicDao;
        this.employeeService = employeeService;
        this.articleService = articleService;
        this.issueService = issueService;
    }

    /**
     * @param articleId id статьи
     * @return статья
     */
    public ArticleDto getArticleById(final int articleId) {
        return articleService.getArticleDtoById(articleId);
    }

    /**
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    public List<ArticleDto> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
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
     * Получение списка id авторов, которые проходят указаный фильтр.
     *
     * @param filter фильтр, с помощью которого из бд выбираются записи
     * @return список идентификаторов авторов
     */
    public List<Integer> getAuthorsIdList(AuthorFilter filter) {
        List<Integer> list = articleService.getAuthorsIdList(filter);

        //todo Реализовать выборку id авторов с опубликованными (или неопубликованными) статьями

        return list;
    }

    /**
     * Получение статьи по её идентификатору.
     *
     * @param articleId id статьи
     * @return {@link ArticleDto}
     */
    public ArticleDto getArticleDto(final int articleId) {
        return articleService.getArticleDto(articleId);
    }

    /**
     * Получение списка статей, которые проходят указанный фильтр.
     *
     * @param filter фильтр, с помощью которого выбираются записи из бд
     * @return список статей
     */
    public List<ArticleDto> getArticleDtoList(final ArticleFilter filter) {
        List<ArticleDto> list = articleService.getArticleDtoList(filter);
        if (filter.getPublished() != null) {
            for (int i = 0; i < list.size(); i++) {
                ArticleDto articleDto = list.get(i);
                if (filter.getPublished() != issueService.isArticlePublished(articleDto.getId())) {
                    list.remove(articleDto);
                    i--;
                }
            }
        }
        return list;
    }

    /**
     * Проверка того, опубликована ли статья с указанным идентификатором.
     *
     * @param articleId id статьи
     * @return {@code true}, если статья опубликована, иначе - {@code false}
     */
    public Boolean isPublished(final int articleId) {
        return issueService.isArticlePublished(articleId);
    }
}
