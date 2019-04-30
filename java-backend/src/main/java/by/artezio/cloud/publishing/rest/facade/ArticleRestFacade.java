package by.artezio.cloud.publishing.rest.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.ArticleStatistics;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.IssueService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
     * Получение статистики по статьям.
     *
     * @param authorId id автора
     * @return {@link ArticleStatistics}
     */
    public ArticleStatistics getArticleStatistics(final int authorId) {
        ArticleStatistics statistics = new ArticleStatistics();
        statistics.setAuthorId(authorId);

        ArticleFilter filter = new ArticleFilter();
        filter.setAuthorId(authorId);

        int articleCount = articleService.getArticleCountByAuthorId(authorId);
        statistics.setArticleCount(articleCount);

        List<ArticleDto> dtoList = articleService.getArticleDtoList(filter);
        Map<String, Integer> countByPublishing = new HashMap<>(dtoList.size());
        for (ArticleDto dto : dtoList) {
            countByPublishing.put(dto.getTitle(), 0);
        }
        for (ArticleDto dto : dtoList) {
            Integer count = countByPublishing.get(dto.getTitle());
            count++;
            countByPublishing.put(dto.getTitle(), count);
        }

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
    public List<Integer> getAuthorsIdList(final AuthorFilter filter) {
        List<Integer> list = articleService.getAuthorsIdList(filter);
        if (filter.getPublished() != null) {
            for (int i = 0; i < list.size(); i++) {

                if (!checkForFilter(list.get(i), filter.getPublished())) {
                    list.remove(i);
                    i--;
                }

            }
        }
        return list;
    }

    /**
     * Проверка, есть ли у указанного автора опубликованные (или неопубликованные) статьи.
     *
     * @param authorId  id автора
     * @param published {@code true}, если нужно проверить, есть ли у автора опубликованные статьи.
     *                  {@code false} в обратном случае.
     * @return {@code true}, если автор прошёл проверку, иначе - {@code false}
     */
    private boolean checkForFilter(final int authorId, final Boolean published) {
        boolean result = false;
        ArticleFilter newFilter = new ArticleFilter();
        newFilter.setAuthorId(authorId);
        List<ArticleDto> dtoList = articleService.getArticleDtoList(newFilter);
        for (ArticleDto articleDto : dtoList) {
            result = result || published.equals(issueService.isArticlePublished(articleDto.getId()));
        }
        return result;
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
