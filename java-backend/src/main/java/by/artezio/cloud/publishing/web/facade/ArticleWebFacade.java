package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Article;
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

    public ArticleWebFacade(final SecurityService securityService,
                            final ArticleService articleService,
                            final EmployeeService employeeService,
                            final PublishingService publishingService) {
        this.securityService = securityService;
        this.articleService = articleService;
        this.employeeService = employeeService;
        this.publishingService = publishingService;
    }

    public List<ArticleInfo> getArticleInfoList() {
        User current = securityService.getCurrentUser();
        return articleService.getArticleInfoList(current);
    }

    public ArticleForm getNewArticleForm() {
        ArticleForm af = new ArticleForm();
        af.setPublishing(publishingService.getPublishingList());
        return af;
    }

    public ArticleForm getUpdateArticleFormByArticleId(int articleId) {
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

    public boolean isJournalist() {
        User current = securityService.getCurrentUser();
        return current.getType().equals('J');
    }
}