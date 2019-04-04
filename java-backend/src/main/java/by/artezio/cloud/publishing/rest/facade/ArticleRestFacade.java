package by.artezio.cloud.publishing.rest.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Denis Shubin
 */
@Component
public class ArticleRestFacade {

    private TopicDao topicDao;
    private EmployeeService employeeService;
    private ArticleService articleService;

    public ArticleRestFacade(final TopicDao topicDao,
                             final EmployeeService employeeService,
                             final ArticleService articleService) {
        this.topicDao = topicDao;
        this.employeeService = employeeService;
        this.articleService = articleService;
    }

    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return topicDao.getTopicsByPublishingId(publishingId);
    }

    public List<Employee> getEmployeesByPublishingId(final int publishingId) {
        return employeeService.getEmployeesByPublishingId(publishingId);
    }

    public Article getArticleById(final int articleId) {
        return articleService.getArticleById(articleId);
    }
}
