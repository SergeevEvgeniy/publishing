package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.AdvertisingService;
import by.artezio.cloud.publishing.service.ReviewService;
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

    private ReviewService reviewService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * @param articleService - {@link AdvertisingService}
     * @param advertisingService - {@link AdvertisingService}
     * @param reviewService - {@link ReviewService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService,
                          final ArticleService articleService,
                          final AdvertisingService advertisingService,
                          final ReviewService reviewService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
        this.articleService = articleService;
        this.advertisingService = advertisingService;
        this.reviewService = reviewService;
    }

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueForm}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueForm}
     * */
    private IssueForm mapIssueToIssueForm(final Issue issue) {
        IssueForm issueForm = new IssueForm();
        issueForm.setPublishingId(issue.getPublishingId());
        issueForm.setNumber(issue.getNumber());
        issueForm.setLocalDate(issue.getDate());
        issueForm.setPublished(issue.isPublished());
        issueForm.setIssueId(issue.getId());
        return issueForm;
    }

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueInfo}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueInfo}
     * */
    private IssueInfo mapIssueToIssueInfo(final Issue issue) {
        IssueInfo issueInfo = new IssueInfo();
        Publishing publishing =
            publishingService.getPublishingById(issue.getPublishingId());
        issueInfo.setPublishingTitle(publishing.getTitle());
        List<Integer> articleIdList =
            issueService.getArticleIdListByIssueId(issue.getId());
        issueInfo.setNumberOfArticle(articleIdList.size());
        issueInfo.setPublished(issue.isPublished());
        issueInfo.setNumber(issue.getNumber());
        issueInfo.setLocalDate(issue.getDate());
        issueInfo.setIssueId(issue.getId());
        return issueInfo;
    }

    /**
     * Функция преобразования списка {@link Issue}
     * в список {@link IssueForm}.
     * @param issueList список {@link Issue}
     * @return список {@link IssueForm}
     * */
    private List<IssueInfo> mapIssueListToIssueInfoList(final List<Issue> issueList) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issueList) {
            IssueInfo issueInfo = mapIssueToIssueInfo(issue);
            issueInfoList.add(issueInfo);
        }
        return issueInfoList;
    }

    /**
     * Метод для получения списка {@link IssueInfo} который содержит
     * информацию о номерах доступные текущему пользователю.
     * @return список {@link IssueInfo}
     * */
    public List<IssueInfo> getIssueInfoList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        List<Issue> issues = new ArrayList<>();
        if (user.isChiefEditor()) {
            issues = issueService.getListOfAllIssues();
        } else {
            List<PublishingDTO> publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
            for (PublishingDTO p : publishingList) {
                List<Issue> publishingIssues =
                    issueService.getIssueListByPublishingId(p.getId());
                issues.addAll(publishingIssues);
            }
        }
        return mapIssueListToIssueInfoList(issues);
    }

    /**
     * Метод для получения  {@link Map} которая содержит
     * информацию о журналах/газетах {@link PublishingDTO}
     * доступные текущему пользователю. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @return {@link Map}, где ключом является
     * id {@link PublishingDTO} значением является название {@link PublishingDTO}
     * */
    public Map<Integer, String> getPublishingMap() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        Map<Integer, String> publishingMap = new HashMap<>();
        List<PublishingDTO> publishingList;
        if (user.isChiefEditor()) {
            publishingList = publishingService.getPublishingList();
        } else {
            publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
        }
        for (PublishingDTO p : publishingList) {
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
        List<Article> articleList = new ArrayList<>();
        List<Integer> articleIdList =
            issueService.getArticleIdListByIssueId(issueId);
        for (Integer id : articleIdList) {
            Article article = articleService.getArticleById(id);
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
     * Метод для получения dto {@link IssueInfo}
     * по id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return {@link IssueInfo}
     * */
    public IssueInfo getIssueInfoByIssueId(final int issueId) {
        Issue issue = issueService.getIssueById(issueId);
        return mapIssueToIssueInfo(issue);
    }

    /**
     * Получение списка реклам по id номера.
     * @param issueId - id {@link Issue}
     * @return список {@link Advertising}
     * */
    public List<Advertising> getAdvertisingListByIssueId(final int issueId) {
        return advertisingService.getAdvertisingListByIssueId(issueId);
    }

    /**
     * Метод для получения  {@link Map} которая содержит
     * информацию о тематиках {@link Topic}
     * для данного {@link Publishing}. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @param publishingId - id {@link Publishing}
     * @return {@link Map}, где ключом является
     * id {@link Topic} значением является название {@link Topic}
     * */
    public Map<Integer, String> getTopicMapByPublishingId(final int publishingId) {
        List<Topic> topicList =
            publishingService.getTopicsByPublishingId(publishingId);
        Map<Integer, String> topicMap = new HashMap<>();
        for (Topic topic : topicList) {
            topicMap.put(topic.getId(), topic.getName());
        }
        return topicMap;
    }

}
