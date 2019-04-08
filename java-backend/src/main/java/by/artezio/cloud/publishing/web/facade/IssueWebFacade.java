package by.artezio.cloud.publishing.web.facade;


import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.IssueArticle;
import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.AdvertisingService;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс IssueWebFacade в котором размещена основная логика работы.
 * @author Igor Kuzmin
 * */
@Component
public class IssueWebFacade {

    private IssueService issueService;

    private PublishingService publishingService;

    private ArticleService articleService;

    private SecurityService securityService;

    private AdvertisingService advertisingService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * @param articleService - {@link AdvertisingService}
     * @param advertisingService - {@link AdvertisingService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService,
                          final ArticleService articleService,
                          final AdvertisingService advertisingService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
        this.articleService = articleService;
        this.advertisingService = advertisingService;
    }

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueForm}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueForm}
     * */
    private IssueForm mapIssueToIssueForm(final Issue issue) {
        IssueForm issueForm = new IssueForm();
        Publishing publishing = publishingService.getPublishingById(issue.getPublishingId());
        issueForm.setPublishingTitle(publishing.getTitle());
        issueForm.setPublishingId(issue.getPublishingId());
        issueForm.setNumber(issue.getNumber());
        issueForm.setLocalDate(issue.getDate());
        issueForm.setPublished(issue.isPublished());
        issueForm.setIssueId(issue.getId());
        return issueForm;
    }

    /**
     * Функция преобразования списка {@link Issue}
     * в список {@link IssueForm}.
     * @param issueList список {@link Issue}
     * @return список {@link IssueForm}
     * */
    private List<IssueForm> mapIssueListToIssueFormList(final List<Issue> issueList) {
        List<IssueForm> issueInfoList = new ArrayList<>();
        for (Issue issue : issueList) {
            issueInfoList.add(mapIssueToIssueForm(issue));
        }
        return issueInfoList;
    }

    /**
     * Метод для получения списка {@link IssueForm} который содержит
     * информацию о номерах доступные текущему пользователю.
     * @return список {@link IssueForm}
     * */
    public List<IssueForm> getIssueFormList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        List<Issue> issues = new ArrayList<>();
        if (user.isChiefEditor()) {
            issues = issueService.getListOfAllIssues();
        } else {
            List<Publishing> publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
            for (Publishing p : publishingList) {
                List<Issue> publishingIssues =
                    issueService.getIssueListByPublishingId(p.getId());
                issues.addAll(issues);
            }
        }
        return mapIssueListToIssueFormList(issues);
    }

    /**
     * Метод для получения  {@link Map} которая содержит
     * информацию о журналах/газетах {@link Publishing}
     * доступные текущему пользователю. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @return список {@link Publishing}
     * */
    public Map<Integer, String> getPublishingMap() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        Map<Integer, String> publishingMap = new HashMap<>();
        List<Publishing> publishingList;
        if (user.isChiefEditor()) {
            publishingList = publishingService.getPublishingList();
        } else {
            publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
        }
        for (Publishing p : publishingList) {
            publishingMap.put(p.getId(), p.getTitle());
        }
        return publishingMap;
    }

    /**
     * Получение списка статей по id номера.
     * @param issueId - id {@link Issue}
     * @return список {@link Article}
     * */
    public List<Article> getArticleListByIssueId(final int issueId) {
        List<IssueArticle> issueArticleList =
            issueService.getIssueArticleListByIssueId(issueId);
        List<Article> articleList = new ArrayList<>();
        for (IssueArticle ia : issueArticleList) {
            Article article = articleService.getArticleById(ia.getArticleId());
            articleList.add(article);
        }
        return articleList;
    }

    /**
     * Метод для получения dto {@link IssueForm}
     * по id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return {@link IssueForm}
     * */
    public IssueForm getIssueFormByIssueId(final int issueId) {
        securityService.checkIsEditor();
        Issue issue = issueService.getIssueById(issueId);
        return mapIssueToIssueForm(issue);
    }

    /**
     * Получение списка реклам по id номера.
     * @param issueId - id {@link Issue}
     * @return список {@link Advertising}
     * */
    public List<Advertising> getAdvertisingListByIssueId(final int issueId) {
        return advertisingService.getAdvertisingListByIssueId(issueId);
    }

}
