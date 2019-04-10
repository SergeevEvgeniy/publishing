package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Denis Shubin
 */
@Component
public class ArticleWebFacade {

    private SecurityService securityService;
    private ArticleService articleService;
    private EmployeeService employeeService;
    private PublishingService publishingService;
    private TopicDao topicDao;

    /**
     * @param securityService   {@link SecurityService}
     * @param articleService    {@link ArticleService}
     * @param employeeService   {@link EmployeeService}
     * @param publishingService {@link PublishingService}
     * @param topicDao          {@link TopicDao}
     */
    public ArticleWebFacade(final SecurityService securityService,
                            final ArticleService articleService,
                            final EmployeeService employeeService,
                            final PublishingService publishingService,
                            final TopicDao topicDao) {
        this.securityService = securityService;
        this.articleService = articleService;
        this.employeeService = employeeService;
        this.publishingService = publishingService;
        this.topicDao = topicDao;
    }

    /**
     * @return {@link List} of {@link ArticleInfo}
     */
    public List<ArticleInfo> getArticleInfoList() {
        User current = securityService.getCurrentUser();
        return articleService.getArticleInfoList(current);
    }

    /**
     * @return {@link ArticleForm}
     */
    public ArticleForm getNewArticleForm() {
        ArticleForm af = new ArticleForm();
        af.setPublishing(publishingService.getPublishingList());
        return af;
    }

    /**
     * @param articleId id статьи
     * @return {@link ArticleForm}
     */
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        User currentUser = securityService.getCurrentUser();
        Article article = articleService.getArticleById(articleId);
        ArticleForm form;
        if (article.getAuthorId() != currentUser.getId()) {
            form = getNewArticleForm();
        } else {
            form = articleService.getUpdateArticleFormByArticleId(articleId);
        }
        return form;
    }

    /**
     *
     * @return {@code true}, если текущий пользователь является журналистом
     */
    public boolean isJournalist() {
        User current = securityService.getCurrentUser();
        return current.getType().equals('J');
    }

    /**
     *
     * @param publishingId id журнала/газеты
     * @return {@link List} of {@link Topic}
     */
    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return topicDao.getTopicsByPublishingId(publishingId);
    }

    /**
     *
     * @param publishingId id журнала/газеты
     * @return {@link List} of {@link Employee}
     */
    public List<Employee> getEmployeesByPublishingId(final int publishingId) {
        return employeeService.getEmployeesByPublishingId(publishingId);
    }

    /**
     *
     * @param employeeId id сотрудника
     * @return {@link Employee}
     */
    public Employee getEmployeeById(final int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
}
