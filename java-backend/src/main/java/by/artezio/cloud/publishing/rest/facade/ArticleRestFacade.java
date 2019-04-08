package by.artezio.cloud.publishing.rest.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.springframework.stereotype.Component;

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
}
